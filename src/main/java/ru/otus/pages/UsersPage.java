package ru.otus.pages;

import com.codeborne.selenide.SelenideElement;
import com.google.inject.Singleton;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.appium.SelenideAppium.$;
import static io.appium.java_client.AppiumBy.id;

@Singleton
public class UsersPage extends AbsBasePage {


    private final SelenideElement userItem =
            $(id("ru.otus.wishlist:id/user_item"))
                    .as("Элемент пользователя");


    public UsersPage clickUserItem() {
        userItem
                .shouldBe(visible.because("Пользователь не найден или не виден"))
                .click();
        return this;
    }


}
