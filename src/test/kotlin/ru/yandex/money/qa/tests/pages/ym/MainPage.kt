package ru.yandex.money.qa.tests.pages.ym

import io.qameta.allure.Step
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory
import ru.yandex.money.qa.tests.YM_URL
import ru.yandex.money.qa.tests.pages.AbstractPage

class MainPage(driver: WebDriver) : AbstractPage(driver) {
    override val pageUrl: String = "/actions"
    override val homeUrl: String = YM_URL

    init {
        PageFactory.initElements(driver, this)
    }

    @FindBy(css = "ul.ym-menu_type_main")
    lateinit var mainMenu: WebElement

    @FindBy(css = "div.header2__main span.user__name")
    private lateinit var userMenu: WebElement

    @FindBy(css = "div.popup__content a.user__logout")
    private lateinit var logOutMenuItem: WebElement

    @Step("Нажатие на имя пользователя")
    fun userMenuClick() = userMenu.click()

    @Step("Нажатие на пункт меню Выход")
    fun logOutMenuItemClick() = logOutMenuItem.click()

    fun findUserName(login: String) : WebElement {
        val firstLetter = login.substring(0, 1)
        val otherLetters = login.substring(1)
        return driver.findElement(By.xpath("//span[contains(text(), '$otherLetters')]/span[text()='$firstLetter']"))
    }
}