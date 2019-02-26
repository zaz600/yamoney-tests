package ru.yandex.money.qa.tests.pages.ym

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import ru.yandex.money.qa.tests.YM_URL
import ru.yandex.money.qa.tests.pages.AbstractPage
import ru.yandex.money.qa.tests.utils.ElementDescription
import ru.yandex.money.qa.tests.utils.WebElementEx

class MainPage(driver: WebDriver) : AbstractPage(driver) {
    override val pageUrl: String = "/actions"
    override val serverUrl: String = YM_URL

    init {
        initElements(this)
    }

    @FindBy(css = "ul.ym-menu_type_main")
    @ElementDescription("Боковое меню")
    lateinit var mainMenu: WebElementEx

    @FindBy(css = "div.header2__main span.user__name")
    @ElementDescription("Меню пользователя")
    lateinit var userMenu: WebElementEx

    @FindBy(css = "div.popup__content a.user__logout")
    @ElementDescription("Пункт меню Выход")
    lateinit var logOutMenuItem: WebElementEx

    fun findUserName(login: String) : WebElement {
        val firstLetter = login.substring(0, 1)
        val otherLetters = login.substring(1)
        return driver.findElement(By.xpath("//span[contains(text(), '$otherLetters')]/span[text()='$firstLetter']"))
    }
}