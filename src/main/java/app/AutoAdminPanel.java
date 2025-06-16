package app;

import java.util.Scanner;

public class AutoAdminPanel {
    public static void show() {
        Scanner scanner = new Scanner(System.in);
        AutoDAO dao = new AutoDAO();

        while (true) {
            System.out.println("\n=== Автомобили ===");
            System.out.println("1. Показать все");
            System.out.println("2. Добавить");
            System.out.println("3. Удалить");
            System.out.println("4. Найти по ID");
            System.out.println("0. Назад");
            System.out.print("Выбор: ");
            String input = scanner.nextLine();

            switch (input) {
                case "1" -> dao.getAllAutos().printAll();
                case "2" -> {
                    System.out.print("Марка: ");
                    String brand = scanner.nextLine();
                    System.out.print("Тип: ");
                    String type = scanner.nextLine();
                    System.out.print("Год выпуска: ");
                    String year = scanner.nextLine();
                    System.out.print("Стоимость в сутки: ");
                    String cost = scanner.nextLine();

                    Auto auto = new Auto(0, brand, type, year, cost);
                    boolean added = dao.addAuto(auto);
                    System.out.println(added ? "Автомобиль добавлен." : "Ошибка при добавлении.");
                }
                case "3" -> {
                    System.out.print("ID автомобиля: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    boolean deleted = dao.deleteAutoById(id);
                    System.out.println(deleted ? "Удалено." : "Не найдено.");
                }
                case "4" -> {
                    System.out.print("ID автомобиля: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    boolean found = dao.getAutoById(id);
                    if (!found) System.out.println("Автомобиль не найден.");
                }
                case "0" -> {
                    return;
                }
                default -> System.out.println("Неверный выбор.");
            }
        }
    }
}

