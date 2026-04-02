import com.google.inject.Inject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.otus.extensions.AndroidExtension;
import ru.otus.pages.CreateEditWishlistPage;
import ru.otus.pages.LoginPage;
import ru.otus.pages.MyWishlistsPage;

@ExtendWith(AndroidExtension.class)
public class CreateWishTest {

    @Inject
    private LoginPage loginPage;

    @Inject
    private MyWishlistsPage myWishlistsPage;

    @Inject
    private CreateEditWishlistPage createEditWishlistPage;

    private String wishTitle = "Wish2026";
    private String description = "Wish";

    @Test
    public void createWishTest() {
        loginPage.login("Anna", "12345678");

        int beforeCount = myWishlistsPage.getWishlistsCount();

        myWishlistsPage.tapAddWishlist();

        createEditWishlistPage
                .enterTitle(wishTitle)
                .enterDescription(description)
                .setSaveButton();

        myWishlistsPage
                .assertNumberOfWishlists(beforeCount + 1)
                .assertWishlistTitle(beforeCount + 1, wishTitle);
    }

    @AfterEach
    public void cleanup() {
        myWishlistsPage
                .clickDeleteButtonByTitle(wishTitle)
                .clickOkButton();
    }
}