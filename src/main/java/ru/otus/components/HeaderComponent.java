package ru.otus.components;

import com.codeborne.selenide.SelenideElement;
import ru.otus.pages.UsersPage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.appium.SelenideAppium.$;
import static io.appium.java_client.AppiumBy.id;

public class HeaderComponent extends AbsComponent<HeaderComponent> {

    public HeaderComponent(SelenideElement root) {
        super(root);
    }
    private final SelenideElement filterButton =
            $(id("ru.otus.wishlist:id/filter"))
                    .as("Кнопка фильтра пользователей");

    public HeaderComponent clickFilterButton() {
        filterButton
                .shouldBe(visible.because("Кнопка фильтра не видна"))
                .click();
        return this;
    }
}
