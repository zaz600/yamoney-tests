package ru.yandex.money.qa.tests.utils

import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.Dimension
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import java.time.Duration
import java.util.concurrent.TimeUnit

/**
 * Создает преднастроенный вебдрайвер Chrome
 * Есть возможность в стиле dsl настроить его
 * example:
 * val driver = chromeBrowser {
headless { true }
implicitlyWait { Duration.ofSeconds(11) }
screenSize { Dimension(1920, 1080)

chromeOptions {
setPageLoadStrategy(PageLoadStrategy.EAGER)
addArguments("--window-size=1920,1080")
}

driverConfig {
manage().deleteAllCookies()
}
}
 *
 */

class ChromeDriverBuilder {
    private var options: ChromeOptions.() -> Unit = {}
    private var config: ChromeDriver.() -> Unit = {}
    private var headless = true
    private var insecure = true
    private var implicitlyWaitTimeout: Duration = Duration.ZERO
    private var screenSize: Dimension = Dimension(1920, 1080)

    fun chromeOptions(lambda: ChromeOptions.() -> Unit) {
        options = lambda
    }

    fun driverConfig(lambda: ChromeDriver.() -> Unit) {
        config = lambda
    }

    fun headless(lambda: () -> Boolean) {
        this.headless = lambda()
    }

    fun acceptInsecure(lambda: () -> Boolean) {
        this.insecure = lambda()
    }

    fun implicitlyWait(lambda: () -> Duration) {
        this.implicitlyWaitTimeout = lambda()
    }

    fun screenSize(lambda: () -> Dimension) {
        this.screenSize = lambda()
    }

    fun build(): ChromeDriver {
        WebDriverManager.chromedriver().setup()
        val options = ChromeOptions()
                .addArguments("test-type")
                .setHeadless(headless)
                .setAcceptInsecureCerts(insecure)
                .addArguments("--window-size=${screenSize.width},${screenSize.height}")
                .apply(options)
        return ChromeDriver(options).apply {
            manage().timeouts().implicitlyWait(implicitlyWaitTimeout.toMillis(), TimeUnit.MILLISECONDS)
            config
        }
    }
}

fun chromeBrowser(lambda: ChromeDriverBuilder.() -> Unit): ChromeDriver = ChromeDriverBuilder().apply(lambda).build()
