package tests.web;

import basetest.BaseTestWeb;
import data.HeaderDLData;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import jdk.jfr.Label;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import pages.MainPage;

@Tag("front")
@Epic("web")
@Label("front")
@DisplayName("Альфабанк тесты Главной страницы")
public class HedersTest extends BaseTestWeb {

    private final MainPage mainPage = new MainPage();

    @ParameterizedTest
    @Tags(value = {@Tag("navigation"), @Tag("sanity")})
    @Story("Проверка переходов")
    @Feature("Позитивный сценарии")
    @EnumSource(value = HeaderDLData.class, mode = EnumSource.Mode.INCLUDE)
    @DisplayName("Проверка редиректа хедеров главной страницы")
    public void checkHeaderDesktopLayout(HeaderDLData data) {

        mainPage.openMainPage()
                .clickHeader(data.getLinkName())
                .checkOpenUrl(data.getLinkUrl());
    }
}