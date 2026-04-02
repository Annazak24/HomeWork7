package ru.otus.components;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.otus.pages.MyWishlistsPage;
import ru.otus.pages.UsersPage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.appium.SelenideAppium.$;
import static io.appium.java_client.AppiumBy.id;

public class BottomMenuComponent extends AbsComponent<BottomMenuComponent> {
    public BottomMenuComponent(SelenideElement root) {
        super(root);
    }
    private final ElementsCollection items =
            root.$$(id("ru.otus.wishlist:id/wishlist_item"))
                    .as("Списки желаний");

    public BottomMenuComponent openWishList(int index) {
        tapWishlist(index);
        return this;
    }

    public void tapWishlist(int index) {
        root.$$(id("ru.otus.wishlist:id/wishlist_item"))
                .get(index)
                .click();
    }
    public UsersPage clickUsersMenuButton() {
        usersMenu.shouldBe(visible.because("Кнопка меню не видна"))
                .click();
        return this;
    }
    private final SelenideElement usersMenu =
            $(id("ru.otus.wishlist:id/users_menu"))
                    .as("Кнопка меню пользователей");
}
