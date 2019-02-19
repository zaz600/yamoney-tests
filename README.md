# Пример теста на авторизацию в ЯД

## Предварительная настройка
- Должен быть установлен JDK8
- Должен быть установлен браузер Chrome 70+
- Проверено на Ubuntu (16+)
- В ЯД должен быть зарегистрирован пользователь с отключенной двухфакторной аутентификацией

## Установка  и запуск

  mkdir ~/src
  
  cd ~/src
  
  git clone https://github.com/zaz600/yamoney-tests
  
  cd yamoney-tests/
  
  TEST_USER=user TEST_PASSWORD=password ./gradlew clean test

## Настройка имени пользователя
Имя пользователя, с которым будет выполняться тест, можно изменить через переменные среды окружения.
  
  TEST_USER=user
  
  TEST_PASSWORD=password

## Настройка хостов  
Через переменные окружения можно изменить адреса хостов, на которых будут запускаться тесты. Например:

  YM_HOST=https://demomoney.yandex.ru PASSPORT_URL=https://demopassport.yandex.ru TEST_USER=user TEST_PASSWORD=password ./gradlew clean test

По-умолчанию, если переменные среды не заданы, используются значения: 
- https://money.yandex.ru 
- https://passport.yandex.ru

## Allure
Чтобы сформировать и открыть allure отчет в браузере по умолчанию, можно воспользоваться командой allureServe.
Если отчет необходимо только сформировать, то можно воспользоваться командой allureReport. 
В этом случает отчет будет создан в папке build/allure-report

  TEST_USER=user TEST_PASSWORD=password ./gradlew clean test && ./gradlew allureServe


## Импорт в IntelliJ Idea
- File - New - Project from Existing Sources 
- Указать путь к ~/src/yamoney-tests/
- Import project from external model - Gradle
- Use gradle 'wrapper' task configuration
- Run - Edit Configurations - + - Gradle
- Gradle Project = yamoney-tests
- Tasks = clean test allureReport
- Environment variables:
  * TEST_USER=user
  * TEST_PASSWORD=password
  
## Решение проблем
- Для запуска из командной строки в MacOS у терминала должен быть полный доступ к диску. Решение [тут](http://osxdaily.com/2018/10/09/fix-operation-not-permitted-terminal-error-macos/) 