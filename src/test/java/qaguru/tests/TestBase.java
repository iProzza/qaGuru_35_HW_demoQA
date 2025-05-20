package qaguru.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;
import qaguru.utils.Attach;

import java.util.Map;

public class TestBase {

    @BeforeAll
    static void setUp() {

        String selenoidHost = System.getProperty("SELENOID_HOST", "selenoid.autotests.cloud");
        String browser = System.getProperty("browser", "chrome");
        String browserVersion = System.getProperty("browserVersion", "127.0");
        String screenResolution = System.getProperty("screenResolution", "1920x1080");

        WebDriverManager.chromedriver()
                .clearDriverCache()
                .clearResolutionCache()
                .setup();

        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = screenResolution;
        Configuration.browser = browser;
        Configuration.browserVersion = browserVersion;
        Configuration.pageLoadStrategy = "eager";
        Configuration.timeout = 10000;
        Configuration.remote = String.format("https://user1:1234@%s/wd/hub",
                selenoidHost);


        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();

    }
}
