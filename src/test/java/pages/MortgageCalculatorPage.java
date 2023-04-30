package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static basetest.BaseTestWeb.WEB_CONFIG;
import static org.assertj.core.api.Assertions.assertThat;

public class MortgageCalculatorPage extends HelperPage {

    private SelenideElement rootElement;
    private SelenideElement element;

    @Step("Открываем главную страницу")
    public MortgageCalculatorPage openMortgageCalculatorPage() {

        openPage(WEB_CONFIG.getBaseUrl() + "get-money/mortgage/ipotechnyj-kalkulyator/");

        return this;
    }

    @Step("Найти элемент с текстом {0}")
    public MortgageCalculatorPage findElementByText(String text) {

        rootElement = Selenide.$(Selectors.byAttribute("aria-label", text));
        element = rootElement.parent().parent().parent().parent().$(".noUi-base");

        return this;
    }

    @Step("Установить слайдер на {0} процентов")
    public MortgageCalculatorPage moveSliderByPercentage(int percent) {

        int width = element.getSize().width;

        String tmpString = element.$(".noUi-origin").getAttribute("style");
        assertThat(tmpString).isNotNull();

        int position = tmpString.indexOf('(');
        int correct = Integer.parseInt(tmpString.substring(position + 2, position + 4)) - 1;

        element.$(".noUi-touch-area").click();

        //Установить слайдер в крайнее левое положение, если он там не находится
        if (correct != 9) {
            Selenide.actions().dragAndDropBy(element.$(".noUi-touch-area"), -(width * (100 - correct) / 100), 0)
                    .perform();
        } else {
            Selenide.actions().dragAndDropBy(element.$(".noUi-touch-area"), 1, 0)
                    .perform();
            Selenide.actions().dragAndDropBy(element.$(".noUi-touch-area"), -1, 0)
                    .perform();
        }
        Selenide.actions().dragAndDropBy(element.$(".noUi-touch-area"), (width * percent / 100), 0)
                .perform();

        return this;
    }

    @Step("Выбрать ипотечную программу - {0}")
    public MortgageCalculatorPage findAndSelectMortgageProgram(String nameProgram) {

        Selenide.$(Selectors.byAttribute("data-test-id", "arrow")).click();
        Selenide.$(Selectors.byAttribute("data-test-id", "autocomplete"))
                .$(Selectors.byText(nameProgram)).click();

        return this;
    }

    @Step("Сверяем полученное значение слайдера с ожидаемым")
    public void checkSliderValue(int expectedValue) {

        Integer actualValue = Integer.parseInt(rootElement.getAttribute("value").replace(" ", ""));
        assertThat(actualValue).isEqualTo(expectedValue);
    }

    @Step("Проверяем ипотечную ставку на соответствие программе")
    public void checkMortgageRate(String expectedRate) {

        Selenide.$(Selectors.byAttribute("data-test-id", "interest-rate"))
                .shouldBe(Condition.text(expectedRate));
    }
}