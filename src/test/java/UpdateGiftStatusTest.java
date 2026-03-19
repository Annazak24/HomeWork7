import com.google.inject.Inject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.otus.extensions.AndroidExtension;
import ru.otus.pages.GiftsPage;
import ru.otus.pages.LoginPage;
import ru.otus.pages.UsersPage;
import ru.otus.pages.WishListPage;

@ExtendWith(AndroidExtension.class)
public class UpdateGiftStatusTest {

    @Inject
    private LoginPage loginPage;

    @Inject
    private UsersPage usersPage;

    @Inject
    private WishListPage wishListPage;

    @Inject
    private GiftsPage giftsPage;

    @Test
    public void updateStatusTest() {
        loginPage.login("Annaanna", "12345678");
        usersPage
                .clickFilterButton()
                .enterUserName("Anna")
                .clickSearchButton()
                .clickUserItem();
        wishListPage
                .clickWishlistByTitle("77");
        giftsPage
                .getReservedCheckedValue();
        giftsPage
                .assertStatusChangedToast("Статус подарка изменен.");

    }
    @AfterEach
    public void cleanup(){

    }
}
