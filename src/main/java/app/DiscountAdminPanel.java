package app;

import java.util.Scanner;

public class DiscountAdminPanel {
    public static void show() {
        Scanner scanner = new Scanner(System.in);
        DiscountDAO dao = new DiscountDAO();

        while (true) {
            System.out.println("\n=== Управление скидками ===");
            System.out.println("1. Показать все");
            System.out.println("2. Добавить");
            System.out.println("3. Удалить");
            System.out.println("0. Назад");
            System.out.print("Выбор: ");
            String input = scanner.nextLine();

            switch (input) {
                case "1" -> dao.getAllDiscounts().printAll();
                case "2" -> {
                    System.out.print("Введите размер скидки (%): ");
                    int value = Integer.parseInt(scanner.nextLine());
                    boolean added = dao.addDiscount(value);
                    System.out.println(added ? "Скидка добавлена." : "Ошибка при добавлении.");
                }
                case "3" -> {
                    System.out.print("ID скидки для удаления: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    boolean deleted = dao.deleteDiscountById(id);
                    System.out.println(deleted ? "Удалена." : "Не найдена.");
                }
                case "0" -> { return; }
                default -> System.out.println("Неверный выбор.");
            }
        }
    }
}
