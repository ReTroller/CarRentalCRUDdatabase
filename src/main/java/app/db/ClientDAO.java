package app.db;
import app.model.Client;
import app.model.Clients;

import java.sql.Connection;

import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientDAO {

    public Clients getAllClients() {
        Clients clients = new Clients();

        try {
            Connection connection = DBConnection.getConnection();
            String query = "SELECT * FROM клиент";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                int id = result.getInt("id");
                String firstName = result.getString("имя");
                String lastName = result.getString("фамилия");
                String middleName = result.getString("отчество");
                String address = result.getString("адрес");
                String phone = result.getString("телефон");

                Client client = new Client(id, firstName, lastName, middleName, address, phone);
                clients.add(client);
            }

            connection.close();
        } catch (SQLException e) {
            System.out.println("Ошибка при получении клиентов: " + e.getMessage());
        }

        return clients;
    }
    public boolean addClient(Client client) {
        String query = "INSERT INTO клиент (имя, фамилия, отчество, адрес, телефон) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, client.getFirstName());
            statement.setString(2, client.getLastName());
            statement.setString(3, client.getMiddleName());
            statement.setString(4, client.getAddress());
            statement.setString(5, client.getPhone());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            System.out.println("Ошибка при добавлении клиента: " + e.getMessage());
            return false;
        }
    }
    public boolean deleteClientById(int id) {
        String query = "DELETE FROM клиент WHERE id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            System.out.println("Ошибка при удалении клиента: " + e.getMessage());
            return false;
        }
    }
    public Client getClientById(int id) {
        String query = "SELECT * FROM клиент WHERE id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                String firstName = result.getString("имя");
                String lastName = result.getString("фамилия");
                String middleName = result.getString("отчество");
                String address = result.getString("адрес");
                String phone = result.getString("телефон");

                return new Client(id, firstName, lastName, middleName, address, phone);
            }

        } catch (SQLException e) {
            System.out.println("Ошибка при поиске клиента: " + e.getMessage());

        }

        return null;
    }


}
