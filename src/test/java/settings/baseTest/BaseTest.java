package settings.baseTest;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import settings.configs.TestConfig;
import settings.helpers.Attach;

public class BaseTest {


    @BeforeAll
    public static void initMethod() throws Exception {
        TestConfig testConfig = new TestConfig();
        if (System.getProperty("sys").equals("web")) {
            Selenide.open();
        }

        if (System.getProperty("sys").equals("mobile")) {
            Configuration.browserSize = null;
        }
    }

    @BeforeEach
    void addListener() {
        Selenide.open();
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @BeforeEach
    void deleteCockshies() {
        if (System.getProperty("sys").equals("web")) {
            Selenide.clearBrowserCookies();
        }
    }

    @AfterEach
    void closeSession() {

        Attach.screenshotAs("screenShot");
        if (System.getProperty("sys").equals("web")) {
            Attach.pageSource();
            Attach.browserConsoleLogs();
        }
        if (System.getProperty("sys").equals("mobile")) {
            Selenide.closeWebDriver();
        }
        if (System.getProperty("env").equals("local")) {
            Attach.addVideo();
        }
    }
}
