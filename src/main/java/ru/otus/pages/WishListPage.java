package ru.otus.pages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.appium.SelenideAppium.$$;

import com.codeborne.selenide.SelenideElement;
import com.google.inject.Singleton;
import io.appium.java_client.AppiumBy;

@Singleton
public class WishListPage extends AbsBasePage {
   private SelenideElement wishlistByTitle(String title) {
      for (SelenideElement row : $$(AppiumBy.id("ru.otus.wishlist:id/wishlist_item"))) {
         String actualTitle = row.$(AppiumBy.id("ru.otus.wishlist:id/title")).getText();
         if (title.equals(actualTitle)) {
            return row.as("Список желаний с названием: " + title);
         }
      }

      throw new IllegalArgumentException("Wishlist with title not found: " + title);
   }

   public WishListPage clickWishlistByTitle(String title) {
      wishlistByTitle(title)
            .shouldBe(visible.because("Список желаний с названием " + title + " не виден"))
            .click();
      return this;
   }
}
