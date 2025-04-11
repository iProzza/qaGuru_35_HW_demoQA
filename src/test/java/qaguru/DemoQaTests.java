package qaguru;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class DemoQaTests {

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
        Configuration.holdBrowserOpen = true;
    }

    @Test
    void demoQaFillFormTest() {
        open("/automation-practice-form");

        executeJavaScript("$('footer').remove();");
        executeJavaScript("$('#fixedban').remove();");

        $("#firstName").setValue("Aganez");
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
        $("#uploadPicture").uploadFromClasspath("picture.jpeg");
        $("#currentAddress").setValue("Penza City");
        $("#state").click();
        $("#react-select-3-option-3").click();
        $("#city").click();
        $("#react-select-4-option-0").click();

        $("#submit").click();

        //Проверки
        $(".modal-content").shouldHave(text("Thanks for submitting the form"));
        $$("table tr").findBy(text("Student Name")).shouldHave(text("Aganez Toorbeena"));
        $$("table tr").findBy(text("Student Email")).shouldHave(text("Toorbeena@mail.ru"));
        $$("table tr").findBy(text("Gender")).shouldHave(text("Male"));
        $$("table tr").findBy(text("Mobile")).shouldHave(text("9811254534"));
        $$("table tr").findBy(text("Date of Birth")).shouldHave(text("4 October,1987"));
        $$("table tr").findBy(text("Subjects")).shouldHave(text("Chemistry"));
        $$("table tr").findBy(text("Hobbies")).shouldHave(text("Reading"));
        $$("table tr").findBy(text("Picture")).shouldHave(text("picture.jpeg"));
        $$("table tr").findBy(text("Address")).shouldHave(text("Penza City"));
        $$("table tr").findBy(text("State and City")).shouldHave(text("Rajasthan Jaipur"));

        $("#closeLargeModal").click();
    }
}
