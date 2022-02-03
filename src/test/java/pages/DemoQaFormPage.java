package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import components.Calendar;
import io.qameta.allure.Step;
import org.assertj.core.api.SoftAssertions;

import java.util.Map;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static java.lang.String.format;

public class DemoQaFormPage {
    @Step("Открываем страницу")
    public DemoQaFormPage openFormPage() {
        open("https://demoqa.com/automation-practice-form");

        return this;
    }

    @Step("Заполняем имя")
    public DemoQaFormPage fillName(String firstName, String lastName) {
        $("#firstName").val(firstName);
        $("#lastName").val(lastName);

        return this;
    }

    @Step("Заполняем email")
    public DemoQaFormPage fillEmail(String email) {
        $("#userEmail").val(email);

        return this;
    }

    @Step("Выбираем пол")
    public DemoQaFormPage selectGender(String gender) {
        $(format("[name=gender][value=%s]", gender)).parent().click();

        return this;
    }

    @Step("Заполняем номер телефона")
    public DemoQaFormPage fillNumber(String number) {
        $("#userNumber").val(number);

        return this;
    }

    @Step("Выбираем предмет")
    public DemoQaFormPage selectSubjects(String subject) {
        $("#subjectsInput").val(subject).pressEnter();

        return this;
    }

    @Step("Выбираем хобби")
    public DemoQaFormPage selectHobbie(String hobbie) {
        $(byText(hobbie)).click();

        return this;
    }

    @Step("Загружаем фото")
    public DemoQaFormPage uploadPicture(String imagePicture) {
        $("#uploadPicture").uploadFromClasspath("./img/" + imagePicture);

        return this;
    }

    @Step("Вводим адрес")
    public DemoQaFormPage fillCurrentAddress(String address) {
        $x("//textarea[@placeholder='Current Address']").val(address);

        return this;
    }

    @Step("Выбираем регион")
    public DemoQaFormPage selectState(String state) {
        $("#state").scrollTo().click();
        $("#stateCity-wrapper").$(byText(state)).click();

        return this;
    }

    @Step("Выбраем город")
    public DemoQaFormPage selectCity(String city) {
        $("#city").scrollTo().click();
        $("#stateCity-wrapper").$(byText(city)).click();

        return this;
    }

    @Step("Выбираем дату рождения")
    public DemoQaFormPage selectDate(String day, String month, String year) {
        Calendar.setDate(day, month, year);

        return this;
    }

    @Step("Сохраняем форму")
    public DemoQaFormPage submitButtonClick() {
        $("#submit").scrollTo().click();

        return this;
    }

    @Step("Проверяем форму")
    public void checkForm(Map <String, String> expectedData) {
        ElementsCollection lines = $$(".table-responsive tbody tr").snapshot();

        SoftAssertions softly = new SoftAssertions();

        for (SelenideElement line: lines) {
            String key = line.$("td").text(); // Student Name
            String expectedValue = expectedData.get(key);
            String actualValue = line.$("td", 1).text();

            softly.assertThat(actualValue)
                    .as(format("Result in line %s was %s, but expected %s", key, actualValue, expectedValue))
                    .isEqualTo(expectedValue);
        }
        softly.assertAll();
    }

}

