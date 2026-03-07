
import com.google.inject.Inject;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.otus.components.WishlistsContent;
import ru.otus.extensions.AndroidExtension;
import ru.otus.pages.CreateEditWishlistPage;
import ru.otus.pages.LoginPage;
import ru.otus.pages.MyWishlistsPage;

public class CreateWishTest {
    @Nested
    @ExtendWith(AndroidExtension.class)
    class GiftCreateTest {

        @Inject
        private LoginPage loginPage;

        @Inject
        private MyWishlistsPage myWishlistsPage;

        @Inject
        private CreateEditWishlistPage createEditWishlistPage;


        @Test
        public void createGiftTest() {

            loginPage.login("tonyp90", "12345678");

            String title= "iPhone";
            String description= "The latest";

            int beforeCount = myWishlistsPage.getWishlistsCount();

            myWishlistsPage.tapAddWishlist();

            createEditWishlistPage
                    .enterTitle(title)
                    .enterDescription(description)
                    .setSaveButton();

            myWishlistsPage
                    .assertNumberOfWishlists(beforeCount + 1)
                    .assertWishlistTitle(beforeCount + 1, title);
        }
    }
}
