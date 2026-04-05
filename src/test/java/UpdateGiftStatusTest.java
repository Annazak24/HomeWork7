import com.google.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.otus.components.FilterComponent;
import ru.otus.components.HeaderComponent;
import ru.otus.extensions.AndroidExtension;
import ru.otus.pages.GiftsPage;
import ru.otus.pages.LoginPage;
import ru.otus.pages.UsersPage;
import ru.otus.pages.WishListPage;
import ru.otus.utils.DatabaseUtils;

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

   private FilterComponent filterComponent;

   private HeaderComponent headerComponent;

   private final DatabaseUtils databaseUtils = new DatabaseUtils();

   @Test
   public void updateStatusTest() {
      databaseUtils.prepareGiftReservedStatus("Anna","The Eiffel tower", false);

      loginPage
            .login("Annaanna", "12345678");
      usersPage
            .bottomMenu().clickUsersMenuButton();;
      usersPage
            .header()
            .clickFilterButton();
      usersPage
            .filter()
            .enterUserName("Anna")
            .clickSearchButton();
      usersPage
            .clickUserItem();
      wishListPage
            .clickWishlistByTitle("Paris");
      giftsPage
            .getReservedCheckedValue();
      giftsPage
            .tapStatusChangeButton()
            .chaeckReservedStatus("true");
   }
}
