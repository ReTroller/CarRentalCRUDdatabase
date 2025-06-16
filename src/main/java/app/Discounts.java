package app;

import java.util.ArrayList;

public class Discounts {
    private ArrayList<Discount> list = new ArrayList<>();

    public void add(Discount discount) {
        list.add(discount);
    }

    public void printAll() {
        if (list.isEmpty()) {
            System.out.println("Список скидок пуст.");
            return;
        }
        for (Discount d : list) {
            System.out.println(d);
        }
    }
}
