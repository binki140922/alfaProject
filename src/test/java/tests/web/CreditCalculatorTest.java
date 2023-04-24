package tests.web;

import basetest.BaseTestWeb;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import jdk.jfr.Label;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.CreditCalculatorPage;

@Tag("front")
@Epic("web")
@Label("front")
@DisplayName("Альфабанк тесты кредитного калькулятора")
public class CreditCalculatorTest extends BaseTestWeb {

    private final CreditCalculatorPage creditCalculatorPage = new CreditCalculatorPage();

    @Test
    @Tag("functional")
    @Story("Проверка кредитного калькулятора")
    @Feature("Позитивные сценарии")
    @DisplayName("Проверка кнопки Альфа-Онлайн")
    public void checkButtonAlfaOnline() {

        creditCalculatorPage.openCreditCalculatorPage();

        String x = Selenide.$("#estateCost").parent().$("div").$("div").getAttribute("class");
                //.$("div").setValue("0");
        Selenide.$("#estateCost").click();
        Selenide.$("#estateCost").shouldBe(Condition.value("750 000"));
        Selenide.sleep(3000);
    }

    @Test
    @Tag("functional")
    @Story("Проверка кредитного калькулятора")
    @Feature("Позитивные сценарии")
    @DisplayName("Проверка кнопки Альфа-Онлайн")
    public void checkButtonAlfaOnline2() {

        creditCalculatorPage.openCreditCalculatorPage();

        Selenide.$("#creditAmount").clear();
        Selenide.$("#creditAmount").shouldBe(Condition.value("50 000"));
    }
}