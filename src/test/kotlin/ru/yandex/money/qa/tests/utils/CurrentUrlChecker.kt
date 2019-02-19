package ru.yandex.money.qa.tests.utils

import io.qameta.allure.Step
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.WebDriverWait

/**
 * Сахар для проверки текущего URL-а
 */
class CurrentUrlChecker(val driver: WebDriver) {
    @Step("Проверка содержимого URLа браузера")
    private fun checkCurrentUrl(timeoutSec: Long = 30, checker: (String) -> Boolean) {
        WebDriverWait(driver, timeoutSec).until { checker(it.currentUrl) }
    }

    @Step("Проверка, что URL браузера содержит подстроку")
    fun shouldContains(partUrl: String, timeoutSec: Long = 30) {
        checkCurrentUrl(timeoutSec) { it.contains(partUrl) }
    }

    @Step("Проверка, что URL браузера оканчивается подстрокой")
    fun shouldEndsWith(partUrl: String, timeoutSec: Long = 30) {
        checkCurrentUrl(timeoutSec) { it.endsWith(partUrl) }
    }

    @Step("Проверка, что URL браузера равен строке")
    fun shouldBeEqualsTo(url: String, timeoutSec: Long = 30) {
        checkCurrentUrl(timeoutSec) { it == url }
    }
}
