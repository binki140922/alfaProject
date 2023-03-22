package settings.configs;

import static org.aeonbits.owner.Config.*;

@LoadPolicy(LoadType.MERGE)
@Sources({
        "system:properties",
        "classpath:credential.properties"
})
public interface CredentialConfig {

    @Key("browserstack.login")
    String getBrowserStackLogin();

    @Key("browserstack.password")
    String getBrowserStackPassword();

    @Key("selenide.login")
    String getSelenideLogin();

    @Key("selenide.password")
    String getSelenidePassword();
}
