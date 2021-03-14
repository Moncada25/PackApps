package com.bookverse.development.packapps.automation.utils;

import static com.bookverse.development.packapps.automation.utils.Constants.BROWSER;
import static com.bookverse.development.packapps.automation.utils.Constants.CHROME;
import static com.bookverse.development.packapps.automation.utils.Constants.CHROME_DRIVER;
import static com.bookverse.development.packapps.automation.utils.Constants.DRIVER;
import static com.bookverse.development.packapps.automation.utils.Constants.EDGE;
import static com.bookverse.development.packapps.automation.utils.Constants.EDGE_DRIVER;
import static com.bookverse.development.packapps.automation.utils.Constants.MAC;
import static com.bookverse.development.packapps.automation.utils.Constants.OS;
import static com.bookverse.development.packapps.automation.utils.Constants.RUN_DRIVER;
import static com.bookverse.development.packapps.automation.utils.Constants.SYSTEM;
import static com.bookverse.development.packapps.automation.utils.Constants.WINDOWS;

import com.bookverse.development.packapps.models.DataSet;
import java.io.File;
import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.edge.EdgeOptions;

public class WebDriverFactory {

  private static String defaultBrowser = DataSet.getDefaultBrowser();

  public static WebDriver goToWeb(String url) {
    WebDriver driver = getDriver();
    driver.get(url);
    driver.manage().window().maximize();

    return driver;
  }

  private static WebDriver getDriver() {
    return (defaultBrowser.equals(CHROME)) ? getChromeDriver() : getEdgeDriver();
  }

  private static WebDriver getChromeDriver() {

    ChromeDriverService chromeDriverService =
        new ChromeDriverService.Builder()
            .usingDriverExecutable(new File(getPath()))
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

  private static WebDriver getEdgeDriver() {

    EdgeDriverService edgeDriverService = new EdgeDriverService.Builder()
        .usingDriverExecutable(new File(getPath()))
        .usingAnyFreePort()
        .build();

    try {
      edgeDriverService.start();
    } catch (IOException e) {
      e.printStackTrace();
    }

    EdgeOptions edgeOptions = new EdgeOptions();
    edgeOptions.setCapability("InPrivate", true);

    return new EdgeDriver(edgeDriverService, edgeOptions);
  }

  private static String getPath() {

    String currentSystem = (System.getProperty(OS).contains(MAC))
        ? System.getProperty(OS).replace(" OS X", "")
        : System.getProperty(OS).replace(" 10", "");

    String driverPath;

    switch (defaultBrowser) {

      case CHROME:

        driverPath = (currentSystem.equals(MAC))
            ? DRIVER.replace(BROWSER, defaultBrowser).replace(SYSTEM, MAC)
            .replace(RUN_DRIVER, CHROME_DRIVER)
            : DRIVER.replace(BROWSER, defaultBrowser).replace(SYSTEM, WINDOWS)
                .replace(RUN_DRIVER, CHROME_DRIVER).concat(".exe");

        break;

      case EDGE:

        driverPath = (currentSystem.equals(MAC))
            ? DRIVER.replace(BROWSER, defaultBrowser).replace(SYSTEM, MAC)
            .replace(RUN_DRIVER, EDGE_DRIVER)
            : DRIVER.replace(BROWSER, defaultBrowser).replace(SYSTEM, WINDOWS)
                .replace(RUN_DRIVER, EDGE_DRIVER).concat(".exe");

        break;

      default:
        throw new IllegalArgumentException("Browser invalid");
    }

    return driverPath;
  }
}
