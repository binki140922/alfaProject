<h2 align="center"> Проект по автоматизации тестирования для Альфа-Банк</h2>
<p  align="center">
<a href="https://alfabank.ru/"><img src="./images/logos/alfabank.png" width="950"></a>
</p>

## <a name="Содержание">Содержание</a>
+ [Описание](#Описание)
+ [Стек технологий](#стек-технологий)
+ [Запуск тестов](#Запуск-тестов)
+ [Сборка в Jenkins](#jenkins-job)
+ [Allure отчеты:](#Allure-отчеты)
  + [Allure report:](#report)
  + [Allure test ops:](#0TestOps)
+ [Уведомление в Telegram](#telegram-уведомления)
+ [Интеграция с Jira](#Jira-интеграция)
+ [Видео прохождения тестов](#selenoid-видеозапись-тестов)

## <a name="Описание">Описание</a>
Тестовый проект состоит из веб-тестов (UI), тестов API и мобильных тестов (Android).\
Краткий список интересных фактов о проекте:
- [x] `Page Object` проектирование
- [x] Параметризованные тесты
- [x] Различные файлы конфигурации для запуска теста в зависимости от параметров сборки
- [x] Конфигурация с библиотекой `Owner`
- [x] Использование `Lombok` для моделей в API тестах
- [x] Использование request/response спецификаций для API тестов
- [x] Custom Allure listener для API requests/responses логов
- [x] Интеграция с `Allure TestOps`
- [x] Автотесты как тестовая документация
- [x] Интеграция с `Jira`


## <img src="/images/logos/utils.svg" width="25" height="25"  alt="utils"/><a name="Стек технологий">Стек технологий</a>
<p align="center">
<a href="https://www.jetbrains.com/idea/"><img src="/images/logos/IDEA.svg" width="50" height="50"  alt="IDEA"/></a>
<a href="https://www.java.com/"><img src="/images/logos/Java.svg" width="50" height="50"  alt="Java"/></a>
<a href="https://github.com/"><img src="/images/logos/Github.svg" width="50" height="50"  alt="Github"/></a>
<a href="https://junit.org/junit5/"><img src="/images/logos/JUnit5.svg" width="50" height="50"  alt="JUnit5"/></a>
<a href="https://gradle.org/"><img src="/images/logos/Gradle.svg" width="50" height="50"  alt="Gradle"/></a>
<a href="https://appium.io/"><img src="/images/logos/appium.svg" width="50" height="50"  alt="appium"/></a>
<a href="https://selenide.org/"><img src="/images/logos/rest-assured-logo.svg" width="50" height="50"  alt="rest-assured"/></a>
<a href="https://selenide.org/"><img src="/images/logos/Selenide.svg" width="50" height="50"  alt="Selenide"/></a>
<a href="https://www.browserstack.com//"><img src="/images/logos/browserstack.svg" width="50" height="50"  alt="browserstack"/></a>
<a href="https://aerokube.com/selenoid/"><img src="/images/logos/Selenoid.svg" width="50" height="50"  alt="Selenoid"/></a>
<a href="https://github.com/allure-framework/allure2"><img src="/images/logos/Allure.svg" width="50" height="50"  alt="Allure"/></a>
<a href="https://www.jenkins.io/"><img src="/images/logos/Jenkins.svg" width="50" height="50"  alt="Jenkins"/></a>
<a href="https://qameta.io/"><img src="/images/logos/Allure_TO.svg" width="50" height="50"  alt="Allure TestOps"/></a>
<a href="https://www.atlassian.com/software/jira/"><img src="/images/logos/Jira.svg" width="50" height="50"  alt="Jira"/></a>
<a href="https://web.telegram.org/"><img src="/images/logos/Telegram.svg" width="50" height="50"  alt="Telegram"/></a>
</p>

## <img src="/images/logos/terminal.png" width="25" height="25"  alt="terminal"/><a name="Запуск тестов">Запуск тестов</a>
#### Про конфигурации
Файлы конфигурации должны находиться по пути:
```
src/test/resources
```
Креды вынесены в отдельный конфиг файл - `credential.properties`\
Ожидаемые конфиги прописаны у соответсвующих команд.

#### Команды запуска тестов на различных платформах
Mobile тесты (android):\
emulationTest - используется эмулятор, `local.properties`\
realTest - используется реальное устройство, `real.properties`\
browserstackTest - используется сервис browserstack, `browserstack.properties`
```
gradle clean emulationTest
gradle clean realTest
gradle clean browserstackTest
```
Веб тесты:\
localTest - используется эмулятор, `local.properties`\
selenideTest - используется реальное устройство, `selenide.properties`
```
gradle clean localTest
gradle clean selenideTest
```
API тесты:\
apiTest - используется эмулятор, `api.properties`
```
gradle clean apiTest
```

[Вернуться к оглавлению](#Содержание)

## <img src="/images/logos/Jenkins.svg" width="25" height="25"  alt="Jenkins"/><a name="Jenkins">Jenkins</a><a target="_blank" href="https://jenkins.autotests.cloud/job/golem272_alfa/"> job</a>
Параметры сборки в Jenkins:

- <code>TYPETEST</code> выбор типа тестов, из CI доступны:
  - browserstackTest
  - selenideTest
  - apiTest

#### Доп параметры для команды - selenideTest
- <code>BROWSER</code> браузер, по умолчанию chrome
- <code>VERSION</code> версия браузера, по умолчанию 100.0
- <code>RESOLUTION</code> размер окна браузера, по умолчанию 1280x720
- <code>REMOTE_URL</code> адрес удаленного сервера
- <code>REMOTE_URL_VIDEO</code> адрес удаленного сервера

[Вернуться к оглавлению](#Содержание)

## <img src="/images/logos/report.png" width="25" height="25"  alt="report"/>Allure отчеты:
### <img src="/images/logos/Allure.svg" width="25" height="25"  alt="Allure"/>Отчет в Allure</a> <a target="_blank" href="https://jenkins.autotests.cloud/job/golem272_alfa/10/"> report</a>
#### Основной отчет
<p align="center">
<img title="Allure Overview Dashboard" src="/images/screens/overviewReport.jpg">
</p>

#### Тесты
<p align="center">
<img title="Allure Suites" src="/images/screens/testsReport.jpg">
</p>

[Вернуться к оглавлению](#Содержание)

### <img src="/images/logos/Allure_TO.svg" width="25" height="25"  alt="Allure TestOps"/>Отчет в Allure</a> <a target="_blank" href="https://allure.autotests.cloud/jobrun/20210"> TestOps</a>
#### Основной отчет
<p align="center">
<img title="Allure Overview Dashboard" src="/images/screens/overviewOps.jpg">
</p>

#### Тесты
<p align="center">
<img title="Allure Suites" src="/images/screens/testsOps.jpg">
</p>

#### Пример ручных тестов
<p align="center">
  <img title="Allure TestOps manual tests" src="/images/screens/manual.jpg">
</p>

[Вернуться к оглавлению](#Содержание)

## <img src="/images/logos/Telegram.svg" width="25" height="25"  alt="Telegram"/>Telegram уведомления</a>
После завершения сборки специальный бот, созданный в Telegram, автоматически обрабатывает и отправляет сообщение с отчетом о прогоне.
<p align="center">
<img title="Allure Overview Dashboard" src="/images/screens/telegram.jpg">
</p>

[Вернуться к оглавлению](#Содержание)

## <img src="/images/logos/Jira.svg" width="25" height="25" alt="Jira"/>Jira интеграция</a>
<p align="center">
  <img title="Allure TestOps overview" src="/images/screens/jira.jpg">
</p>

[Вернуться к оглавлению](#Содержание)

## <img src="/images/logos/Selenoid.svg" width="25" height="25" alt="Selenoid"/>Selenoid видеозапись тестов</a>
### Видео запись прохождения тестов
<p align="center">
  <img title="Selenoid video for test4" src="/images/gif/videoWeb.gif">
  <img title="Selenoid video for test4" src="/images/gif/vidoMobile.gif">
</p>

[Вернуться к оглавлению](#Содержание)