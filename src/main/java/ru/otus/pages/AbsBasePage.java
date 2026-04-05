package ru.otus.pages;

import ru.otus.components.BottomMenuComponent;
import ru.otus.components.HeaderComponent;
import ru.otus.pageobject.AbsPageObject;

import static com.codeborne.selenide.appium.SelenideAppium.$;
import static io.appium.java_client.AppiumBy.xpath;
import static org.openqa.selenium.By.id;

public abstract class AbsBasePage extends AbsPageObject {

   public BottomMenuComponent bottomMenu() {
      return new BottomMenuComponent(
            $(id("//android.widget.FrameLayout[@resource-id='android:id/content']"))
      );
   }

   public HeaderComponent header() {
      return new HeaderComponent(
            $(id("//android.widget.FrameLayout[@resource-id='android:id/content']"))
      );
   }
}