package com.bookverse.packapps.automation.utils

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.edge.EdgeDriver
import org.openqa.selenium.edge.EdgeOptions
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxOptions
import org.openqa.selenium.firefox.FirefoxProfile

object WebApp {

    private val logger: Logger = LoggerFactory.getLogger(this::class.java)
    private var driver: WebDriver? = null

    @JvmStatic
    fun start(url: String, headLess: Boolean): WebDriver {

        val browser = SerenityConf.getBrowser()

        driver = when (browser) {
            SerenityConf.CHROME -> { getChromeDriver(headLess) }
            SerenityConf.FIREFOX -> { getFirefoxDriver(headLess) }
            SerenityConf.EDGE -> { getEdgeDriver(headLess) }
            else -> throw IllegalArgumentException("Browser '$browser' not supported!")
        }

        driver?.get(url)
        driver?.manage()?.window()?.maximize()

        return driver as WebDriver
    }

    @JvmStatic
    fun stop() {

        if (driver != null) {
            driver?.quit()
            logger.info("Driver closed!")
        }
    }

    @JvmStatic
    fun getDriver(): WebDriver {
        return driver as WebDriver
    }

    private fun getChromeDriver(headLess: Boolean): WebDriver {

        val prefs: MutableMap<String, Any> = HashMap()
        prefs["profile.default_content_setting_values.notifications"] = 1

        val options = ChromeOptions()
        options.addArguments(getChoromiumArguments(headLess))
        options.setExperimentalOption("prefs", prefs)

        return ChromeDriver(options)
    }

    private fun getFirefoxDriver(headLess: Boolean): WebDriver {

        val options = FirefoxOptions()
        val profile = FirefoxProfile()

        val arguments = mutableListOf(
            "enable-automation",
            "-private"
        )

        if (headLess) arguments.add("-headless")

        profile.setPreference("permissions.default.desktop-notification", 1)
        options.setProfile(profile)
        options.addArguments(arguments)

        return FirefoxDriver(options)
    }

    private fun getEdgeDriver(headLess: Boolean): WebDriver {
        return EdgeDriver(EdgeOptions().addArguments(getChoromiumArguments(headLess)))
    }

    private fun getChoromiumArguments(headLess: Boolean): List<String> {

        val arguments = mutableListOf(
            "--incognito",
            "--disable-infobars",
            "enable-automation",
            "--disable-browser-side-navigation",
            "start-maximized",
            "--disable-extensions"
        )

        if (headLess) arguments.add("--headless=new")

        return arguments
    }
}