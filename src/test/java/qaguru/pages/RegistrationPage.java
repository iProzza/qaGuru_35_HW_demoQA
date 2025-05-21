package qaguru.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import qaguru.pages.components.CalendarComponent;
import qaguru.utils.JsActionsHelper;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {

    private final SelenideElement centralBannerName = $(".text-center");
    private final SelenideElement firstNameInput = $("#firstName");
    private final SelenideElement lastNameInput = $("#lastName");
    private final SelenideElement userEmailInput = $("#userEmail");
    private final SelenideElement genderWrapper = $("#genterWrapper");
    private final SelenideElement userNumberInput = $("#userNumber");
    private final SelenideElement calendarInput = $("#dateOfBirthInput");
    private final SelenideElement subjectInput = $("#subjectsInput");
    private final SelenideElement hobbiesCheckbox = $("label[for=hobbies-checkbox-2]");
    private final SelenideElement uploadPicture = $("#uploadPicture");
    private final SelenideElement currentAddressTextArea = $("#currentAddress");
    private final SelenideElement selectStateDdl = $("#state");
    private final ElementsCollection dropdownOptions = $$("div[id^='react-select']");
    private final SelenideElement selectCityDdl = $("#city");
    private final SelenideElement submitBtn = $("#submit");

    CalendarComponent calendarComponent = new CalendarComponent();
    JsActionsHelper jsActionsHelper = new JsActionsHelper();


    public RegistrationPage openPage() {
        open("/automation-practice-form");
        centralBannerName.shouldHave(text("Practice Form"));

        jsActionsHelper.removeFixedElements();
        return this;
    }

    public RegistrationPage setFirstName(String firstName) {
        firstNameInput.setValue(firstName);
        return this;
    }

    public RegistrationPage setLastName(String value) {
        lastNameInput.setValue(value);
        return this;
    }

    public RegistrationPage setEmail(String value) {
        userEmailInput.setValue(value);
        return this;
    }

    public RegistrationPage setGender(String value) {
        genderWrapper.$(byText(value)).click();
        return this;
    }

    public RegistrationPage setUserNumber(String value) {
        userNumberInput.setValue(value);
        return this;
    }

    public RegistrationPage setDateOfBirth(String day, String month, String year) {
        calendarInput.click();
        calendarComponent.setDate(day, month, year);
        return this;
    }

    public RegistrationPage setSubjects(String value) {
        subjectInput.setValue(value).pressEnter();
        return this;
    }

    public RegistrationPage setHobbies() {
        hobbiesCheckbox.click();
        return this;
    }

    public RegistrationPage upLoadPicture() {
        uploadPicture.uploadFromClasspath("picture.jpeg");
        return this;
    }

    public RegistrationPage setCurrentAddress(String address) {
        currentAddressTextArea.setValue(address);
        return this;
    }

    public RegistrationPage setState(String state) {
        selectStateDdl.scrollIntoView(true).click();
        dropdownOptions.findBy(text(state)).click();
        return this;
    }

    public RegistrationPage setCity(String city) {
        selectCityDdl.scrollIntoView(true).click();
        dropdownOptions.findBy(text(city)).click();
        return this;
    }

    public void clickSubmitBtn() {
        submitBtn.scrollIntoView(true).click();
    }
}
