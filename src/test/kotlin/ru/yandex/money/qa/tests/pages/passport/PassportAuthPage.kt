package ru.yandex.money.qa.tests.pages.passport

import io.qameta.allure.Step
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory
import ru.yandex.money.qa.tests.PASSPORT_URL
import ru.yandex.money.qa.tests.pages.AbstractPage

class PassportAuthPage(driver: WebDriver) : AbstractPage(driver) {
    override val pageUrl: String = "/auth"
    override val homeUrl: String = PASSPORT_URL

    init {
        PageFactory.initElements(driver, this)
    }

    @FindBy(id = "passp-field-login")
    private lateinit var fieldLogin: WebElement

    @FindBy(id = "passp-field-passwd")
    private lateinit var fieldPassword: WebElement

    @FindBy(css = "div.passp-sign-in-button button.button2_type_submit")
    private lateinit var signInButton: WebElement


    @Step("Заполнение значения поля Логин")
    fun fillLogin(login: String) {
        fieldLogin.sendKeys(login)
    }

    @Step("Заполнение значения поля Пароль")
    fun fillPassword(password: String) {
        fieldPassword.sendKeys(password)
    }

    @Step("Нажатие на кнопку Войти")
    fun signInClick() = signInButton.click()

}