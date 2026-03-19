package ru.otus.pages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.appium.SelenideAppiumElement;
import com.google.inject.Singleton;
import ru.otus.components.GiftItem;
import ru.otus.components.GiftListContent;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.appium.SelenideAppium.$;
import static io.appium.java_client.AppiumBy.id;
import static org.openqa.selenium.By.xpath;

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
            $(xpath("//android.widget.Toast"))
                    .as("Toast об изменении статуса подарка");


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

    String checkedValue = reservedToggle.getAttribute("checked");

    public String getReservedCheckedValue() {
        reservedToggle.shouldBe(visible.because("Переключатель статуса подарка не виден"));
        return reservedToggle.getAttribute("checked");
    }

    public GiftsPage assertStatusChangedToast(String checkedValue) {
        reservedToggle.shouldBe(visible.because("Переключатель статуса подарка не виден"));

        if ("false".equals(checkedValue)) {
            reservedToggle.click();
            reservedToggle.shouldHave(
                    attribute("checked", "true")
                            .because("Статус подарка после нажатия должен стать true")
            );
        } else if ("true".equals(checkedValue)) {
            throw new AssertionError("Подарок уже отмечен как reserved");
        } else {
            throw new AssertionError("Не удалось определить состояние подарка. checked = " + checkedValue);
        }

        return this;
    }
}
