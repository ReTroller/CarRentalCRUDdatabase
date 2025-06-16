package app;

import java.sql.*;

public class DiscountDAO {

    public Discounts getAllDiscounts() {
        Discounts discounts = new Discounts();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement("SELECT * FROM скидка");
             ResultSet result = stmt.executeQuery()) {

            while (result.next()) {
                int id = result.getInt("id_скидки");
                int value = result.getInt("значение");
                String description = result.getString("описание");
                discounts.add(new Discount(id, value, description));
            }

        } catch (SQLException e) {
            System.out.println("Ошибка при получении скидок: " + e.getMessage());
        }

        return discounts;
    }

    public boolean addDiscount(int value) {
        String query = "INSERT INTO скидка (значение) VALUES (?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, value);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Ошибка при добавлении скидки: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteDiscountById(int id) {
        String query = "DELETE FROM скидка WHERE id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Ошибка при удалении скидки: " + e.getMessage());
            return false;
        }
    }
}
