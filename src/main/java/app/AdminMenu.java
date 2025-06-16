package app;

import java.util.Scanner;

public class AdminMenu {
    public static void show() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Главное меню администратора ===");
            System.out.println("1. Управление клиентами");
            System.out.println("2. Управление автомобилями");
            System.out.println("3. Управление скидками");
            System.out.println("4. Управление штрафами");
            System.out.println("5. Управление прокатами");
            System.out.println("0. Выход");
            System.out.print("Выбор: ");
            String input = scanner.nextLine();

            switch (input) {
                case "1" -> ClientAdminPanel.show();
                case "2" -> AutoAdminPanel.show();
                case "3" -> DiscountAdminPanel.show();
                case "4" -> PenaltyAdminPanel.show();
                case "5" -> RentalAdminPanel.show();
                case "0" -> {
                    System.out.println("Выход...");
                    return;
                }
                default -> System.out.println("Неверный выбор. Повторите.");
            }
        }
    }
}
