package ru.yandex.money.qa.tests.specs

import io.github.bonigarcia.wdm.WebDriverManager
import io.qameta.allure.Attachment
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle
import org.junit.jupiter.api.TestMethodOrder
import org.openqa.selenium.OutputType
import org.openqa.selenium.TakesScreenshot
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import ru.yandex.money.qa.tests.HEADLESS_MODE
import ru.yandex.money.qa.tests.utils.AllureHelper
import ru.yandex.money.qa.tests.utils.CurrentUrlChecker
import java.util.concurrent.TimeUnit


enum class WebDriverType {
    //    REMOTE,
    CHROME;

    companion object {
        fun from(s: String): WebDriverType = values().find { it.name.toLowerCase() == s.toLowerCase() } ?: CHROME
    }
}


@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation::class)
abstract class AbstractSeleniumTest {

    abstract val driver: WebDriver

    val currentUrl by lazy { CurrentUrlChecker(driver) }

    @AfterAll
    open fun afterAll() {
        driver.quit()
    }

    @AfterEach
    open fun afterTest() {
        saveScreenshot("After test")
    }

    operator fun String.invoke(runnable: () -> Unit) {
        AllureHelper.makeStep(this, runnable)
    }


    @Attachment(value = "Screenshot {0}", type = "image/png")
    @Suppress("UNUSED_PARAMETER")
    open fun saveScreenshot(name: String): ByteArray {
        return (driver as TakesScreenshot).getScreenshotAs(OutputType.BYTES)
    }

    /**
     * Создает вебдрайвер хрома
     */
    private fun createChromeWebDriver(options: ChromeOptions = ChromeOptions()): WebDriver {
        WebDriverManager.chromedriver().setup()

        val op = ChromeOptions()
        op.addArguments("test-type")
        op.setAcceptInsecureCerts(true)
        op.setHeadless(HEADLESS_MODE)
        op.addArguments("--window-size=1920,1080")
        op.merge(options)

        val driver = ChromeDriver(op)
        driver.manage()?.timeouts()?.implicitlyWait(10, TimeUnit.SECONDS)
        return driver
    }


    /**
     * Создает WebDriver в зависимости от переменой окружения webdriver
     * Если переменная окружения не выставлена, возвращает ChromeWebDriver
     */
    fun createWebDriver(): WebDriver {
        val webDriverProperty = System.getenv("webdriver") ?: "CHROME"

        return when (WebDriverType.from(webDriverProperty)) {
            WebDriverType.CHROME -> createChromeWebDriver()
        }
    }
}