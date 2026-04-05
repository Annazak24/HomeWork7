package ru.otus.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static io.appium.java_client.AppiumBy.id;

public class BottomMenuComponent extends AbsComponent<BottomMenuComponent> {

   public BottomMenuComponent(SelenideElement root) {
      super(root);
   }

   private final SelenideElement usersMenu =
         root.$(id("ru.otus.wishlist:id/users_menu"))
               .as("Кнопка меню пользователей");


   public BottomMenuComponent clickUsersMenuButton() {
      usersMenu.shouldBe(visible.because("Кнопка меню не видна"))
            .click();
      return this;
   }
}