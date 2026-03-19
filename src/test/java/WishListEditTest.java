import com.google.inject.Inject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.otus.extensions.AndroidExtension;
import ru.otus.pages.CreateEditWishlistPage;
import ru.otus.pages.LoginPage;
import ru.otus.pages.MyWishlistsPage;

@ExtendWith(AndroidExtension.class)
public class WishListEditTest {

    @Inject
    private LoginPage loginPage;

    @Inject
    private MyWishlistsPage myWishlistsPage;

    @Inject
    private CreateEditWishlistPage createEditWishlistPage;

    @Test
            public void editWishlist(){
    loginPage.login("Annaanna", "12345678");

        String wishlistTitle = "Trip";
        String newWishlistDescription = "Europe";


        myWishlistsPage
                .tapEditWishlist(1);

        createEditWishlistPage
                .enterDescription(newWishlistDescription)
                .enterTitle(wishlistTitle)
                .setSaveButton();

        myWishlistsPage
                .assertWishlistTitle(1, wishlistTitle)
                .assertWishlistSubtitle(1, newWishlistDescription);
    }
    @AfterEach
    public void cleanup(){

    }
}
