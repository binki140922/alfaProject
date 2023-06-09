package configs;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:${env}.properties"
})
public interface MobileConfig extends Config, CredentialConfig {

    @Key("settings.host")
    String getHost();

    @Key("settings.device")
    String getDevice();

    @Key("settings.osVersion")
    String getOsVersion();

    @Key("settings.appPackage")
    String getAppPackage();

    @Key("settings.appActivity")
    String getAppActivity();

    @Key("browserstack.url.application")
    String getUrlApplicationBrowserstack();

    @Key("browserstack.url")
    String getUrlBrowserstack();

    @Key("other.project")
    String getProject();

    @Key("other.build")
    String getBuildNumber();

    @Key("other.name")
    String getBuildName();
}
