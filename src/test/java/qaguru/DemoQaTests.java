package qaguru;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class DemoQaTests {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.holdBrowserOpen = false;
    }


    @Test
    void demoQaFillFormTest() {
        open("/automation-practice-form");

        executeJavaScript("$('footer').remove();");
        executeJavaScript("$('#fixedban').remove();");

        open("/automation-practice-form");

        $("#firstName").setValue("Anatoly");
        $("#lastName").setValue("Toorbeena");
        $("#userEmail").setValue("Toorbeena@mail.ru");
        $("#genterWrapper").$(byText("Male")).click();
        $("#userNumber").setValue("9811254534");

        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("October");
        $(".react-datepicker__year-select").selectOption("1987");
        $$("div.react-datepicker__day").findBy(text("4")).click();

        $("#subjectsInput").setValue("Chemistry").pressEnter();
        $("label[for=hobbies-checkbox-2]").click();

        $("#uploadPicture").uploadFromClasspath("download_picture.jpg"); // Загрузка картинки


    }

    @AfterAll
    static void afterAll() {
        closeWebDriver();
    }

}
