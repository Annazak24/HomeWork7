package ru.otus.components;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static io.appium.java_client.AppiumBy.id;

public class GiftListContent extends AbsComponent<GiftItem> {

    private final ElementsCollection items =
            root.$$(id("ru.otus.wishlist:id/gift_item"))
                    .as("Списки желаний");


    public GiftListContent(SelenideElement root) {
        super(root);
    }

    public GiftItem get(int index) {
        return new GiftItem(items.get(index - 1));
    }

    public int getWishesSize() {
        return items.size();
    }

}
