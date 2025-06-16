package app; // если все в app

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите роль (админ / пользователь): ");
        String role = scanner.nextLine();

        if (role.equalsIgnoreCase("админ")) {
            AdminMenu.show();
        } else {
            ClientMenu.show();
        }
    }
}
