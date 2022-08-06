package com.bookverse.development.packapps.automation.utils;

import static net.serenitybdd.core.environment.EnvironmentSpecificConfiguration.from;

import net.thucydides.core.environment.SystemEnvironmentVariables;
import net.thucydides.core.util.EnvironmentVariables;

public class Constants {

  private static final EnvironmentVariables CONF = SystemEnvironmentVariables.createEnvironmentVariables();

  public static final String BOOKVERSE_DEV = from(CONF).getProperty("config.environment.dev");
  public static final String BOOKVERSE_PDN = from(CONF).getProperty("config.environment.prod");
  public static final String DEFAULT_BROWSER = from(CONF).getProperty("config.browser.default");
  public static final String ACTOR = from(CONF).getProperty("config.browser.actor");
  public static final String CHROME = "Chrome";
  public static final String ALERT_ERROR = "Ocurrió un error inesperado";
  public static final String USER_REGISTERED = "USER_REGISTERED";

  private Constants() {
  }
}