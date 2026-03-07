package ru.otus.pages;

import com.codeborne.selenide.SelenideElement;
import com.google.inject.Singleton;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.appium.SelenideAppium.$;
import static io.appium.java_client.AppiumBy.id;


@Singleton
public class LoginPage extends AbsBasePage {

    private final SelenideElement usernameInputField =
            $(id("ru.otus.wishlist:id/username_text_input"))
                    .as("Поле ввода имени пользователя");

    private final SelenideElement passwordInputField =
            $(id("ru.otus.wishlist:id/password_text_input"))
                    .as("Поле ввода пароля");

    private final SelenideElement logInButton =
            $(id("ru.otus.wishlist:id/log_in_button"))
                    .as("Кнопка входа");

    public void login(String username, String password) {

        usernameInputField
                .shouldBe(visible.because("'UserName' can't be found "))
                .sendKeys(username);

        passwordInputField
                .shouldBe(visible.because("'Password' can't be found"))
                .sendKeys(password);

        logInButton
                .shouldBe(visible.because("'Login' botton can't be found"))
                .click();
    }
}
