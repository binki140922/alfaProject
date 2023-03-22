package settings.driver;

import com.codeborne.selenide.WebDriverProvider;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import settings.configs.TestConfig;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;

public class BrowserstackMobileDriver implements WebDriverProvider {

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {

        MutableCapabilities mutableCapabilities = new MutableCapabilities();
        mutableCapabilities.merge(capabilities);

        // Set your access credentials
        mutableCapabilities.setCapability("browserstack.user", TestConfig.loginMobile);
        mutableCapabilities.setCapability("browserstack.key", TestConfig.userPasswordMobile);

        // Set URL of the application under test
        mutableCapabilities.setCapability("app", TestConfig.urlApplicationBrowserstackMobile);

        // Specify device and os_version for testing
        mutableCapabilities.setCapability("device", TestConfig.deviceMobile);
        mutableCapabilities.setCapability("os_version", TestConfig.osVersionMobile);

        // Set other BrowserStack capabilities
        mutableCapabilities.setCapability("project", TestConfig.projectMobile);
        mutableCapabilities.setCapability("build", TestConfig.buildNumberMobile);
        mutableCapabilities.setCapability("name", TestConfig.buildNameMobile);

        // Initialise the remote Webdriver using BrowserStack remote URL
        // and desired capabilities defined above
        try {
            return new RemoteWebDriver(new URL(TestConfig.urlBrowserstackMobile), mutableCapabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}


