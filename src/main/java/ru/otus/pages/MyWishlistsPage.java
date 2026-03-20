package ru.otus.pages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.appium.SelenideAppiumElement;
import com.google.inject.Singleton;
import io.appium.java_client.AppiumBy;
import ru.otus.components.WishlistItem;
import ru.otus.components.WishlistsContent;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.appium.SelenideAppium.$;
import static io.appium.java_client.AppiumBy.id;

@Singleton
public class MyWishlistsPage extends AbsBasePage {

    private final WishlistsContent wishlistsContent =
            new WishlistsContent(
                    $(id("ru.otus.wishlist:id/wishlists")));

    private final SelenideAppiumElement addButton =
            $(id("ru.otus.wishlist:id/add_button"))
                    .as("Кнопка добавления списка");

    private final SelenideElement okButton =
            $(id("android:id/button1"))
                    .as("ok botton");

    public MyWishlistsPage assertNumberOfWishlists(int value) {
        wishlistsContent
                .shouldBe(
                        visible.because("Список списков желаний не виден на экране"))
                .assertSizeEqualTo(value);
        return this;
    }

    public MyWishlistsPage assertWishlistTitle(int index, String value) {
        getWishlistItem(index).assertTitleEqualsTo(value);
        return this;
    }


    public void tapEditWishlist(int index) {
        getWishlistItem(index).tapEdit();
    }

    private WishlistItem getWishlistItem(int index) {
        return wishlistsContent.get(index)
                .shouldBe(
                        visible.because(
                                "The wish can't be found"
                                        .formatted(index)));
    }

    public void tapAddWishlist() {
        addButton
                .shouldBe(visible.because("'Add' botton can't be found"))
                .click();
    }

    public int getWishlistsCount() {
        return wishlistsContent.getWishesSize();
    }

    public MyWishlistsPage openWishList(int index) {
        wishlistsContent.tapWishlist(index);
        return this;
    }

    public MyWishlistsPage clickDeleteButtonByTitle(String title) {
        SelenideElement row = $(AppiumBy.xpath(
                "//android.view.ViewGroup[@resource-id='ru.otus.wishlist:id/wishlist_item'][.//android.widget.TextView[@resource-id='ru.otus.wishlist:id/title' and @text='" + title + "']]"
        ));

        row.$(AppiumBy.id("ru.otus.wishlist:id/delete_button")).click();
        return this;
    }
    public MyWishlistsPage clickOkButton() {
        okButton.click();
        return this;
    }
}
