package configs;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:api.properties"
})
public interface ApiConfig extends Config, CredentialConfig {

    @Key("setting.baseUrl")
    String getBaseUrl();

    @Key("setting.patch")
    String getPatch();

}
