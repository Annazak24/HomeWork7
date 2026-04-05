package ru.otus.components;

import static com.codeborne.selenide.Condition.visible;
import static io.appium.java_client.AppiumBy.id;

import com.codeborne.selenide.SelenideElement;

public class FilterComponent extends AbsComponent<FilterComponent> {

   public FilterComponent(SelenideElement root) {
      super(root);
   }

   private SelenideElement searchInput() {
      return root.$(id("ru.otus.wishlist:id/username_input"))
            .as("Поле ввода имени пользователя");
   }

   private SelenideElement searchButton() {
      return root.$(id("ru.otus.wishlist:id/apply_button"))
            .as("Кнопка поиска пользователя");
   }

   public FilterComponent enterUserName(String userName) {
      searchInput()
            .shouldBe(visible.because("Поле ввода имени пользователя не видно"))
            .setValue(userName);
      return this;
   }

   public FilterComponent clickSearchButton() {
      searchButton()
            .shouldBe(visible.because("Кнопка поиска не видна"))
            .click();
      return this;
   }
}