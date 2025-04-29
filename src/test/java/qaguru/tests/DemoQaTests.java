package qaguru.tests;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qaguru.pages.RegistrationPage;
import qaguru.pages.components.SubmittingFormComponent;

import java.util.Locale;


public class DemoQaTests extends TestBase {

    Faker faker = new Faker(new Locale("en-GB"));

    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();
    String email = faker.internet().emailAddress();
    String gender = faker.options().option("Male", "Female");
    String mobile = faker.phoneNumber().subscriberNumber(10);
    String birthDay = String.format("%02d", faker.number().numberBetween(1, 28));
    String birthMonth = faker.options().option(
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
    );
    String birthYear = String.valueOf(faker.number().numberBetween(1950, 2000));
    String subject = faker.options().option(
            "Chemistry", "Maths", "Physics", "Arts", "English",
            "Biology", "History", "Economics", "Computer Science", "Commerce", "Accounting"
    );
    String address = faker.address().streetAddress();
    String state = "Rajasthan";
    String city = "Jaiselmer";

    RegistrationPage practiceFormPage = new RegistrationPage();
    SubmittingFormComponent submittingFormComponent = new SubmittingFormComponent();

    @Test
    @DisplayName("Успешная отправка формы со всеми заполненными полями")
    void demoQaFillFormTest() {

        practiceFormPage.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setGender(gender)
                .setUserNumber(mobile)
                .setDateOfBirth(birthDay, birthMonth, birthYear)
                .setSubjects(subject)
                .setHobbies()
                .upLoadPicture()
                .setCurrentAddress(address)
                .setState(state)
                .setCity(city)
                .clickSubmitBtn();

        //Проверки
        submittingFormComponent.verifyFormSubmittedSuccessfully()
                .checkResult("Student Name", firstName + " " + lastName)
                .checkResult("Student Email", email)
                .checkResult("Gender", gender)
                .checkResult("Mobile", mobile)
                .checkResult("Date of Birth", birthDay + " " + birthMonth + "," + birthYear)
                .checkResult("Subjects", subject)
                .checkResult("Hobbies", "Reading")
                .checkResult("Picture", "picture.jpeg")
                .checkResult("Address", address)
                .checkResult("State and City", state + " " + city);
    }

    @Test
    @DisplayName("Успешная отправка формы только с обязательными полями")
    void demoQaFillFormWithMinimumDataTest() {

        practiceFormPage.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setGender(gender)
                .setUserNumber(mobile)
                .clickSubmitBtn();

        // Проверки
        submittingFormComponent.verifyFormSubmittedSuccessfully()
                .checkResult("Student Name", firstName + " " + lastName)
                .checkResult("Gender", gender)
                .checkResult("Mobile", mobile);
    }

    @Test
    @DisplayName("Негативная проверка: попытка отправки без имени")
    void demoQaFillFormWithOutFirstNameTest() {
        practiceFormPage.openPage()
                .setLastName(lastName)
                .setGender(gender)
                .setUserNumber(mobile)
                .clickSubmitBtn();

        // Проверки
        submittingFormComponent.verifyFormNotSubmitted();
    }
}
