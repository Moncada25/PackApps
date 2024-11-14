package com.bookverse.development.packapps.automation.utils

import net.thucydides.model.environment.SystemEnvironmentVariables
import org.slf4j.LoggerFactory

object SerenityConf {
    private val logger = LoggerFactory.getLogger(SerenityConf::class.java)
    private const val CONF_ENVIRONMENT = "environment."
    private const val CONF_DATABASE = "database."
    const val CHROME = "Chrome"
    const val FIREFOX = "Firefox"
    const val EDGE = "Edge"

    @JvmField
    val CONF: SystemEnvironmentVariables = SystemEnvironmentVariables.createEnvironmentVariables()

    @JvmStatic
    fun getConfig(key: String): String? {
        val config = CONF.getProperty(key)

        if (config != null) {
            logger.info("Config found! '$key': $config")
        } else {
            logger.warn("Config not found! '$key'")
        }

        return config
    }

    @JvmStatic
    fun getEnvironmentConfig(config: String): String? {
        return getConfig("$CONF_ENVIRONMENT$config")
    }

    @JvmStatic
    fun getDatabaseConfig(config: String): String? {
        return getConfig("$CONF_DATABASE$config")
    }

    @JvmStatic
    fun getBrowser(): String {
        return getEnvironmentConfig("browser") ?: CHROME
    }

    @JvmStatic
    fun getDevUrl(): String {
        return getEnvironmentConfig("dev")!!
    }
}