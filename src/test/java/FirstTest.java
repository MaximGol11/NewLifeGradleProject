
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.javafaker.Faker;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.DemoQaFormPage;
import pages.DragAndDropPage;
import java.util.HashMap;
import java.util.Map;
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
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);

        Configuration.browserCapabilities = capabilities;
        Configuration.startMaximized = true;
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub/";
    }



    @Test
    @Feature("Форма регистрации")
    @Story("Полное заполнение формы")
    @Owner("maximGo")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Заполнение формы регистрации DemoQA")
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
                .submitButtonClick()
                .checkForm(expectedData);
    }


    DragAndDropPage dragAndDropPage = new DragAndDropPage();

    @Test
    @Feature("Drag and Drop test")
    @Story("Drag and Drop")
    @Owner("maximGo")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Drag and Drop A to B test")
    public void dragAndDropAtoBTest() {

        dragAndDropPage
                .openPage()
                .columnAtoB()
                .checkA()
                .checkB();
    }
}
