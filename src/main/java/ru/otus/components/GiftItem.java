package ru.otus.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static io.appium.java_client.AppiumBy.id;

public class GiftItem extends AbsComponent<GiftItem> {
    public GiftItem(SelenideElement root) {
        super(root);
    }

    private final SelenideElement title =
            root.$(id("ru.otus.wishlist:id/title"))
                    .as("Заголовок списка желаний");

    private final SelenideElement subtitle =
            root.$(id("ru.otus.wishlist:id/subtitle"))
                    .as("Подзаголовок списка желаний");


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
