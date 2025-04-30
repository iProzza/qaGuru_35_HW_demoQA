package qaguru.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qaguru.pages.RegistrationPage;
import qaguru.pages.components.SubmittingFormComponent;

import static qaguru.utils.RandomTestDataGenerator.*;


public class DemoQaTests extends TestBase {

    String firstName = getRandomFirstName();
    String lastName = getRandomLastName();
    String email = getRandomEmail();
    String gender = getRandomGender();
    String mobile = getRandomMobile();
    String birthDay = getRandomBirthDay();
    String birthMonth = getRandomBirthMonth();
    String birthYear = getRandomBirthYear();
    String subject = getRandomSubject();
    String address = getRandomAddress();
    String state = getRandomState();
    String city = getRandomCity(state);

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
