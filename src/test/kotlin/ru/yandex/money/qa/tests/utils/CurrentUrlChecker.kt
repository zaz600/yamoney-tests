package ru.yandex.money.qa.tests.utils

import io.qameta.allure.Step
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait


/**
 * Сахар для проверки текущего URL-а
 */
class CurrentUrlChecker(val driver: WebDriver) {
    @Step("Проверка, что URL браузера содержит подстроку")
    fun shouldContains(partUrl: String, timeoutSec: Long = 30) {
        WebDriverWait(driver, timeoutSec).until(ExpectedConditions.urlContains(partUrl))
    }

    @Step("Проверка, что URL браузера оканчивается подстрокой")
    fun shouldEndsWith(partUrl: String, timeoutSec: Long = 30) {
        WebDriverWait(driver, timeoutSec).until(ExpectedConditions.urlMatches(".*?$partUrl\$"))
    }

    @Step("Проверка, что URL браузера равен строке")
    fun shouldBeEqualsTo(url: String, timeoutSec: Long = 30) {
        WebDriverWait(driver, timeoutSec).until(ExpectedConditions.urlToBe(url))
    }
}
