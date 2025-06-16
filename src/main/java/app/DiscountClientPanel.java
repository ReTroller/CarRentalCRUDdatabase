package app;

public class DiscountClientPanel {
    public static void show() {
        DiscountDAO dao = new DiscountDAO();
        System.out.println("\n=== Доступные скидки ===");
        dao.getAllDiscounts().printAll();
    }
}
