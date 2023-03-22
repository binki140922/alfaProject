package settings.configs;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:${env}.properties"
})
public interface BrowserStackMobileConfig extends Config, CredentialConfig {

    @Key("browserstack.url.application")
    String getUrlApplicationBrowserstack();

    @Key("browserstack.url")
    String getUrlBrowserstack();

    @Key("settings.device")
    String getDevice();

    @Key("settings.os_version")
    String getOsVersion();

    @Key("other.project")
    String getProject();

    @Key("other.build")
    String getBuildNumber();

    @Key("other.name")
    String getBuildName();
}
