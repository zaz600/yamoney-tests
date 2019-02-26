package ru.yandex.money.qa.tests.pages

import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory
import ru.yandex.money.qa.tests.utils.AllureHelper
import ru.yandex.money.qa.tests.utils.ExtendedFieldDecorator
import java.net.URL

abstract class AbstractPage(val driver: WebDriver) {

    abstract val pageUrl: String
    abstract val serverUrl: String

    open val fullUrl get() = URL(serverUrl).toURI().resolve(pageUrl).normalize().toURL().toExternalForm()!!

    open fun initElements(page: AbstractPage) {
        PageFactory.initElements(ExtendedFieldDecorator(DefaultElementLocatorFactory(driver)), page)
    }

    open fun open() {
        val url = serverUrl + pageUrl.trimStart('/')
        AllureHelper.makeStep("Открытие страницы $url") {
            driver.get(url)
        }
    }
}
