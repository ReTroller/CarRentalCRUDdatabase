package app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AutoDAO {

    public Autos getAllAutos() {
        Autos autos = new Autos();

        try {
            Connection connection = DBConnection.getConnection();
            String query = "SELECT * FROM автомобиль";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                int id = result.getInt("id_автомобиля");
                String brand = result.getString("марка");
                String type = result.getString("тип");
                String year = result.getString("год_выпуска");
                String cost = result.getString("стоимость_в_сутки");

                Auto auto = new Auto(id, brand, type, year, cost);
                autos.add(auto);
            }

            connection.close();
        } catch (SQLException e) {
            System.out.println("Ошибка при получении автомобилей: " + e.getMessage());
        }

        return autos;
    }

    public boolean addAuto(Auto auto) {
        String query = "INSERT INTO автомобиль (марка, тип, год_выпуска, стоимость_в_сутки) VALUES (?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, auto.getBrandName());
            statement.setString(2, auto.getType());
            statement.setString(3, auto.getManufacture_year());
            statement.setString(4, auto.getCost_per_day());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            System.out.println("Ошибка при добавлении автомобиля: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteAutoById(int id) {
        String query = "DELETE FROM автомобиль WHERE id_автомобиля = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            System.out.println("Ошибка при удалении автомобиля: " + e.getMessage());
            return false;
        }
    }

    public boolean getAutoById(int id) {
        String query = "SELECT * FROM автомобиль WHERE id_автомобиля = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                String brand = result.getString("марка");
                String type = result.getString("тип");
                String year = result.getString("год_выпуска");
                String cost = result.getString("стоимость_в_сутки");

                Auto auto = new Auto(id, brand, type, year, cost);
                System.out.println("Найден автомобиль:");
                System.out.println(auto);
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Ошибка при поиске автомобиля: " + e.getMessage());
        }

        return false;
    }
}
