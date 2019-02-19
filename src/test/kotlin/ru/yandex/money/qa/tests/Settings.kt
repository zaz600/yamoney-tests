package ru.yandex.money.qa.tests


val PASSPORT_URL = System.getenv("PASSPORT_URL") ?: "https://passport.yandex.ru"
val YM_URL = System.getenv("YM_URL") ?: "https://money.yandex.ru"
val HEADLESS_MODE = System.getenv().containsKey("HEADLESS")

