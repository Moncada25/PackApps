package com.bookverse.development.packapps.automation.utils;

import static net.serenitybdd.core.environment.EnvironmentSpecificConfiguration.from;

import net.thucydides.core.util.EnvironmentVariables;
import net.thucydides.core.util.SystemEnvironmentVariables;

public class Constants {

  private static final EnvironmentVariables conf = SystemEnvironmentVariables.createEnvironmentVariables();

  public static final String BOOKVERSE_DEV = from(conf).getProperty("config.environment.dev");
  public static final String BOOKVERSE_PDN = from(conf).getProperty("config.environment.prod");
  public static final String DEFAULT_BROWSER = from(conf).getProperty("config.browser.default");
  public static final String ACTOR = from(conf).getProperty("config.browser.actor");
  public static final String CHROME = "Chrome";
  public static final String ALERT_ERROR = "Ocurrió un error inesperado";
  public static final String USER_REGISTERED = "USER_REGISTERED";

  private Constants() {
  }
}