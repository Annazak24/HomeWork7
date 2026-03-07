import com.google.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.otus.extensions.AndroidExtension;
import ru.otus.pages.EditWishlistPage;
import ru.otus.pages.LoginPage;
import ru.otus.pages.MyWishlistsPage;

@ExtendWith(AndroidExtension.class)
public class WishListEditTest {

    @Inject
    private LoginPage loginPage;

    @Inject
    private MyWishlistsPage myWishlistsPage;

    @Inject
    private EditWishlistPage editWishlistPage;

    @Test
            public void editWishlist(){
    loginPage.login("tonyp90", "12345678");

        String wishlistTitle = "Ждем следующий год";
        String newWishlistDescription = "остлалось 300 дней";

        myWishlistsPage
                .assertNumberOfWishlists(1)
                .assertWishlistTitle(1, wishlistTitle)
                .assertWishlistSubtitle(1, "остлалось 300 дней")
                .tapEditWishlist(1);

        editWishlistPage
                .assertEditWishlistTitle("Изменить список желаний")
                .editDescription(newWishlistDescription);

        myWishlistsPage
                .assertNumberOfWishlists(1)
                .assertWishlistTitle(1, wishlistTitle)
                .assertWishlistSubtitle(1, newWishlistDescription);
    }
}
