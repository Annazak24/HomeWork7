package ru.otus.pages;

import static com.codeborne.selenide.Condition.visible;
import static io.appium.java_client.AppiumBy.id;

import com.codeborne.selenide.SelenideElement;
import ru.otus.components.AbsComponent;

public class FilterPage extends AbsComponent<FilterPage> {

   public FilterPage(SelenideElement root) {
      super(root);
   }

   private final SelenideElement searchInput =
         root.$(id("ru.otus.wishlist:id/username_input"))
               .as("Поле ввода имени пользователя");

   private final SelenideElement searchButton =
         root.$(id("ru.otus.wishlist:id/apply_button"))
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