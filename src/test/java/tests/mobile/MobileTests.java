package tests.mobile;

import com.codeborne.selenide.Selenide;
import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import settings.baseTest.BaseTest;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;

@Tag("mobile")
public class MobileTests extends BaseTest {

    @Tag("android")
    @Test
    public void firstInstallOpenTest() {
        Selenide.$(AppiumBy.id("ru.alfabank.mobile.android:id/button_text")).click();
        Selenide.$(AppiumBy.id("ru.alfabank.mobile.android:id/text_view_component_title_view"))
                .shouldBe(text("Привет! Введите телефон и заходите скорее \uD83D\uDC4C"));
    }

    @Tag("android")
    @Test
    public void numberSetTest() {
        Selenide.$(AppiumBy.id("ru.alfabank.mobile.android:id/text_field_input")).sendKeys("9999999999");
        Selenide.$(AppiumBy.id("ru.alfabank.mobile.android:id/button_container")).click();
        Selenide.$(AppiumBy.id("ru.alfabank.mobile.android:id/text_view_component_title_view")).shouldBe(text("Вот что обычно берут новички \uD83D\uDC47"));
    }

    @Tag("android")
    @Test
    public void clickToCardFormTest() {
        Selenide.$(AppiumBy.id("ru.alfabank.mobile.android:id/text_field_input")).sendKeys("9999999999");
        Selenide.$(AppiumBy.id("ru.alfabank.mobile.android:id/button_container")).click();
        Selenide.$(AppiumBy.id("ru.alfabank.mobile.android:id/ad_card_view")).click();
        Selenide.$(AppiumBy.id("ru.alfabank.mobile.android:id/text_view_component_title_view"))
                .shouldBe(text("Бесплатная дебетовая Альфа‑Карта"));
    }

    @Tags(value = {@Tag("android"), @Tag("browserstack")})
    @Test
    public void clickToCardFormButtonTest() {
        Selenide.$(AppiumBy.id("ru.alfabank.mobile.android:id/text_field_input")).sendKeys("9999999999");
        Selenide.$(AppiumBy.id("ru.alfabank.mobile.android:id/button_container")).click();
        Selenide.$(AppiumBy.id("ru.alfabank.mobile.android:id/button_text")).click();
        Selenide.$(AppiumBy.id("ru.alfabank.mobile.android:id/text_view_component_title_view"))
                .shouldBe(text("Представьтесь, пожалуйста! Пишите как в паспорте ✍️"));
    }
}