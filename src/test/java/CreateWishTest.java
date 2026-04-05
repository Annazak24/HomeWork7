import com.google.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.otus.extensions.AndroidExtension;
import ru.otus.pages.CreateEditWishlistPage;
import ru.otus.pages.LoginPage;
import ru.otus.pages.MyWishlistsPage;
import ru.otus.utils.DatabaseUtils;

@ExtendWith(AndroidExtension.class)
public class CreateWishTest {

   @Inject
   private LoginPage loginPage;

   @Inject
   private MyWishlistsPage myWishlistsPage;

   @Inject
   private CreateEditWishlistPage createEditWishlistPage;

   private final DatabaseUtils databaseUtils = new DatabaseUtils();
   private String wishTitle = "Wish2026";
   private String description = "Wish";

   @Test
   public void createWishTest() {
      databaseUtils.deleteWishlistByTitle("Anna", "Wish2026");

      loginPage.login("Anna", "12345678");

      myWishlistsPage.tapAddWishlist();

      createEditWishlistPage
            .enterTitle(wishTitle)
            .enterDescription(description)
            .setSaveButton();

      myWishlistsPage
            .assertWishlistTitle(0,wishTitle)
            .assertWishlistDescription(0,description);
   }
}
