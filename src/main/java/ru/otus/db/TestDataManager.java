package ru.otus.db;

import com.google.inject.Singleton;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Singleton
public class TestDataManager {

    private String url = System.getProperty("database_url");
    private String dbUser = System.getProperty("database_username");
    private String dbPassword = System.getProperty("database_password");

    private Connection getConnection() throws SQLException {
        if (url == null || dbUser == null || dbPassword == null) {
            throw new IllegalStateException("DB_URL, DB_USER или DB_PASSWORD не заданы. Передайте их через -D параметры.");
        }
        return DriverManager.getConnection(url, dbUser, dbPassword);
    }

    public void updateWishlistDescription(String login, String description) {

        String sql =
                """
              UPDATE wishlists
              SET description = ?
              WHERE user_id = (
                 SELECT id FROM users WHERE username = ?
              )
                """;

        try (
                Connection conn = getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, description);
            ps.setString(2, login);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Failed to update wishlist description",
                    e
            );
        }
    }

    public void updateWishlistTitle(String login, String newTitle) {

        String sql =
                """
              UPDATE wishlists
              SET title = ?
              WHERE user_id = (
                SELECT id FROM users WHERE username = ?
              )
                """;

        try (
                Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {

            ps.setString(1, newTitle);
            ps.setString(2, login);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Failed to update wishlist title",
                    e
            );
        }
    }

    public void updateGiftStatus(String username, String giftName, boolean isReserved) {
        String sql =
                """
            UPDATE gifts g
            SET is_reserved = ?
            FROM wishlists w
            JOIN users u ON w.user_id = u.id
            WHERE g.wish_id = w.id
            AND u.username = ?
            AND g.name = ?
                """;

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setBoolean(1, isReserved);
            ps.setString(2, username);
            ps.setString(3, giftName);

            int updatedRows = ps.executeUpdate();

            if (updatedRows == 0) {
                throw new RuntimeException("Failed to update gift status for " + giftName + " of user " + username);
            }

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Failed to update gift status for " + giftName + " of user " + username,
                    e
            );
        }
    }


    public void deleteWishlist(String login) {
        String sql =
                """
              DELETE FROM wishlists
              WHERE user_id = (
                  SELECT id FROM users WHERE username = ?
              )
                """;

        try (
                Connection conn = getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, login);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete wishlist for user " + login, e);
        }
    }

    public void deleteAllGifts(String username) {
        String sql =
                """
            DELETE FROM gifts g
            USING wishlists w
            JOIN users u ON w.user_id = u.id
            WHERE g.wish_id = w.id
            AND u.username = ?
                """;

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Failed to delete gifts for user " + username,
                    e
            );
        }
    }

}