package ru.yandex.money.qa.tests.pages.passport

import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.FindBy
import ru.yandex.money.qa.tests.PASSPORT_URL
import ru.yandex.money.qa.tests.pages.AbstractPage
import ru.yandex.money.qa.tests.utils.ElementDescription
import ru.yandex.money.qa.tests.utils.WebElementEx

class PassportAuthPage(driver: WebDriver) : AbstractPage(driver) {
    override val pageUrl: String = "/auth"
    override val homeUrl: String = PASSPORT_URL

    init {
        initElements(this)
    }

    @FindBy(id = "passp-field-login")
    @ElementDescription("Поле Логин")
    lateinit var fieldLogin: WebElementEx

    @FindBy(id = "passp-field-passwd")
    @ElementDescription("Поле Пароль")
    lateinit var fieldPassword: WebElementEx

    @FindBy(css = "div.passp-sign-in-button button.button2_type_submit")
    @ElementDescription("Кнопка Войти")
    lateinit var signInButton: WebElementEx
}