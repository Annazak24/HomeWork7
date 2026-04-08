package ru.otus.pages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.appium.SelenideAppium.$;
import static com.codeborne.selenide.appium.SelenideAppium.$$;
import static io.appium.java_client.AppiumBy.id;

import com.codeborne.selenide.SelenideElement;
import com.google.inject.Singleton;
import io.appium.java_client.AppiumBy;
import ru.otus.components.WishlistItemComponent;

@Singleton
public class MyWishlistsPage extends AbsBasePage {

   private final SelenideElement addButton =
         $(id("ru.otus.wishlist:id/add_button"))
               .as("Кнопка добавления списка");

   private final SelenideElement okButton =
         $(id("android:id/button1"))
               .as("ok button");

   public MyWishlistsPage assertWishlistTitle(int index, String value) {
      getWishlistItem(index).assertTitleEqualsTo(value);
      return this;
   }

   public MyWishlistsPage assertWishlistDescription(int index, String value) {
      getWishlistItem(index).assertSubtitleEqualsTo(value);
      return this;
   }

   public WishlistItemComponent getWishlistItem(int index) {
      SelenideElement row = $$(id("ru.otus.wishlist:id/wishlist_item"))
            .get(index)
            .shouldBe(visible.because("The wish can't be found: " + index));

      return new WishlistItemComponent(row);
   }

   public void tapAddWishlist() {
      addButton
            .shouldBe(visible.because("'Add' button can't be found"))
            .click();
   }

   public int getWishlistsCount() {
      return $$(id("ru.otus.wishlist:id/wishlist_item")).size();
   }

   public MyWishlistsPage clickDeleteButtonByTitle(String title) {
      for (SelenideElement row : $$(AppiumBy.id("ru.otus.wishlist:id/wishlist_item"))) {
         WishlistItemComponent item = new WishlistItemComponent(row);

         if (title.equals(item.getTitle())) {
            item.clickDeleteButton();
            return this;
         }
      }

      throw new IllegalArgumentException("Wishlist with title not found: " + title);
   }

   public MyWishlistsPage openWishList(int index) {
      $$(id("ru.otus.wishlist:id/wishlist_item"))
            .get(index)
            .click();
      return this;
   }

   public MyWishlistsPage clickOkButton() {
      okButton.click();
      return this;
   }
}