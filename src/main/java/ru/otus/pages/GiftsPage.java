package ru.otus.pages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.appium.SelenideAppiumElement;
import com.google.inject.Singleton;
import io.appium.java_client.AppiumBy;
import ru.otus.components.GiftItem;
import ru.otus.components.GiftListContent;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.appium.SelenideAppium.$;
import static io.appium.java_client.AppiumBy.id;


@Singleton
public class GiftsPage extends AbsBasePage {

    private final GiftListContent giftListContent =
            new GiftListContent(
                    $(id("ru.otus.wishlist:id/gifts"))
            );

    private final SelenideAppiumElement addButton =
            $(id("ru.otus.wishlist:id/add_button"))
                    .as("Кнопка добавления списка");

    private final SelenideAppiumElement reservedToggle =
            $(id("ru.otus.wishlist:id/reserved"))
                    .as("Переключатель статуса подарка");

    private final SelenideElement statusChangedToast =
            $(id("ru.otus.wishlist:id/reserved"))
                    .as("Toast об изменении статуса подарка");

    private final SelenideElement okButton =
            $(id("android:id/button1"))
                    .as("ok botton");

    public void tapAddGiftBotton() {
        addButton
                .shouldBe(visible.
                        because("'Add' botton can't be found"))
                .click();
    }

    private GiftItem getGiftstItem(int index) {
        return giftListContent.get(index)
                .shouldBe(visible
                        .because("The gift can't be found" .formatted(index)));
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
        statusChangedToast
                .shouldBe(visible.
                        because("'Staus change' botton can't be found"))
                .click();
        return this;
    }

    public GiftsPage clickDeleteButtonByTitle(String title) {
        SelenideElement row = $(AppiumBy.xpath(
                "//android.view.ViewGroup[@resource-id='ru.otus.wishlist:id/wishlist_item'][.//android.widget.TextView[@resource-id='ru.otus.wishlist:id/title' and @text='" + title + "']]"
        ));

        row.$(AppiumBy.id("ru.otus.wishlist:id/delete_button")).click();
        return this;
    }

    public GiftsPage clickOkButton() {
        okButton.click();
        return this;
    }
}

