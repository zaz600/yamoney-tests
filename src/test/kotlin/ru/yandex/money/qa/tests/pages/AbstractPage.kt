package ru.yandex.money.qa.tests.pages

import org.openqa.selenium.WebDriver
import ru.yandex.money.qa.tests.utils.AllureHelper

abstract class AbstractPage(val driver: WebDriver) {

    abstract val pageUrl: String
    abstract val homeUrl: String

    open val fullUrl get() = homeUrl.trimEnd('/') + "/" + pageUrl.trimStart('/')

    open fun open() {
        val url = homeUrl + pageUrl.trimStart('/')
        AllureHelper.makeStep("Открытие страницы $url") {
            driver.get(url)
        }
    }
}
