
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.DemoQaFormPage;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static java.lang.String.format;

public class FirstTest {

    static Faker faker = new Faker();

    static String firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            email = faker.internet().emailAddress(),
            gender = "Male",
            mobile = "1234567890",
            monthOfBirth = "May",
            yearOfBirth = "1988",
            dayOfBirth = "10",
            dayOfWeekOfBirth = "Tuesday",
            subject1 = "Chemistry",
            subject2 = "Commerce",
            hobby1 = "Sports",
            hobby2 = "Reading",
            hobby3 = "Music",
            picture = "1.png",
            currentAddress = faker.address().fullAddress(),
            state = "Uttar Pradesh",
            city = "Merrut";

    Map<String, String> expectedData = new HashMap<String, String>()
    {{
        put("Student Name", firstName + " " + lastName);
        put("Student Email", email);
        put("Gender", gender);
        put("Mobile", mobile);
        put("Date of Birth", dayOfBirth + " " + monthOfBirth + "," + yearOfBirth);
        put("Subjects", subject1);
        put("Hobbies", hobby1);
        put("Picture", picture);
        put("Address", currentAddress);
        put("State and City", state + " " + city);
    }};

    @BeforeEach
    public void setUp() {
        Configuration.baseUrl = "https://demoqa.com/";
        Configuration.startMaximized = true;
    }

    @Test
    public void demoQaPageObjectsTest() {
        DemoQaFormPage demoQaFormPage = new DemoQaFormPage();

        demoQaFormPage
                .openFormPage()
                .fillName(firstName, lastName)
                .fillEmail(email)
                .selectGender(gender)
                .fillNumber(mobile)
                .selectDate(dayOfBirth,monthOfBirth,yearOfBirth)
                .selectSubjects(subject1)
                .selectHobbie(hobby1)
                .uploadPicture(picture)
                .fillCurrentAddress(currentAddress)
                .selectState(state)
                .selectCity(city)
                .submitButtonClick();

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

    @Test
    public void githubSelenideTest() {
        Selenide.open("https://github.com/selenide/selenide");
        $("#wiki-tab").click();
        $x("//div[@id='wiki-body']").shouldHave(text("Soft assertions"));
        $(byText("Soft assertions")).click();
        $("#wiki-body").shouldHave(text("Using JUnit5 extend test class:"));
    }

    @Test
    public void dragAndDropAtoBTest() {
        Selenide.open("https://the-internet.herokuapp.com/drag_and_drop");
        $("#column-a").dragAndDropTo("#column-b");
        $("#column-b").shouldHave(text("A"));
        $("#column-a").shouldHave(text("B"));
    }

    @Test
    public void dragAndDropBtoATest() {
        Selenide.open("https://the-internet.herokuapp.com/drag_and_drop");
        $("#column-b").dragAndDropTo("#column-a");
        $("#column-b").shouldHave(text("A"));
        $("#column-a").shouldHave(text("B"));
    }
}
