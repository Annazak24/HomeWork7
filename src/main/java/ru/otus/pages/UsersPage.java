package ru.otus.pages;

import com.codeborne.selenide.appium.SelenideAppiumElement;
import com.google.inject.Singleton;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.appium.SelenideAppium.$;
import static io.appium.java_client.AppiumBy.id;

@Singleton
public class UsersPage extends AbsBasePage {
    private final SelenideAppiumElement usersMenu =
            $(id("ru.otus.wishlist:id/users_menu"))
            .as("Кнопка меню пользователей");

    private final SelenideAppiumElement filterButton =
            $(id("ru.otus.wishlist:id/filter"))
                    .as("Кнопка фильтра пользователей");

    private final SelenideAppiumElement searchInput =
            $(id("ru.otus.wishlist:id/username_input"))
                    .as("Поле ввода имени пользователя");

    private final SelenideAppiumElement searchButton =
            $(id("ru.otus.wishlist:id/apply_button"))
                    .as("Кнопка поиска пользователя");

    private final SelenideAppiumElement userItem =
            $(id("ru.otus.wishlist:id/user_item"))
                    .as("Элемент пользователя");


    public UsersPage clickFilterButton() {
        filterButton
                .shouldBe(visible.because("Кнопка фильтра не видна"))
                .click();
        return this;
    }

    public UsersPage enterUserName(String userName) {
        searchInput
                .shouldBe(visible.because("Поле ввода имени пользователя не видно"))
                .setValue(userName);
        return this;
    }

    public UsersPage clickSearchButton() {
        searchButton
                .shouldBe(visible.because("Кнопка поиска не видна"))
                .click();
        return this;
    }

    public UsersPage clickUserItem() {
        userItem
                .shouldBe(visible.because("Пользователь не найден или не виден"))
                .click();
        return this;
    }

    public UsersPage clickUsersMenuButton() {
                usersMenu.shouldBe(visible.because("Кнопка меню не видна"))
                .click();
        return this;
    }
}
