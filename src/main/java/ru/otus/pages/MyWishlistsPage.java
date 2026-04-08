package ru.otus.pages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.appium.java_client.AppiumBy.id;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.google.inject.Singleton;
import ru.otus.components.WishlistItemComponent;

@Singleton
public class MyWishlistsPage extends AbsBasePage {

   private final SelenideElement addButton =
         $(id("ru.otus.wishlist:id/add_button"))
               .as("Кнопка добавления списка");

   private final ElementsCollection wishlistItems =
         $$(id("ru.otus.wishlist:id/wishlist_item"));


   public MyWishlistsPage assertWishlistTitle(int index, String value) {
      getWishlistItem(index).assertTitleEqualsTo(value);
      return this;
   }

   public MyWishlistsPage assertWishlistDescription(int index, String value) {
      getWishlistItem(index).assertSubtitleEqualsTo(value);
      return this;
   }

   public WishlistItemComponent getWishlistItem(int index) {
      SelenideElement row = wishlistItems
            .get(index)
            .shouldBe(visible.because("The wish can't be found: " + index));

      return new WishlistItemComponent(row);
   }

   public void tapAddWishlist() {
      addButton
            .shouldBe(visible.because("'Add' button can't be found"))
            .click();
   }

   public MyWishlistsPage openWishList(int index) {
      wishlistItems
            .get(index)
            .shouldBe(visible.because("Wishlist item not visible: " + index))
            .click();
      return this;
   }
}