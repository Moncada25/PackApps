package com.bookverse.development.packapps.automation.utils;

import static com.bookverse.development.packapps.automation.utils.Constants.CHROME_DRIVER;
import static com.bookverse.development.packapps.automation.utils.Constants.MAC;
import static com.bookverse.development.packapps.automation.utils.Constants.OS;
import static com.bookverse.development.packapps.automation.utils.Constants.SYSTEM;
import static com.bookverse.development.packapps.automation.utils.Constants.WINDOWS;

import java.io.File;
import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverFactory {

  public static WebDriver goToWeb(String url) {
    WebDriver driver = getDriver();
    driver.get(url);
    driver.manage().window().maximize();

    return driver;
  }

  private static WebDriver getDriver() {

    String currentSystem = (System.getProperty(OS).contains(MAC))
        ? System.getProperty(OS).replace(" OS X", "")
        : System.getProperty(OS).replace(" 10", "");

    String driver = (currentSystem.equals(MAC))
        ? CHROME_DRIVER.replace(SYSTEM, MAC)
        : CHROME_DRIVER.replace(SYSTEM, WINDOWS).concat(".exe");

    ChromeDriverService chromeDriverService =
        new ChromeDriverService.Builder()
            .usingDriverExecutable(new File(driver))
            .usingAnyFreePort()
            .build();

    try {
      chromeDriverService.start();
    } catch (IOException e) {
      e.printStackTrace();
    }

    ChromeOptions chromeOptions = new ChromeOptions();

    chromeOptions.addArguments(
        "--incognito",
        "--disable-infobars",
        "enable-automation",
        "--disable-browser-side-navigation");

    return new ChromeDriver(chromeDriverService, chromeOptions);
  }
}
