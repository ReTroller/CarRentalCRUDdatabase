package app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ClientMenu {

    public static void show() {
        Scanner scanner = new Scanner(System.in);
        ClientDAO dao = new ClientDAO();
        AutoDAO autodao = new AutoDAO();
        DiscountDAO discdao = new DiscountDAO();

        while (true) {
            System.out.println("\n=== Меню пользователя ===");

            System.out.println("1. Посмотреть доступные автомобили");
            System.out.println("2. Посмотреть все скидки");
            System.out.println("0. Выход");
            System.out.print("Выбор: ");
            String input = scanner.nextLine();

            switch (input) {
                case "1" -> autodao.getAllAutos().printAll();
                case "0" -> {
                    System.out.println("Выход...");
                    return;
                }
                case "2" -> discdao.getAllDiscounts().printAll();
                default -> System.out.println("Неверный выбор. Повторите.");
            }
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
