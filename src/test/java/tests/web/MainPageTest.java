package tests.web;

import basetest.BaseTestWeb;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import jdk.jfr.Label;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import pages.MainPage;

@Tag("front")
@Epic("web")
@Label("front")
@DisplayName("Альфабанк тесты Главной страницы")
public class MainPageTest extends BaseTestWeb {

    private final MainPage mainPage = new MainPage();

    @Test
    @Tag("navigation")
    @Story("Проверка переходов")
    @Feature("Позитивные сценарии")
    @DisplayName("Проверка кнопки Альфа-Онлайн")
    public void checkButtonAlfaOnline() {

        mainPage.openMainPage()
                .findAndClickText("Альфа-Онлайн")
                .checkVisibleElement("в Альфа-Онлайн");
    }

    @Test
    @Tag("navigation")
    @Story("Проверка переходов")
    @Feature("Позитивные сценарии")
    @DisplayName("Проверка кнопки СТАТЬ КЛИЕНТОМ")
    public void mortgageLayoutTest() {

        mainPage.openMainPage()
                .findAndClickText("СТАТЬ КЛИЕНТОМ")
                .findAndClickText("Ипотека")
                .checkVisibleElement("Узнайте лимит по ипотеке");
    }

    @Test
    @Tag("navigation")
    @Story("Проверка переходов")
    @Feature("Позитивные сценарии")
    @DisplayName("Проверка поисковой выдачи по запросу - ипотека")
    public void mortgageFindTest() {

        mainPage.openMainPage()
                .setText("ипотека", "placeholder", "Я ищу")
                .checkOneResultFound("Поисковая технология");
    }

    @Test
    @Tags(value = {@Tag("navigation"), @Tag("smoke")})
    @Story("Проверка переходов")
    @Feature("Позитивные сценарии")
    @DisplayName("Проверка перехода через логотип")
    public void clickLogoTest() {

        mainPage.openMainPage()
                .findAndClickAttributeElement("логотипу", "data-test-id", "Main-Header-Main-DesktopLogo")
                .checkOpenUrl("");
    }
}