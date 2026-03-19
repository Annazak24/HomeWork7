package ru.otus.pages;

import com.codeborne.selenide.appium.SelenideAppiumElement;
import com.google.inject.Singleton;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.appium.SelenideAppium.$;
import static org.openqa.selenium.By.xpath;

@Singleton
public class WishListPage extends AbsBasePage {
    private SelenideAppiumElement wishlistByTitle(String title) {
        return $(xpath("//*[@resource-id='ru.otus.wishlist:id/title' and @text='" + title + "']"))
                .as("Список желаний с названием: " + title);
    }

    public WishListPage clickWishlistByTitle(String title) {
        wishlistByTitle(title)
                .shouldBe(visible.because("Список желаний с названием " + title + " не виден"))
                .click();
        return this;
    }
}
