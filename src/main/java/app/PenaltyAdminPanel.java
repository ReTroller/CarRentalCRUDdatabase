package app;

import java.util.Scanner;

public class PenaltyAdminPanel {
    public static void show() {
        Scanner scanner = new Scanner(System.in);
        PenaltyDAO dao = new PenaltyDAO();

        while (true) {
            System.out.println("\n=== Управление штрафами ===");
            System.out.println("1. Показать все");
            System.out.println("2. Добавить");
            System.out.println("3. Удалить");
            System.out.println("0. Назад");
            System.out.print("Выбор: ");
            String input = scanner.nextLine();

            switch (input) {
                case "1" -> dao.getAllPenalties().printAll();
                case "2" -> {
                    System.out.print("Сумма штрафа (₽): ");
                    int value = Integer.parseInt(scanner.nextLine());
                    System.out.print("Описание: ");
                    String description = scanner.nextLine();

                    boolean added = dao.addPenalty(value, description);
                    System.out.println(added ? "Штраф добавлен." : "Ошибка при добавлении.");
                }
                case "3" -> {
                    System.out.print("ID штрафа для удаления: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    boolean deleted = dao.deletePenaltyById(id);
                    System.out.println(deleted ? "Удалено." : "Не найдено.");
                }
                case "0" -> { return; }
                default -> System.out.println("Неверный выбор.");
            }
        }
    }
}
