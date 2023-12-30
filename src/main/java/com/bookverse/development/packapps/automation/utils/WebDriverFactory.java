package com.bookverse.development.packapps.automation.utils;

import static com.bookverse.development.packapps.automation.utils.Constants.CHROME;
import static com.bookverse.development.packapps.automation.utils.Constants.DEFAULT_BROWSER;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;

public class WebDriverFactory {

  private WebDriverFactory(){}

  public static WebDriver goToWeb(String url) {
    WebDriver driver = getDriver();
    driver.get(url);
    driver.manage().window().maximize();

    return driver;
  }

  private static WebDriver getDriver() {
    return (DEFAULT_BROWSER.equals(CHROME)) ? getChromeDriver() : getEdgeDriver();
  }

  private static WebDriver getChromeDriver() {

    ChromeOptions chromeOptions = new ChromeOptions();

    chromeOptions.addArguments(
        "--incognito",
        "--disable-infobars",
        "enable-automation",
        "--disable-browser-side-navigation");

    return WebDriverManager.chromedriver()
        .capabilities(chromeOptions)
        .arm64()
        .create();
  }

  private static WebDriver getEdgeDriver() {

    EdgeOptions edgeOptions = new EdgeOptions();
    edgeOptions.setCapability("inprivate", true);

    return WebDriverManager.edgedriver().capabilities(edgeOptions).create();
  }
}
