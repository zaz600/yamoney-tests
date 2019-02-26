package ru.yandex.money.qa.tests.utils

import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory
import java.lang.reflect.Field

class ExtendedFieldDecorator(factory: ElementLocatorFactory) : DefaultFieldDecorator(factory) {

    override fun decorate(loader: ClassLoader, field: Field): Any? {
        if (field.type == WebElementEx::class.java) {
            val locator = factory.createLocator(field) ?: return null
            val desc = field.getAnnotation(ElementDescription::class.java)?.desc ?: field.name
            return WebElementEx(
                proxyForLocator(loader, locator),
                desc = desc
            )
        }
        return super.decorate(loader, field)
    }
}
