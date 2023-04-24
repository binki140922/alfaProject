package mobilesteps;

import com.codeborne.selenide.Selenide;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;

public class MobileSteps {

    @Step("Нажать кнопку Зайти")
    public MobileSteps clickButtonLogin() {
        Selenide.$(AppiumBy.id("ru.alfabank.mobile.android:id/button_text")).click();
        return this;
    }

    @Step("Вводим НТ")
    public MobileSteps setPhoneNumber(String phoneNumber) {
        Selenide.$(AppiumBy.id("ru.alfabank.mobile.android:id/text_field_input")).sendKeys(phoneNumber);
        return this;
    }

    @Step("Проверяем текущий лейаут")
    public MobileSteps checkLayout(String checkText) {
        Selenide.$(AppiumBy.id("ru.alfabank.mobile.android:id/text_view_component_title_view"))
                .shouldBe(text(checkText));
        return this;
    }

    @Step("Кликнуть по блоку карты")
    public MobileSteps clickBlockCard() {
        Selenide.$(AppiumBy.id("ru.alfabank.mobile.android:id/ad_card_view")).click();
        return this;
    }

    @Step("Кликнуть по кнопке в блоке карты")
    public MobileSteps clickButtonBlockCard() {
        Selenide.$(AppiumBy.id("ru.alfabank.mobile.android:id/button_text")).click();
        return this;
    }
}