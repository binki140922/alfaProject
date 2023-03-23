package tests.web;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import jdk.jfr.Label;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import settings.basetest.BaseTest;
import settings.data.HeaderDLData;
import settings.pages.MainPage;

@Tags(value = {@Tag("web"), @Tag("front")})
@Epic("web")
@Label("front")
@DisplayName("Альфабанк тесты Главной страницы")
public class MainPageWebTest extends BaseTest {

    private final MainPage mainPage = new MainPage();

    @ParameterizedTest(name = "{index}. Проверка хедера - {0}")
    @Tags(value = {@Tag("regress"), @Tag("sanity")})
    @Story("Проверка переходов")
    @Feature("Позитивный сценарии")
    @DisplayName("Проверка хедеров")
    @EnumSource(value = HeaderDLData.class, mode = EnumSource.Mode.INCLUDE)
    public void checkHeaderDesktopLayout(HeaderDLData data) {

        mainPage.openMainPage()
                .clickHeader(data.getLinkName())
                .checkOpenUrl(data.getLinkUrl());
    }

    @Test
    @Tag("regress")
    @Story("Проверка переходов")
    @Feature("Негативный сценарии")
    @DisplayName("Проверка кнопки Альфа-Онлайн")
    public void checkButtonAlfaOnline() {

        mainPage.openMainPage()
                .findAndClickText("Альфа-Онлайн")
                .checkVisibleElement("войти в Альфа-Онлайн?");
    }
    
    @Test
    @Tag("regress")
    @Story("Проверка переходов")
    @Feature("Негативный сценарии")
    @DisplayName("Проверка кнопки СТАТЬ КЛИЕНТОМ")
    public void mortgageLayoutTest() {

        mainPage.openMainPage()
                .findAndClickText("СТАТЬ КЛИЕНТОМ")
                .findAttributElement("data-test-id", "layout")
                .findAndClickText("Ипотека")
                .checkVisibleElement("Узнайте лимит по ипотеке");
    }

    @Test
    @Tag("regress")
    @Story("Проверка переходов")
    @Feature("Негативный сценарии")
    @DisplayName("Проверка поисковой выдачи по запросу - ипотека")
    public void mortgageFindTest() {

        mainPage.openMainPage()
                .findAttributElement("placeholder", "Я ищу")
                .setText("ипотека")
                .checkOneResultFaind(" — Оформить заявку онлайн на ипотечный кредит...");
    }
    
    @Test
    @Tags(value = {@Tag("regress"), @Tag("smoke")})
    @Story("Проверка переходов")
    @Feature("Негативный сценарии")
    @DisplayName("Проверка перехода через логотип")
    public void clickLogoTest() {

        mainPage.openMainPage()
                .findAndClickAttributElement("data-test-id", "Main-Header-Main-DesktopLogo")
                .checkOpenUrl("");
    }
}