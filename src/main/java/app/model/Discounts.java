package app.model;

import java.util.ArrayList;
import java.util.List;

public class Discounts {
    private ArrayList<Discount> list = new ArrayList<>();

    public void add(Discount discount) {
        list.add(discount);
    }
    public List<Discount> getList() {
        return list;
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
