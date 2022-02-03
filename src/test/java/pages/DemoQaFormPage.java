package pages;

import com.codeborne.selenide.Selenide;
import components.Calendar;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static java.lang.String.format;

public class DemoQaFormPage {

    public DemoQaFormPage openFormPage() {
        open("https://demoqa.com/automation-practice-form");

        return this;
    }

    public DemoQaFormPage fillName(String firstName, String lastName) {
        $("#firstName").val(firstName);
        $("#lastName").val(lastName);

        return this;
    }

    public DemoQaFormPage fillEmail(String email) {
        $("#userEmail").val(email);

        return this;
    }

    public DemoQaFormPage selectGender(String gender) {
        $(format("[name=gender][value=%s]", gender)).parent().click();

        return this;
    }

    public DemoQaFormPage fillNumber(String number) {
        $("#userNumber").val(number);

        return this;
    }

    public DemoQaFormPage selectSubjects(String subject) {
        $("#subjectsInput").val(subject).pressEnter();

        return this;
    }

    public DemoQaFormPage selectHobbie(String hobbie) {
        $(byText(hobbie)).click();

        return this;
    }

    public DemoQaFormPage uploadPicture(String imagePicture) {
        $("#uploadPicture").uploadFromClasspath("./img/" + imagePicture);

        return this;
    }

    public DemoQaFormPage fillCurrentAddress(String address) {
        $x("//textarea[@placeholder='Current Address']").val(address);

        return this;
    }

    public DemoQaFormPage selectState(String state) {
        $("#state").scrollTo().click();
        $("#stateCity-wrapper").$(byText(state)).click();

        return this;
    }

    public DemoQaFormPage selectCity(String city) {
        $("#city").scrollTo().click();
        $("#stateCity-wrapper").$(byText(city)).click();

        return this;
    }

    public DemoQaFormPage selectDate(String day, String month, String year) {
        Calendar.setDate(day, month, year);

        return this;
    }

    public void submitButtonClick() {
        $("#submit").scrollTo().click();
    }

}

