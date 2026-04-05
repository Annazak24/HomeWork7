import com.google.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.otus.extensions.AndroidExtension;
import ru.otus.pages.CreateGiftPage;
import ru.otus.pages.GiftsPage;
import ru.otus.pages.LoginPage;
import ru.otus.pages.MyWishlistsPage;
import ru.otus.utils.DatabaseUtils;

@ExtendWith(AndroidExtension.class)
public class CreateGiftTest {

   @Inject
   private LoginPage loginPage;

   @Inject
   private GiftsPage giftPage;

   @Inject
   MyWishlistsPage myWishlistsPage;

   @Inject
   private CreateGiftPage createGiftPage;

   private final DatabaseUtils databaseUtils = new DatabaseUtils();
   private String giftName = "Gift2026";
   private String giftDescription = "Big gift 2026";
   private int price = 800;

   @Test
   public void createGiftTest() {

      databaseUtils.deleteGiftByTitle("tonyp90", "Gift2026");

      loginPage.login("tonyp90", "12345678");
      giftPage
            .bottomMenu();
      myWishlistsPage
            .openWishList(0);
      giftPage
            .tapAddGiftBotton();
      createGiftPage
            .enterTitle(giftName)
            .enterPrice(price)
            .enterDescription(giftDescription)
            .setSaveButton();

      giftPage
            .assertGiftTitle(1, giftName)
            .assertGiftSubtitle(1, giftDescription);
   }
}
