package tests.web;

import basetest.BaseTestWeb;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import jdk.jfr.Label;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pages.MortgageCalculatorPage;

@Tag("front")
@Epic("web")
@Label("front")
@DisplayName("Альфабанк тесты ипотечного калькулятора")
public class MortgageCalculatorTest extends BaseTestWeb {

    private final MortgageCalculatorPage mortgageCalculatorPage = new MortgageCalculatorPage();

    @CsvSource({
            "Срок кредитования, 0, 3",
            "Срок кредитования, 50, 17",
            "Срок кредитования, 100, 30",
            "Стоимость недвижимости, 0, 750_000",
            "Стоимость недвижимости, 50, 44_130_000",
            "Стоимость недвижимости, 100, 87_500_000",
            "Первоначальный взнос, 0, 750_000",
            "Первоначальный взнос, 50, 2_575_000",
            "Первоначальный взнос, 100, 4_400_000"
    })
    @ParameterizedTest
    @Tag("functional")
    @Story("Проверка ипотечного калькулятора")
    @Feature("Позитивные сценарии")
    @DisplayName("Проверка слайдера ипотечного калькулятора")
    public void checkSlidersMortgageCalculator(String text, int percent, int value) {
        mortgageCalculatorPage.openMortgageCalculatorPage()
                .findElementByText(text)
                .moveSliderByPercentage(percent)
                .checkSliderValue(value);
    }

    @CsvSource(value = {
            "Вторичка; 11,79%",
            "ИТ ипотека — от 4,8%; 4,8%",
            "Семейная ипотека; 5,5%",
            "Новостройка; 11,99%",
            "Господдержка; 7,8%",
            "Дальневосточная ипотека — 2%; 2%"
    },
            delimiter = ';'
    )
    @ParameterizedTest
    @Tag("functional")
    @Story("Проверка ипотечного калькулятора")
    @Feature("Позитивные сценарии")
    @DisplayName("")
    public void checkProgramList(String program, String rate) {

        mortgageCalculatorPage.openMortgageCalculatorPage()
                .findAndSelectMortgageProgram(program)
                .checkMortgageRate(rate);
    }
}