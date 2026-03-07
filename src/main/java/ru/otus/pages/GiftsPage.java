package ru.otus.pages;

import com.codeborne.selenide.SelenideElement;
import ru.otus.components.GiftItem;
import ru.otus.components.GiftListContent;
import ru.otus.components.WishlistItem;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.appium.SelenideAppium.$;
import static io.appium.java_client.AppiumBy.id;

public class GiftsPage extends AbsBasePage {

    private final GiftListContent giftListContent =
            new GiftListContent(
                    $(id("ru.otus.wishlist:id/gifts"))
            );

    private final SelenideElement addButton =
            $(id("ru.otus.wishlist:id/add_button"))
                    .as("Кнопка добавления списка");

    public void tapAddGiftBotton() {
        addButton
                .shouldBe(visible.
                        because("'Add' botton can't be found"))
                .click();
    }

    private GiftItem getGiftstItem(int index) {
        return giftListContent.get(index)
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
}
