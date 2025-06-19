package app.db;

import app.model.Discount;
import app.model.Discounts;

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
        }

        return discounts;
    }

    public boolean addDiscount(int value, String desc) {
        String query = "INSERT INTO скидка (значение, описание) VALUES (?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, value);
            stmt.setString(2, desc);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            return false;
        }
    }

    public boolean deleteDiscountById(int id) {
        String query = "DELETE FROM скидка WHERE id_скидки = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            return false;
        }
    }
}
