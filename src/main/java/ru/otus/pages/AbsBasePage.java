package ru.otus.pages;

import static com.codeborne.selenide.appium.SelenideAppium.$;
import static org.openqa.selenium.By.id;

import ru.otus.components.BottomMenuComponent;
import ru.otus.components.FilterComponent;
import ru.otus.components.HeaderComponent;
import ru.otus.pageobject.AbsPageObject;

public abstract class AbsBasePage extends AbsPageObject {

   public BottomMenuComponent bottomMenu() {
      return new BottomMenuComponent(
            $(id("ru.otus.wishlist:id/bottom_navigation"))
      );
   }

   public HeaderComponent header() {
      return new HeaderComponent(
            $(id("ru.otus.wishlist:id/top_app_bar_layout"))
      );
   }

   public FilterComponent filter() {
      return new FilterComponent(
            $(id("ru.otus.wishlist:id/users_filter_bottom_sheet"))
      );
   }
}