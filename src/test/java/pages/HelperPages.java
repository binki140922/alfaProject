package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;

import static org.assertj.core.api.Assertions.assertThat;

public class HelperPages {

    public static void checkUrlPage(String fullUrl) {
        assertThat(WebDriverRunner.url()).isEqualTo(fullUrl);
    }

    public static void openPage(String url) {
        Selenide.open(url);
        Selenide.sleep(1500);
    }
}
