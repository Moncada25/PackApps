"""Build native downloads and exercise the installed contents on the current host."""

import os
import platform
import shutil
import subprocess
import sys
import tarfile
import tempfile
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
VERSION = (ROOT / "gradle.properties").read_text().strip().split("=")[1]
OS = platform.system()
ARCH = "arm64" if platform.machine().lower() in ("aarch64", "arm64") else "x64"
OUTPUT = ROOT / "build" / "release"
NAME = f"PackApps-{VERSION}"
JDK = Path(os.environ["JAVA_HOME"])


def run(*args, **kwargs):
    return subprocess.run(list(map(str, args)), check=True, **kwargs)


def verify(launcher, runtime, lib, scratch):
    with tempfile.TemporaryDirectory(dir=scratch) as directory:
        env = {**os.environ, "PACKAPPS_HOME": directory, "JAVA_HOME": directory}
        env.pop("PACKAPPS_MYSQL_URL", None)
        env.pop("JRE_HOME", None)
        run(launcher, "--check-startup", env=env, timeout=90)
        classes = ROOT / "build/classes/java/test"
        for step in ["write", "read"]:
            run(
                runtime,
                "--enable-native-access=ALL-UNNAMED",
                "-cp",
                os.pathsep.join([str(classes), str(lib / "*")]),
                "com.bookverse.packapps.DesktopChecks",
                directory,
                step,
                env=env,
                timeout=120,
            )


def package(scratch):
    run(
        ROOT / ("gradlew.bat" if OS == "Windows" else "gradlew"),
        "installDist",
        "testClasses",
        cwd=ROOT,
    )
    runtime = scratch / "runtime"
    run(
        JDK / "bin/jlink",
        "--add-modules",
        "java.se,jdk.crypto.ec,jdk.unsupported,jdk.charsets,jdk.localedata",
        "--strip-debug",
        "--no-man-pages",
        "--no-header-files",
        "--output",
        runtime,
    )
    icon = (
        ROOT
        / "src/main/resources"
        / {
            "Darwin": "icons/cat.icns",
            "Windows": "images/icon.ico",
            "Linux": "icons/cat.png",
        }[OS]
    )
    opts = [
        JDK / "bin/jpackage",
        "--name",
        "PackApps",
        "--app-version",
        VERSION,
        "--vendor",
        "Bookverse",
    ]
    stage = scratch / "stage"
    run(
        *opts,
        "--type",
        "app-image",
        "--dest",
        stage,
        "--runtime-image",
        runtime,
        "--input",
        ROOT / "build/install/PackApps/lib",
        "--main-jar",
        f"PackApps-{VERSION}.jar",
        "--main-class",
        "com.bookverse.packapps.core.Start",
        "--icon",
        icon,
        "--java-options",
        "--enable-native-access=ALL-UNNAMED",
    )
    image = stage / ("PackApps.app" if OS == "Darwin" else "PackApps")
    installer = scratch / "installer"
    installer.mkdir()

    def build(kind, *extra):
        run(*opts, "--type", kind, "--dest", installer, "--app-image", image, *extra)
        return next(installer.glob("*." + kind))

    if OS == "Darwin":
        run("codesign", "--force", "--deep", "--sign", "-", image)
        dmg = build("dmg")
        mount = scratch / "mounted"
        run("hdiutil", "attach", "-readonly", "-nobrowse", "-mountpoint", mount, dmg)
        try:
            app = mount / "PackApps.app"
            run("codesign", "--verify", "--deep", "--strict", app)
            verify(
                app / "Contents/MacOS/PackApps",
                app / "Contents/runtime/Contents/Home/bin/java",
                app / "Contents/app",
                scratch,
            )
        finally:
            run("hdiutil", "detach", mount)
        shutil.copy2(dmg, OUTPUT / f"{NAME}-macos-{ARCH}.dmg")
    elif OS == "Windows":
        exe = build(
            "exe",
            "--win-per-user-install",
            "--win-menu",
            "--win-upgrade-uuid",
            "101de0bb-990c-45a9-ae96-f10e6e2626d2",
        )
        run(exe, "/quiet", "/norestart", timeout=180)
        app = Path(os.environ["LOCALAPPDATA"]) / "PackApps"
        verify(app / "PackApps.exe", app / "runtime/bin/java.exe", app / "app", scratch)
        shutil.copy2(exe, OUTPUT / f"{NAME}-windows-{ARCH}.exe")
    elif OS == "Linux":
        deb = build(
            "deb",
            "--linux-package-name",
            "packapps",
            "--linux-shortcut",
            "--linux-menu-group",
            "Utility",
        )
        run("sudo", "apt-get", "install", "-y", deb)
        app = Path("/opt/packapps")
        verify(
            app / "bin/PackApps", app / "lib/runtime/bin/java", app / "lib/app", scratch
        )
        shutil.copy2(deb, OUTPUT / f"{NAME}-linux-{ARCH}.deb")
        archive = OUTPUT / f"{NAME}-linux-{ARCH}.tar.gz"
        with tarfile.open(archive, "w:gz") as tar:
            tar.add(image, arcname="PackApps")
        portable = scratch / "portable"
        with tarfile.open(archive, "r:gz") as tar:
            tar.extractall(portable, filter="data")
        app = portable / "PackApps"
        verify(
            app / "bin/PackApps", app / "lib/runtime/bin/java", app / "lib/app", scratch
        )
    else:
        raise ValueError(OS)


if __name__ == "__main__":
    OUTPUT.mkdir(parents=True, exist_ok=True)
    with tempfile.TemporaryDirectory() as temp:
        package(Path(temp))
