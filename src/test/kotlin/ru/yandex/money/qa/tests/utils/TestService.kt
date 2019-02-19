package ru.yandex.money.qa.tests.utils

class TestService {

    /**
     * Возвращает данные пользователя для авторизации.
     * В реальной ситуации может брать информацию извне или создавать пользователя
     * при помощи тестовых утилит в тестовом окружении
     */
    fun getUser(): UserInfo {
        val login = System.getenv("TEST_USER")
        val password = System.getenv("TEST_PASSWORD")
        return UserInfo(login = login, password = password)
    }
}