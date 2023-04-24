package basetest;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import configs.MobileConfig;
import driver.BrowserstackMobileDriver;
import driver.LocalMobileDriver;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.Getter;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class BaseTestMobile {

    public static final String ENV = System.getProperty("env");

    @Getter
    public static String loginMobile;
    @Getter
    public static String userPasswordMobile;
    @Getter
    public static String urlApplicationBrowserstackMobile;
    @Getter
    public static String urlBrowserstackMobile;
    @Getter
    public static String deviceMobile;
    @Getter
    public static String osVersionMobile;
    @Getter
    public static String projectMobile;
    @Getter
    public static String buildNumberMobile;
    @Getter
    public static String buildNameMobile;
    @Getter
    public static String appPackageMobile;
    @Getter
    public static String appActivityMobile;
    @Getter
    public static String hostMobile;

    @BeforeAll
    public static void init() throws Exception {

        MobileConfig config = ConfigFactory.create(MobileConfig.class, System.getProperties());

        switch (ENV) {
            case "browserstack": {
                userPasswordMobile = config.getBrowserStackPassword();
                loginMobile = config.getBrowserStackLogin();
                urlApplicationBrowserstackMobile = config.getUrlApplicationBrowserstack();
                urlBrowserstackMobile = config.getUrlBrowserstack();
                deviceMobile = config.getDevice();
                osVersionMobile = config.getOsVersion();
                projectMobile = config.getProject();
                buildNumberMobile = config.getBuildNumber();
                buildNameMobile = config.getBuildName();

                Configuration.browser = BrowserstackMobileDriver.class.getName();
                break;
            }
            case "emulation":
            case "real": {
                hostMobile = config.getHost();
                deviceMobile = config.getDevice();
                osVersionMobile = config.getOsVersion();
                appPackageMobile = config.getAppPackage();
                appActivityMobile = config.getAppActivity();

                Configuration.browser = LocalMobileDriver.class.getName();
                break;
            }
            default: {
                throw new Exception("Не верный тип окружения");
            }
        }

        Configuration.browserSize = null;
    }

    @BeforeEach
    void preparation() {

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        Selenide.open();
    }

    @AfterEach
    void completion() {

        if (ENV.equals("browserstack")) {
            Attach.sessionId = Selenide.sessionId().toString();
        } else {
            Attach.screenshotAs("screenShot");
        }

        Selenide.closeWebDriver();

        if (ENV.equals("browserstack")) {
            Attach.addVideo();
        }
    }
}
