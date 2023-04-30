package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import pages.components.HeaderDesktopLayout;

import static basetest.BaseTestWeb.WEB_CONFIG;

public class MainPage extends HelperPage {

    HeaderDesktopLayout headerDesktopLayout = new HeaderDesktopLayout();

    @Step("Открываем главную страницу")
    public MainPage openMainPage() {
        openPage(WEB_CONFIG.getBaseUrl());
        return this;
    }

    @Step("Нажимаем на хедер - {0}")
    public MainPage clickHeader(String headerName) {
        headerDesktopLayout.clickHeader(headerName);
        return this;
    }

    @Step("Нажимаем на - {0}")
    public MainPage findAndClickText(String nameElement) {
        Selenide.$(Selectors.byText(nameElement)).click();
        return this;
    }

    @Step("Кликнуть по - {}")
    public MainPage findAndClickAttributeElement(String nameStep, String nameAttribute, String valueAttribute) {
        Selenide.$(Selectors.byAttribute(nameAttribute, valueAttribute)).click();
        return this;
    }

    @Step("Ввести текст - {0} и нажать Enter ")
    public MainPage setText(String text, String nameAttribute, String valueAttribute) {
        Selenide.$(Selectors.byAttribute(nameAttribute, valueAttribute)).setValue(text).pressEnter();
        return this;
    }

    @Step("Проверяем верность переадресации")
    public void checkOpenUrl(String fullUrl) {
        checkUrlPage(WEB_CONFIG.getBaseUrl() + fullUrl);
    }

    @Step("Проверяем видимость элемента")
    public void checkVisibleElement(String nameElement) {
        Selenide.$(Selectors.byText(nameElement)).shouldBe(Condition.visible);
    }

    @Step("Проверяем наличие текста {0}")
    public void checkOneResultFound(String text) {
        Selenide.$(Selectors.byText("Поисковая технология")).shouldBe(Condition.text(text));
    }
}
