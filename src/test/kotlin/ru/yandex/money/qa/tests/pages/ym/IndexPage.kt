package ru.yandex.money.qa.tests.pages.ym

import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.FindBy
import ru.yandex.money.qa.tests.YM_URL
import ru.yandex.money.qa.tests.pages.AbstractPage
import ru.yandex.money.qa.tests.utils.ElementDescription
import ru.yandex.money.qa.tests.utils.WebElementEx

class IndexPage(driver: WebDriver) : AbstractPage(driver) {
    override val pageUrl: String = "/"
    override val homeUrl: String = YM_URL

    init {
        initElements(this)
    }

    @FindBy(css = "div.header2__main span.user a.button")
    @ElementDescription("Кнопка Войти")
    lateinit var loginButton: WebElementEx
}