package ru.otus.components;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static io.appium.java_client.AppiumBy.id;

import com.codeborne.selenide.SelenideElement;

public class GiftItemComponent extends AbsComponent<GiftItemComponent> {

   private final SelenideElement title =
         root.$(id("ru.otus.wishlist:id/title"))
               .as("Заголовок подарка");
   private final SelenideElement subtitle =
         root.$(id("ru.otus.wishlist:id/subtitle"))
               .as("Подзаголовок подарка");

   private final SelenideElement reservationSwitch =
         root.$(id("ru.otus.wishlist:id/reserved_switch"))
               .as("Переключатель статуса резервирования");

   public GiftItemComponent(SelenideElement root) {
      super(root);
   }

   public String getTitle() {
      return title.getText();
   }

   public void assertTitleEqualsTo(String value) {
      title.shouldHave(
            text(value)
                  .because("Wrong Title"));
   }

   public void assertSubtitleEqualsTo(String value) {
      subtitle.shouldHave(
            text(value)
                  .because("Wrong Subtitle"));
   }
}