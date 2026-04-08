package ru.otus.components;

import static com.codeborne.selenide.Condition.visible;
import static io.appium.java_client.AppiumBy.id;

import com.codeborne.selenide.SelenideElement;

public class ButtomMenuComponent extends AbsComponent<ButtomMenuComponent> {

   public ButtomMenuComponent(SelenideElement root) {
      super(root);
   }

   private final SelenideElement usersMenu =
         root.$(id("ru.otus.wishlist:id/users_menu"))
               .as("Кнопка меню пользователей");


   public ButtomMenuComponent clickUsersMenuButton() {
      usersMenu.shouldBe(visible.because("Кнопка меню не видна"))
            .click();
      return this;
   }
}