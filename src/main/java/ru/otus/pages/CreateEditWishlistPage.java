package ru.otus.pages;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.appium.SelenideAppium.$;
import static io.appium.java_client.AppiumBy.id;

import com.codeborne.selenide.SelenideElement;
import com.google.inject.Singleton;

@Singleton
public class CreateEditWishlistPage extends AbsBasePage {

   private final SelenideElement titleInputField =
         $(id("ru.otus.wishlist:id/title_input"))
               .as("Заголовок формы редактирования списка желаний");

   private final SelenideElement wishlistDescriptionInputField =
         $(id("ru.otus.wishlist:id/description_input"))
               .as("Поле ввода подзаголовка списка желаний");

   private final SelenideElement saveButton =
         $(id("ru.otus.wishlist:id/save_button"))
               .as("Кнопка сохранения списка желаний");

   public CreateEditWishlistPage assertEditWishlistTitle(String expected) {
      titleInputField
            .shouldBe(visible.because("Заголовок не виден на экране"))
            .shouldHave(text(expected).because("Неверный текст заголовка"));
      return this;
   }

   public CreateEditWishlistPage enterDescription(String description) {
      wishlistDescriptionInputField
            .shouldBe(visible.because("Поле ввода подзаголовка не видно на экране"))
            .clear();

      wishlistDescriptionInputField.sendKeys(description);
      return this;
   }

   public CreateEditWishlistPage enterTitle(String title) {
      titleInputField
            .shouldBe(visible.because("Поле названия не видно"))
            .setValue(title);
      return this;
   }

   public CreateEditWishlistPage setSaveButton() {
      saveButton
            .shouldBe(visible.because("Кнопка сохранения не видна на экране"))
            .click();
      return this;
   }
}