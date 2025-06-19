package app.db;

import app.model.Penalties;
import app.model.Penalty;

import java.sql.*;

public class PenaltyDAO {

    public Penalties getAllPenalties() {
        Penalties penalties = new Penalties();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement("SELECT * FROM штраф");
             ResultSet result = stmt.executeQuery()) {

            while (result.next()) {
                int id = result.getInt("id_штрафа");
                int value = result.getInt("значение");
                String description = result.getString("описание");

                penalties.add(new Penalty(id, value, description));
            }

        } catch (SQLException e) {
            System.out.println("Ошибка при получении штрафов: " + e.getMessage());
        }

        return penalties;
    }

    public boolean addPenalty(int value, String description) {
        String query = "INSERT INTO штраф (значение, описание) VALUES (?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, value);
            stmt.setString(2, description);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Ошибка при добавлении штрафа: " + e.getMessage());
            return false;
        }
    }

    public boolean deletePenaltyById(int id) {
        String query = "DELETE FROM штраф WHERE id_штрафа = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Ошибка при удалении штрафа: " + e.getMessage());
            return false;
        }
    }
}
