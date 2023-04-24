package driver;

import basetest.BaseTestMobile;
import com.codeborne.selenide.WebDriverProvider;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

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
        mutableCapabilities.setCapability("browserstack.user", BaseTestMobile.loginMobile);
        mutableCapabilities.setCapability("browserstack.key", BaseTestMobile.userPasswordMobile);

        // Set URL of the application under test
        mutableCapabilities.setCapability("app", BaseTestMobile.urlApplicationBrowserstackMobile);

        // Specify device and os_version for testing
        mutableCapabilities.setCapability("device", BaseTestMobile.deviceMobile);
        mutableCapabilities.setCapability("os_version", BaseTestMobile.osVersionMobile);

        // Set other BrowserStack capabilities
        mutableCapabilities.setCapability("project", BaseTestMobile.projectMobile);
        mutableCapabilities.setCapability("build", BaseTestMobile.buildNumberMobile);
        mutableCapabilities.setCapability("name", BaseTestMobile.buildNameMobile);

        // Initialise the remote Webdriver using BrowserStack remote URL
        // and desired capabilities defined above
        try {
            return new RemoteWebDriver(new URL(BaseTestMobile.urlBrowserstackMobile), mutableCapabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}


