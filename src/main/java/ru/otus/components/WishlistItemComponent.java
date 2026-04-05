package ru.otus.components;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static io.appium.java_client.AppiumBy.id;

import com.codeborne.selenide.SelenideElement;

public class WishlistItemComponent extends AbsComponent<WishlistItemComponent> {

   private final SelenideElement title =
         root.$(id("ru.otus.wishlist:id/title"))
               .as("Заголовок списка желаний");

   private final SelenideElement subtitle =
         root.$(id("ru.otus.wishlist:id/subtitle"))
               .as("Подзаголовок списка желаний");

   private final SelenideElement editButton =
         root.$(id("ru.otus.wishlist:id/edit_button"))
               .as("Кнопка редактирования списка желаний");

   private final SelenideElement deleteButton =
         root.$(id("ru.otus.wishlist:id/delete_button"))
               .as("Кнопка удаления списка желаний");

   public WishlistItemComponent(SelenideElement root) {
      super(root);
   }

   public WishlistItemComponent assertTitleEqualsTo(String value) {
      title.shouldHave(text(value).because("Wrong Title"));
      return this;
   }

   public WishlistItemComponent assertSubtitleEqualsTo(String value) {
      subtitle.shouldHave(text(value).because("Wrong Subtitle"));
      return this;
   }

   public String getTitle() {
      return title.getText();
   }

   public String getSubtitle() {
      return subtitle.getText();
   }

   public WishlistItemComponent tapEdit() {
      editButton.shouldBe(visible.because("'Edit' button can't be found"))
            .click();
      return this;
   }

   public WishlistItemComponent clickDeleteButton() {
      deleteButton.shouldBe(visible.because("'Delete' button can't be found"))
            .click();
      return this;
   }
}