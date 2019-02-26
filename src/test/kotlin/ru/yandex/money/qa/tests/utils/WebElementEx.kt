package ru.yandex.money.qa.tests.utils

import org.openqa.selenium.WebElement

class WebElementEx(private val el: WebElement, private val desc: String) : WebElement by el {

    override fun click() {
        AllureHelper.makeStep("Клик на элемент '$desc'") {
            el.click()
        }
    }

    override fun sendKeys(vararg keysToSend: CharSequence?) {
        // TODO если их много?
        // TODO параметры лучше спрятать внутрь allure степа
        val text = keysToSend.first().toString()
        AllureHelper.makeStep("Заполнение значения поля '$desc' = '$text'") {
            el.sendKeys(*keysToSend)
        }
    }
}
