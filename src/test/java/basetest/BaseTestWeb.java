package basetest;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import configs.DesktopConfig;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BaseTestWeb {

    public static final String ENV = System.getProperty("env");

    public static final DesktopConfig WEB_CONFIG = ConfigFactory.create(DesktopConfig.class, System.getProperties());

    @BeforeAll
    public static void init() {

        Configuration.baseUrl = WEB_CONFIG.getBaseUrl();
        Configuration.browser = WEB_CONFIG.getBrowser();
        Configuration.browserVersion = WEB_CONFIG.getBrowserVersion();
        Configuration.browserSize = WEB_CONFIG.getBrowserSize();

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);

        if (ENV.equals("selenide")) {
            Configuration.remote = "https://"
                    + WEB_CONFIG.getSelenideLogin()
                    + ":"
                    + WEB_CONFIG.getSelenidePassword()
                    + "@"
                    + WEB_CONFIG.getRemote();
            Configuration.timeout = 15000L;
            Configuration.browserCapabilities = capabilities;
        }

        Selenide.open();
    }

    @BeforeEach
    void preparation() {

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void completion() {

        Attach.screenshotAs("screenShot");
        Attach.pageSource();
        Attach.browserConsoleLogs();

        if (ENV.equals("selenide")) {
            Attach.addVideo();
        }
    }
}
