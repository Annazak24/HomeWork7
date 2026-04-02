package ru.otus.pages;

import com.codeborne.selenide.SelenideElement;
import ru.otus.components.AbsComponent;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.appium.SelenideAppium.$;
import static io.appium.java_client.AppiumBy.id;

public class FilterPage extends AbsComponent<FilterPage> {
    public FilterPage(SelenideElement root) {
        super(root);
    }

    private final SelenideElement searchInput =
            $(id("ru.otus.wishlist:id/username_input"))
                    .as("Поле ввода имени пользователя");

    private final SelenideElement searchButton =
            $(id("ru.otus.wishlist:id/apply_button"))
                    .as("Кнопка поиска пользователя");

    public FilterPage enterUserName(String userName) {
        searchInput
                .shouldBe(visible.because("Поле ввода имени пользователя не видно"))
                .setValue(userName);
        return this;
    }

    public FilterPage clickSearchButton() {
        searchButton
                .shouldBe(visible.because("Кнопка поиска не видна"))
                .click();
        return this;
    }
}
