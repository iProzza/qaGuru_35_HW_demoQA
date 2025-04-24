package qaguru.utils;

import static com.codeborne.selenide.Selenide.executeJavaScript;

public class JsActionsHelper {

    public void removeFixedElements() {
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
    }
}
