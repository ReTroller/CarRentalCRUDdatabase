package app.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAuthenticationDAO {

    public static String checkUser(String login, String password) {
        String query = "SELECT статус FROM данные_пользователей WHERE логин = ? AND пароль = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                return result.getString("статус");
            } else {
                // Пользователь не найден — добавим его как "пользователь"

                return "пользователь";
            }

        } catch (SQLException e) {
            System.out.println("Ошибка при авторизации: " + e.getMessage());
        }

        return null;
    }


}
