package basetest;

import configs.ApiConfig;
import org.aeonbits.owner.ConfigFactory;

public class BaseTestAPI {

    public static final ApiConfig API_CONFIG = ConfigFactory.create(ApiConfig.class, System.getProperties());
}
