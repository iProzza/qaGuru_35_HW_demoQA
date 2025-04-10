package qaguru;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class DemoQaTests {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.holdBrowserOpen = false;
        Configuration.timeout = 5000; // default 4000
    }


    @Test
    void demoQaFillFormTest() {
        open("/automation-practice-form");

        $x("//input[@id='firstName']").setValue("Anatoly");
        $x("//input[@id='lastName']").setValue("Toorbeena");
        $x("//input[@id='userEmail']").setValue("toorboAnatoly@mail.ru");
        $x("//label[@title=''][contains(text(), 'Male')]").click();
        $x("//input[@id='userNumber']").setValue("89678467411");
        $x("//input[@id='dateOfBirthInput']").click();
        $x("//div[contains(@class, 'react-datepicker__day--today')]").click();
        $x("//input[@id= 'subjectsInput']").setValue("e");
        $x("//div[contains(@id, 'subjectsContainer')]").setValue("e");//Это выпадашка вообще, паузой надо искать локатор
        $(".subjects-auto-complete__menu").$("div").click(); // клик по первому элементу выпадаш
        $x("//label[@title=''][contains(text(), 'Sports\n')]").click();
//        $("#submit").click();
//
//         $("#submit").click();
//         $("#output #name").shouldHave(text("Anatoly Toorbeena"));
//         $("#output #email").shouldHave(text("toorboAnatoly@mail.ru"));
    }


    @AfterAll
    static void afterAll() {
        closeWebDriver();
    }

}
