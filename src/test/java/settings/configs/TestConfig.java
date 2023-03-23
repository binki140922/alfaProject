package settings.configs;

import com.codeborne.selenide.Configuration;
import lombok.Getter;
import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;
import settings.driver.BrowserstackMobileDriver;
import settings.driver.LocalDriver;
import settings.driver.LocalMobileDriver;
import settings.driver.RemoteDriver;

public class TestConfig {

    public static final String ENV = System.getProperty("env");
    public static final String SYS = System.getProperty("sys");


    private final Config CONFIG;

    @Getter
    private static String baseUrlWeb;
    @Getter
    private static String browserWeb;
    @Getter
    private static String browserVersionWeb;
    @Getter
    private static String browserSizeWeb;
    @Getter
    private static String remoteWeb;
    @Getter
    private static String remoteUrlVideoWeb;

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

    public TestConfig() throws Exception {

        switch (ENV) {
            case "local":
            case "selenide":
            case "api": {
                this.CONFIG = ConfigFactory.create(DesktopConfig.class, System.getProperties());
                break;
            }
            case "browserstack": {
                this.CONFIG = ConfigFactory.create(BrowserStackMobileConfig.class, System.getProperties());
                break;
            }
            case "emulation":
            case "real": {
                this.CONFIG = ConfigFactory.create(MobileConfig.class, System.getProperties());
                break;
            }
            default: {
                throw new Exception("Не верное окружение");
            }
        }
    }

    public void init() throws Exception {

        if (SYS.equals("web")) {
            baseUrlWeb = ((DesktopConfig) CONFIG).getBaseUrl();
            browserWeb = ((DesktopConfig) CONFIG).getBrowser();
            browserVersionWeb = ((DesktopConfig) CONFIG).getBrowserVersion();
            browserSizeWeb = ((DesktopConfig) CONFIG).getBrowserSize();
            remoteWeb = "https://" + ((CredentialConfig) CONFIG).getSelenideLogin() + ":" +
                    ((CredentialConfig) CONFIG).getSelenidePassword() + "@" +
                    ((DesktopConfig) CONFIG).getRemote();
            remoteUrlVideoWeb = ((DesktopConfig) CONFIG).getRemoteUrlVideo();
            if (ENV.equals("local")) {
                LocalDriver.init();
            } else if (ENV.equals("selenide")) {
                RemoteDriver.init();
            } else {
                throw new Exception("Не верный тип окружения");
            }
        } else if (SYS.equals("mobile")) {
            if (ENV.equals("browserstack")) {
                userPasswordMobile = ((CredentialConfig) CONFIG).getBrowserStackPassword();
                loginMobile = ((CredentialConfig) CONFIG).getBrowserStackLogin();
                urlApplicationBrowserstackMobile = ((BrowserStackMobileConfig) CONFIG).getUrlApplicationBrowserstack();
                urlBrowserstackMobile = ((BrowserStackMobileConfig) CONFIG).getUrlBrowserstack();
                deviceMobile = ((BrowserStackMobileConfig) CONFIG).getDevice();
                osVersionMobile = ((BrowserStackMobileConfig) CONFIG).getOsVersion();
                projectMobile = ((BrowserStackMobileConfig) CONFIG).getProject();
                buildNumberMobile = ((BrowserStackMobileConfig) CONFIG).getBuildNumber();
                buildNameMobile = ((BrowserStackMobileConfig) CONFIG).getBuildName();
                Configuration.browser = BrowserstackMobileDriver.class.getName();
            } else if (ENV.equals("emulation") || ENV.equals("real")) {
                hostMobile = ((MobileConfig) CONFIG).getHost();
                deviceMobile = ((MobileConfig) CONFIG).getDevice();
                osVersionMobile = ((MobileConfig) CONFIG).getOsVersion();
                appPackageMobile = ((MobileConfig) CONFIG).getAppPackage();
                appActivityMobile = ((MobileConfig) CONFIG).getAppActivity();
                Configuration.browser = LocalMobileDriver.class.getName();
            } else {
                throw new Exception("Не верный тип окружения");
            }
        } else {
            throw new Exception("Не верный тип системы");
        }
    }
}