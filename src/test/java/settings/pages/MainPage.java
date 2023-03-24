package settings.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import settings.basetest.BaseTest;
import settings.configs.TestConfig;
import settings.pages.components.HeaderDesktopLayout;
import util.TestUtil;

public class MainPage extends BaseTest {

    HeaderDesktopLayout headerDesktopLayout = new HeaderDesktopLayout();

    private SelenideElement tmpSelenideElement;

    @Step("Открываем главную страницу")
    public MainPage openMainPage() {
        TestUtil.openPage(TestConfig.getBaseUrlWeb());
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

    @Step("Выбрать - {0}")
    public MainPage findAttributElement(String nameStep, String nameAttribute, String valueAttribute) {
        tmpSelenideElement = Selenide.$(Selectors.byAttribute(nameAttribute, valueAttribute));
        return this;
    }

    @Step("Кликнуть по - {}")
    public MainPage findAndClickAttributElement(String nameStep ,String nameAttribute, String valueAttribute) {
        Selenide.$(Selectors.byAttribute(nameAttribute, valueAttribute)).click();
        return this;
    }

    @Step("Ввести текст - {0} и нажать Enter ")
    public MainPage setText(String text) {
        Selenide.$(tmpSelenideElement).setValue(text).pressEnter();
        return this;
    }

    @Step("Проверяем верность переадресации")
    public void checkOpenUrl(String fullUrl) {
        TestUtil.checkUrlPage(TestConfig.getBaseUrlWeb() + fullUrl);
    }

    @Step("Проверяем видимость элемента")
    public void checkVisibleElement(String nameElement) {
        Selenide.$(Selectors.byText(nameElement)).shouldBe(Condition.visible);
    }

    @Step("Проверяем наличие текста {0}")
    public void checkOneResultFaind(String text) {
        Selenide.$$(".b-serp-item__content").get(0).$(".b-serp-item__title-link")
                .shouldBe(Condition.text(text));
    }
}
