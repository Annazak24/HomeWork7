import com.google.inject.Inject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.otus.extensions.AndroidExtension;
import ru.otus.pages.*;

@ExtendWith(AndroidExtension.class)
public class CreateGiftTest {

    @Inject
    private LoginPage loginPage;

    @Inject
    private MyWishlistsPage myWishlistsPage;

    @Inject
    private CreateEditWishlistPage createEditWishlistPage;

    @Inject
    private GiftsPage giftPage;

    @Inject
    private CreateGiftPage createGiftPage;

    String giftName = "Gift2026";
    String giftDescription = "Big gift 2026";
    int price = 800;

    @Test
    public void createGift() {

        loginPage.login("tonyp90", "12345678");

        myWishlistsPage
                .openWishList(0);

        giftPage
                .tapAddGiftBotton();

        createGiftPage
                .enterTitle(giftName)
                .enterPrice(price)
                .enterDescription(giftDescription)
                .setSaveButton();

        giftPage
                .assertGiftTitle(1, giftName)
                .assertGiftSubtitle(1, giftDescription);
    }

    @AfterEach
    public void cleanup() {
        giftPage
                .clickDeleteButtonByTitle(giftName)
                .clickOkButton();
    }
}
