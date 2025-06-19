//package app.console;
//
//import app.db.PenaltyDAO;
//import app.db.AutoDAO;
//import app.db.ClientDAO;
//import app.db.DiscountDAO;
//
//import java.util.Scanner;
//
//public class ClientMenu {
//
//    public static void show() {
//        Scanner scanner = new Scanner(System.in);
//        ClientDAO dao = new ClientDAO();
//        AutoDAO autodao = new AutoDAO();
//        DiscountDAO discdao = new DiscountDAO();
//        PenaltyDAO pendao = new PenaltyDAO();
//
//        while (true) {
//            System.out.println("\n=== Меню пользователя ===");
//
//            System.out.println("1. Посмотреть доступные автомобили");
//            System.out.println("2. Посмотреть все скидки");
//            System.out.println("0. Выход");
//            System.out.print("Выбор: ");
//            String input = scanner.nextLine();
//
//            switch (input) {
//                case "1" -> autodao.getAllAutos().printAll();
//                case "0" -> {
//                    System.out.println("Выход...");
//                    return;
//                }
//                case "2" -> discdao.getAllDiscounts().printAll();
//                case "3" -> pendao.getAllPenalties().printAll();
//                default -> System.out.println("Неверный выбор. Повторите.");
//
//            }
//        }
//    }
//
//
//}
