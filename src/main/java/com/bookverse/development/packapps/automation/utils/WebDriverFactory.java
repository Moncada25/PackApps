package com.bookverse.development.packapps.automation.utils;

import static com.bookverse.development.packapps.automation.utils.Constants.CHROME;
import static com.bookverse.development.packapps.automation.utils.Constants.DEFAULT_BROWSER;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
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

    List<String> options = List.of(
        "--incognito",
        "--disable-infobars",
        "enable-automation",
        "--disable-browser-side-navigation");

    return new ChromeDriver(new ChromeOptions().addArguments(options));
  }

  private static WebDriver getEdgeDriver() {
    return new EdgeDriver(new EdgeOptions().addArguments(List.of("inprivate")));
  }
}
