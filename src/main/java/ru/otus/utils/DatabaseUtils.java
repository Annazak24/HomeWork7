package ru.otus.utils;

import com.google.inject.Singleton;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Singleton
public class DatabaseUtils {

   private final String url = System.getProperty(
         "databaseUrl",
         "jdbc:postgresql://sql.otus.kartushin.su:5432/wishlist"
   );
   private final String username = System.getProperty("databaseUsername");
   private final String password = System.getProperty("databasePassword");

   public void prepareWishlistDescription(String login, String description) {
      String sql = """
            UPDATE wishlists
            SET description = ?
            WHERE user_id IN (
                SELECT id FROM users WHERE username = ?
            )
            """;

      try (Connection conn = DriverManager.getConnection(url, username, password);
            PreparedStatement ps = conn.prepareStatement(sql)) {

         ps.setString(1, description);
         ps.setString(2, login);
         ps.executeUpdate();
      } catch (SQLException e) {
         throw new RuntimeException("Failed to prepare wishlist description", e);
      }
   }

   public void deleteGiftByTitle(String login, String giftTitle) {
      String sql = """
            DELETE FROM gifts g
            USING wishlists w, users u
            WHERE g.wish_id = w.id
            AND w.user_id = u.id
            AND u.username = ?
            AND g.name = ?
            """;

      try (Connection conn = DriverManager.getConnection(url, username, password);
            PreparedStatement ps = conn.prepareStatement(sql)) {

         ps.setString(1, login);
         ps.setString(2, giftTitle);
         ps.executeUpdate();

      } catch (SQLException e) {
         throw new RuntimeException("Failed to delete gift", e);
      }
   }
   public void deleteWishlistByTitle(String login, String wishlistTitle) {
      String sql = """
            DELETE FROM wishlists
            WHERE title = ?
            AND user_id IN (
                SELECT id FROM users WHERE username = ?
            )
            """;

      try (Connection conn = DriverManager.getConnection(url, username, password);
            PreparedStatement ps = conn.prepareStatement(sql)) {

         ps.setString(1, wishlistTitle);
         ps.setString(2, login);
         ps.executeUpdate();

      } catch (SQLException e) {
         throw new RuntimeException("Failed to delete wishlist", e);
      }
   }
   public void prepareGiftReservedStatus(String login, String giftName, boolean isReserved) {
      String sql = """
        UPDATE gifts g
        SET is_reserved = ?
        FROM wishlists w
        JOIN users u ON w.user_id = u.id
        WHERE g.wish_id = w.id
        AND u.username = ?
        AND g.name = ?
            """;

      try (Connection conn = DriverManager.getConnection(url, username, password);
            PreparedStatement ps = conn.prepareStatement(sql)) {

         ps.setBoolean(1, isReserved);
         ps.setString(2, login);
         ps.setString(3, giftName);

         ps.executeUpdate();

      } catch (SQLException e) {
         throw new RuntimeException("Failed to prepare gift reserved status", e);
      }
   }
}