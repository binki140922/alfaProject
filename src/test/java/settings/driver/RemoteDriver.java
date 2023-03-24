package settings.driver;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;
import settings.configs.TestConfig;

public class RemoteDriver {

    @BeforeAll
    public static void init() {
        Configuration.baseUrl = TestConfig.getBaseUrlWeb();
        Configuration.browser = TestConfig.getBrowserWeb();
        Configuration.browserVersion = TestConfig.getBrowserVersionWeb();
        Configuration.browserSize = TestConfig.getBrowserSizeWeb();
        Configuration.remote = TestConfig.getRemoteWeb();
        Configuration.timeout = 15000L;

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);

        Configuration.browserCapabilities = capabilities;
    }
}
