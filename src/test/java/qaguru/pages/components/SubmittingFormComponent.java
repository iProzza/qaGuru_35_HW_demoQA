package qaguru.pages.components;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SubmittingFormComponent {

    private final SelenideElement modal = $(".modal-content");
    private final ElementsCollection tableRows = $$("table tr");


    public SubmittingFormComponent checkResult(String fieldName, String expectedValue) {
        tableRows.findBy(text(fieldName)).shouldHave(text(expectedValue));
        return this;
    }

    public SubmittingFormComponent verifyFormSubmittedSuccessfully() {
        modal.shouldBe(visible);
        return this;
    }

    public SubmittingFormComponent verifyFormNotSubmitted() {
        modal.shouldNotBe(visible);
        return this;
    }
}
