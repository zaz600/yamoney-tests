package ru.yandex.money.qa.tests

import io.qameta.allure.Epic
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import ru.yandex.money.qa.tests.pages.passport.PassportAuthPage
import ru.yandex.money.qa.tests.pages.ym.IndexPage
import ru.yandex.money.qa.tests.pages.ym.MainPage
import ru.yandex.money.qa.tests.specs.AbstractSeleniumTest
import ru.yandex.money.qa.tests.utils.TestService

@DisplayName("Авторизация")
@Epic("Авторизация")
class TestAuth : AbstractSeleniumTest() {
    override val driver = this.createWebDriver()

    private val indexPage = IndexPage(driver)
    private val passportAuthPage = PassportAuthPage(driver)
    private val mainPage = MainPage(driver)
    private val testService = TestService()

    @Test
    @Order(1)
    @DisplayName("Авторизация. Авторизация в кабинете Яндекс.Деньги")
    fun testAuth() {
        val user = testService.getUser()

        with(indexPage) {
            open()
            loginButton.click()
        }

        with(passportAuthPage) {
            currentUrl.shouldContains(passportAuthPage.fullUrl)
            fieldLogin.sendKeys(user.login)
            signInButton.click()
            fieldPassword.sendKeys(user.password)
            signInButton.click()
        }

        currentUrl.shouldContains(mainPage.serverUrl)

        "Отображается главное меню" {
            assertTrue(mainPage.mainMenu.isDisplayed)
        }
        "Отображается логин пользователя" {
            assertTrue(mainPage.findUserName(user.login).isDisplayed)
        }
    }

    @Test
    @Order(2)
    @DisplayName("Авторизация. Выход из личного кабинета через меню")
    fun testLogout() {

        // В этом тесте не выполняется авторизация в ЛК
        // Тест завязан на результат предыдущего.
        // Это экономит 30 сек в целом, но связывает тесты
        // и усложняет разбор в случае падения тестов.фы

        with(mainPage) {
            userMenu.click()
            logOutMenuItem.click()
        }
        currentUrl.shouldContains("/promo/money/apps")
    }
}