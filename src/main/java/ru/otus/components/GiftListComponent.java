package ru.otus.components;

import static io.appium.java_client.AppiumBy.id;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

public class GiftListComponent extends AbsComponent<GiftItemComponent> {

   private final ElementsCollection items =
         root.$$(id("ru.otus.wishlist:id/gift_item"))
               .as("Списки желаний");

   public GiftListComponent(SelenideElement root) {
      super(root);
   }

   public GiftItemComponent get(int index) {
      return new GiftItemComponent(items.get(index - 1));
   }

   public int getWishesSize() {
      return items.size();
   }
}