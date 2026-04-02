package ru.otus.components;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.CollectionCondition.size;


public class WishlistsContent extends AbsComponent<WishlistsContent> {


    public WishlistsContent(SelenideElement root) {
        super(root);
    }

    public WishlistItem get(int index) {
        return new WishlistItem(items.get(index - 1));
    }

    public void assertSizeEqualTo(int expected) {
        items.shouldHave(size(expected)
                        .because("Wrong size"));
    }

    public int getWishesSize() {
        return items.size();
    }


}
