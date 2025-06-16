package app;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class RentalAdminPanel {
    private static final Scanner scanner = new Scanner(System.in);
    private static final RentalDAO rentalDAO = new RentalDAO();

    public static void show() {
        while (true) {
            System.out.println("\n=== Управление прокатами ===");
            System.out.println("1. Показать все прокаты");
            System.out.println("2. Добавить прокат");
            System.out.println("3. Завершить прокат");
            System.out.println("0. Назад");
            System.out.print("Выбор: ");
            String input = scanner.nextLine();

            switch (input) {
                case "1" -> rentalDAO.getAllRentals().printAll();
                case "2" -> addRental();
                case "3" -> completeRental();
                case "0" -> { return; }
                default -> System.out.println("Неверный выбор.");
            }
        }
    }

    private static void addRental() {
        try {
            System.out.print("ID клиента: ");
            int clientId = Integer.parseInt(scanner.nextLine());

            System.out.print("ID автомобиля: ");
            int autoId = Integer.parseInt(scanner.nextLine());

            System.out.print("Дата выдачи (гггг-мм-дд): ");
            Date issueDate = parseDate(scanner.nextLine());

            System.out.print("Ожидаемая дата возврата (гггг-мм-дд): ");
            Date expectedReturn = parseDate(scanner.nextLine());

            boolean success = rentalDAO.addRental(clientId, autoId, issueDate, expectedReturn);
            System.out.println(success ? "Прокат добавлен." : "Ошибка при добавлении.");
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private static void completeRental() {
        try {
            System.out.print("ID проката для завершения: ");
            int rentalId = Integer.parseInt(scanner.nextLine());
            System.out.print("ID скидки (или пусто): ");
            String discountInput = scanner.nextLine();
            Integer discountId = discountInput.isEmpty() ? null : Integer.parseInt(discountInput);

            System.out.print("ID штрафа (или пусто): ");
            String penaltyInput = scanner.nextLine();
            Integer penaltyId = penaltyInput.isEmpty() ? null : Integer.parseInt(penaltyInput);



            boolean success = rentalDAO.completeRental(rentalId, discountId, penaltyId);
            System.out.println(success ? "Прокат завершён." : "Ошибка при завершении.");

        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private static Date parseDate(String input) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.parse(input);
    }
}
