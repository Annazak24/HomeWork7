package ru.otus.pages;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.appium.SelenideAppium.$;
import static io.appium.java_client.AppiumBy.id;

import com.codeborne.selenide.SelenideElement;
import com.google.inject.Singleton;
import ru.otus.components.ButtomMenuComponent;
import ru.otus.components.GiftItemComponent;
import ru.otus.components.GiftListComponent;


@Singleton
public class GiftsPage extends AbsBasePage {

   private final GiftListComponent giftListComponent =
         new GiftListComponent(
               $(id("ru.otus.wishlist:id/gifts")));

   private final SelenideElement addButton =
         $(id("ru.otus.wishlist:id/add_button"))
               .as("Кнопка добавления списка");

   private final SelenideElement reservedToggle =
         $(id("ru.otus.wishlist:id/reserved"))
               .as("Переключатель статуса подарка");

   private final SelenideElement buttomMenuRoot =
         $(id("ru.otus.wishlist:id/bottom_navigation"))
               .as("Нижнее меню");


   public void tapAddGiftButton() {
      addButton
            .shouldBe(visible
                  .because("'Add' botton can't be found"))
            .click();
   }

   private GiftItemComponent getGiftstItem(int index) {
      return giftListComponent.get(index)
            .shouldBe(visible
                  .because("The gift can't be found".formatted(index)));
   }

   public GiftsPage assertGiftTitle(int index, String value) {
      getGiftstItem(index).assertTitleEqualsTo(value);
      return this;
   }

   public GiftsPage assertGiftSubtitle(int index, String value) {
      getGiftstItem(index).assertSubtitleEqualsTo(value);
      return this;
   }

   public String getReservedCheckedValue() {
      reservedToggle.shouldBe(visible.because("Переключатель статуса подарка не виден"));
      return reservedToggle.getAttribute("checked");
   }

   public GiftsPage chaeckReservedStatus(String status) {
      reservedToggle.shouldHave(
            attribute("checked", status)
                  .because("После нажатия ожидалось значение checked=true"));
      return this;
   }

   public GiftsPage tapStatusChangeButton() {
      reservedToggle
            .shouldBe(visible
                  .because("'Staus change' botton can't be found"))
            .click();
      return this;
   }

   public ButtomMenuComponent buttomMenu() {
      return new ButtomMenuComponent(buttomMenuRoot);
   }
}

