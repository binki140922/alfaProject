package tests.mobile;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import jdk.jfr.Label;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import basetest.BaseTestMobile;
import mobilesteps.MobileSteps;

@Tag("mobile")
@Epic("mobile")
@Label("android")
@DisplayName("Альфабанк тесты android приложения")
public class MobileTests extends BaseTestMobile {

    private final MobileSteps mobileSteps = new MobileSteps();

    @Test
    @Tags(value = {@Tag("functional"), @Tag("smoke")})
    @Story("Не зарегистрированный пользователь")
    @Feature("Негативный сценарии")
    @DisplayName("Ввод пустого НТ")
    public void firstInstallOpenTest() {

        mobileSteps.clickButtonLogin()
                .checkLayout("Привет! Введите телефон и заходите скорее \uD83D\uDC4C");
    }

    @Test
    @Tags(value = {@Tag("functional"), @Tag("sanity")})
    @Story("Не зарегистрированный пользователь")
    @Feature("Позитивный сценарии")
    @DisplayName("Ввод нового НТ")
    public void numberSetTest() {

        mobileSteps.setPhoneNumber("9009009091")
                .clickButtonLogin()
                .checkLayout("Вот что обычно берут новички \uD83D\uDC47");
    }

    @Test
    @Tags(value = {@Tag("functional"), @Tag("sanity")})
    @Story("Не зарегистрированный пользователь")
    @Feature("Позитивный сценарии")
    @DisplayName("Переход на описание карты")
    public void clickToCardFormTest() {

        mobileSteps.setPhoneNumber("9009009091")
                .clickButtonLogin()
                .clickBlockCard()
                .checkLayout("Бесплатная дебетовая Альфа‑Карта");
    }

    @Test
    @Tags(value = {@Tag("functional"), @Tag("sanity")})
    @Story("Не зарегистрированный пользователь")
    @Feature("Позитивный сценарии")
    @DisplayName("Переход на оформление карты")
    public void clickToCardFormButtonTest() {

        mobileSteps.setPhoneNumber("9009009091")
                .clickButtonLogin()
                .clickButtonBlockCard()
                .checkLayout("Представьтесь, пожалуйста! Пишите как в паспорте ✍️");
    }
}