import com.google.inject.Inject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.otus.components.BottomMenuComponent;
import ru.otus.components.HeaderComponent;
import ru.otus.extensions.AndroidExtension;
import ru.otus.pages.*;
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

   private final DatabaseUtils databaseUtils = new DatabaseUtils();
   private HeaderComponent headerComponent;
   private FilterPage filterPage;

   @Test
   public void updateStatusTest() {
      databaseUtils.prepareGiftReservedStatus("Annaanna","77", false);

      loginPage
            .login("Annaanna", "12345678");
      usersPage
            .bottomMenu().clickUsersMenuButton();;
      headerComponent
            .clickFilterButton();
      filterPage
            .enterUserName("Anna")
            .clickSearchButton();
      usersPage
            .clickUserItem();
      wishListPage
            .clickWishlistByTitle("77");
      giftsPage
            .getReservedCheckedValue();
      giftsPage
            .tapStatusChangeButton()
            .chaeckReservedStatus("true");
   }
}
