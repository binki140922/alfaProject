package settings.basetest;

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
    public static void init() throws Exception {

        TestConfig testConfig = new TestConfig();
        testConfig.init();
    }

    @BeforeEach
    void preparation() {

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        if (TestConfig.SYS.equals("mobile")) {
            Selenide.open();
        } else if (TestConfig.SYS.equals("web")) {
            Selenide.clearBrowserCookies();
        }
    }

    @AfterEach
    void completion() {

        if (TestConfig.ENV.equals("browserstack")) {
            Attach.sessionId = Selenide.sessionId().toString();
        } else {
            Attach.screenshotAs("screenShot");
        }

        if (TestConfig.SYS.equals("web")) {
            Attach.pageSource();
            Attach.browserConsoleLogs();
        }

        if (TestConfig.SYS.equals("mobile")) {
            Selenide.closeWebDriver();
        }

        if (TestConfig.ENV.equals("selenide")
                || TestConfig.ENV.equals("browserstack")) {
            Attach.addVideo();
        }
    }
}
