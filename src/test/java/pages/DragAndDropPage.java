package pages;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class DragAndDropPage {

    @Step("Open page")
    public DragAndDropPage openPage() {
        Selenide.open("https://the-internet.herokuapp.com/drag_and_drop");

        return this;
    }

    @Step("Drag and drop a to b")
    public DragAndDropPage columnAtoB() {
        $("#column-a").dragAndDropTo("#column-b");

        return this;
    }

    @Step("Check A")
    public DragAndDropPage checkA() {
        $("#column-b").shouldHave(text("A"));
        return this;
    }

    @Step("Check B")
    public void checkB() {
        $("#column-a").shouldHave(text("B"));
    }
}
