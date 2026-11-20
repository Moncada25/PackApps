package com.bookverse.packapps;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class DesktopTest {
  @Test
  void savesAndReopens(@TempDir Path directory) throws Exception {
    for (String step : new String[] {"write", "read"}) {
      var process =
          new ProcessBuilder(
                  Path.of(System.getProperty("java.home"), "bin", "java").toString(),
                  "--enable-native-access=ALL-UNNAMED",
                  "-cp",
                  System.getProperty("packapps.testClasspath"),
                  DesktopChecks.class.getName(),
                  directory.toString(),
                  step)
              .inheritIO();
      process.environment().put("PACKAPPS_HOME", directory.toString());
      assertEquals(0, process.start().waitFor());
    }
  }
}
