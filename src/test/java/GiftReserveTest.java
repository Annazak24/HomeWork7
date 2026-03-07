import com.google.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.otus.extensions.AndroidExtension;
import ru.otus.pages.EditWishlistPage;
import ru.otus.pages.LoginPage;
import ru.otus.pages.MyWishlistsPage;

@ExtendWith(AndroidExtension.class)
public class GiftReserveTest {

    @Inject
    private LoginPage loginPage;

    @Inject
    private MyWishlistsPage myWishlistsPage;

    @Inject
    private EditWishlistPage editWishlistPage;

    @Test
    public void reserveGift() {

        loginPage.login("tonyp90", "12345678");

        myWishlistsPage.tapEditWishlist(1);

        editWishlistPage
                .tapGift(1)
                .reserveGift();

        editWishlistPage.assertGiftReserved(1);
    }
}
