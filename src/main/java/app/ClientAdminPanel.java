package app;

import java.util.Scanner;

public class ClientAdminPanel {
    public static void show() {
        Scanner scanner = new Scanner(System.in);
        ClientDAO dao = new ClientDAO();

        while (true) {
            System.out.println("\n=== Клиенты ===");
            System.out.println("1. Показать всех");
            System.out.println("2. Добавить");
            System.out.println("3. Удалить");
            System.out.println("4. Найти по ID");
            System.out.println("0. Назад");
            System.out.print("Выбор: ");
            String input = scanner.nextLine();

            switch (input) {
                case "1" -> dao.getAllClients().printAll();
                case "2" -> {
                    System.out.print("Имя: ");
                    String firstName = scanner.nextLine();
                    System.out.print("Фамилия: ");
                    String lastName = scanner.nextLine();
                    System.out.print("Отчество: ");
                    String middleName = scanner.nextLine();
                    System.out.print("Адрес: ");
                    String address = scanner.nextLine();
                    System.out.print("Телефон: ");
                    String phone = scanner.nextLine();

                    Client client = new Client(0, firstName, lastName, middleName, address, phone);
                    boolean added = dao.addClient(client);
                    System.out.println(added ? "Клиент добавлен." : "Ошибка при добавлении.");
                }
                case "3" -> {
                    System.out.print("ID клиента: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    boolean deleted = dao.deleteClientById(id);
                    System.out.println(deleted ? "Клиент удалён." : "Не найден.");
                }
                case "4" -> {
                    System.out.print("ID клиента: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    Client found = dao.getClientById(id);

                }
                case "0" -> {
                    return;
                }
                default -> System.out.println("Неверный выбор.");
            }
        }
    }
}
