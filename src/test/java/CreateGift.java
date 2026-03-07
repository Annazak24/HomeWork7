
import com.google.inject.Inject;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.otus.extensions.AndroidExtension;
import ru.otus.pages.EditWishlistPage;
import ru.otus.pages.LoginPage;
import ru.otus.pages.MyWishlistsPage;

public class CreateGift {
    @Nested
    @ExtendWith(AndroidExtension.class)
    class GiftCreateTest {

        @Inject
        private LoginPage loginPage;

        @Inject
        private MyWishlistsPage myWishlistsPage;

        @Inject
        private EditWishlistPage editWishlistPage;

        @Test
        public void createGift() {

            loginPage.login("tonyp90", "12345678");

            myWishlistsPage.tapEditWishlist(1);

            editWishlistPage
                    .tapAddGift()
                    .fillGiftName("iPhone")
                    .fillGiftPrice("1000")
                    .saveGift();

            editWishlistPage.assertGiftTitle("iPhone");
        }
    }
}
