package com.bookverse.development.packapps.utils;

import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.thucydides.core.util.EnvironmentVariables;
import net.thucydides.core.util.SystemEnvironmentVariables;

public class ReadConfiguration {

    private static final EnvironmentVariables CONF = SystemEnvironmentVariables
            .createEnvironmentVariables();

    public static String getData(String data) {
        return EnvironmentSpecificConfiguration.from(CONF)
                .getProperty(data);
    }
}