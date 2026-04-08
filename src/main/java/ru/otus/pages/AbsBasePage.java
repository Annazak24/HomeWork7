package ru.otus.pages;

import static com.codeborne.selenide.appium.SelenideAppium.$;
import static org.openqa.selenium.By.id;

import com.codeborne.selenide.SelenideElement;
import ru.otus.components.ButtomMenuComponent;
import ru.otus.components.HeaderComponent;
import ru.otus.pageobject.AbsPageObject;

public abstract class AbsBasePage extends AbsPageObject {

   private final SelenideElement buttomNavigation =
         $(id("ru.otus.wishlist:id/bottom_navigation"))
               .as("Нижнее меню навигации");

   private final SelenideElement topAppBarLayout =
         $(id("ru.otus.wishlist:id/top_app_bar_layout"))
               .as("Верхняя панель страницы");

   private final SelenideElement usersFilterButtomSheet =
         $(id("ru.otus.wishlist:id/users_filter_bottom_sheet"))
               .as("Панель фильтра пользователей");

   private final ButtomMenuComponent buttomMenu =
         new ButtomMenuComponent(buttomNavigation);

   private final HeaderComponent header =
         new HeaderComponent(topAppBarLayout);

   private final FilterPage filter =
         new FilterPage(usersFilterButtomSheet);

   public ButtomMenuComponent buttomMenu() {
      return buttomMenu;
   }

   public HeaderComponent header() {
      return header;
   }

   public FilterPage filter() {
      return filter;
   }
}