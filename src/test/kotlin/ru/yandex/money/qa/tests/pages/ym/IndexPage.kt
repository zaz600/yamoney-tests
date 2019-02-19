package ru.yandex.money.qa.tests.pages.ym

import io.qameta.allure.Step
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory
import ru.yandex.money.qa.tests.YM_URL
import ru.yandex.money.qa.tests.pages.AbstractPage

class IndexPage(driver: WebDriver) : AbstractPage(driver) {
    override val pageUrl: String = "/"
    override val homeUrl: String = YM_URL

    init {
        PageFactory.initElements(driver, this)
    }

    @FindBy(css = "div.header2__main span.user a.button")
    private lateinit var loginButton: WebElement

    @Step("Нажатие на кнопку Войти")
    fun clickLoginButton() = loginButton.click()
}