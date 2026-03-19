package ru.otus.pages;

import com.codeborne.selenide.appium.SelenideAppiumElement;
import com.google.inject.Singleton;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.appium.SelenideAppium.$;
import static io.appium.java_client.AppiumBy.id;

@Singleton
public class CreateGiftPage extends AbsBasePage {

    private final SelenideAppiumElement titleInputField =
            $(id("ru.otus.wishlist:id/name_input"))
                    .as("Заголовок формы редактирования списка желаний");

    private final SelenideAppiumElement descriptionInputField =
            $(id("ru.otus.wishlist:id/description_input"))
                    .as("Поле ввода подзаголовка списка желаний");

    private final SelenideAppiumElement priceInputField =
            $(id("ru.otus.wishlist:id/price_input"))
                    .as("Поле ввода подзаголовка списка желаний");

    private final SelenideAppiumElement saveButton =
            $(id("ru.otus.wishlist:id/save_button"))
                    .as("Кнопка сохранения списка желаний");


    public CreateGiftPage enterDescription(String description) {
        descriptionInputField
                .shouldBe(visible.because("'Description' can't be found"))
                .setValue(description);
        return this;
    }

    public CreateGiftPage enterPrice(int price) {
        priceInputField
                .shouldBe(visible.because("'Price' can't be found"))
                .setValue(String.valueOf(price));
        descriptionInputField.sendKeys(String.valueOf(price));
        return this;
    }

    public CreateGiftPage enterTitle(String title) {
        titleInputField
                .shouldBe(visible.because("'Title' can't be found"))
                .setValue(title);
        return this;
    }

    public CreateGiftPage setSaveButton() {
        saveButton
                .shouldBe(visible.because("'Save' botton can't be found"))
                .click();
        return this;
    }
}
