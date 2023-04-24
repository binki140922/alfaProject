package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import pages.components.HeaderDesktopLayout;

import static basetest.BaseTestWeb.WEB_CONFIG;

public class CreditCalculatorPage extends HelperPages {

    @Step("Открываем главную страницу")
    public CreditCalculatorPage openCreditCalculatorPage() {
        openPage(WEB_CONFIG.getBaseUrl() + "get-money/mortgage/ipotechnyj-kalkulyator/");
        return this;
    }


}
