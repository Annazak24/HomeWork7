package ru.otus.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static io.appium.java_client.AppiumBy.id;

public class HeaderComponent extends AbsComponent<HeaderComponent> {

   public HeaderComponent(SelenideElement root) {
      super(root);
   }

   private final SelenideElement filterButton =
         root.$(id("ru.otus.wishlist:id/filter"))
               .as("Кнопка фильтра пользователей");

   public HeaderComponent clickFilterButton() {
      filterButton
            .shouldBe(visible.because("Кнопка фильтра не видна"))
            .click();
      return this;
   }
}