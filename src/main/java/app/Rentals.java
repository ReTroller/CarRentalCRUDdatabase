package app;

import java.util.ArrayList;

public class Rentals {
    private ArrayList<Rental> list = new ArrayList<>();

    public void add(Rental rental) {
        list.add(rental);
    }

    public void printAll() {
        if (list.isEmpty()) {
            System.out.println("Список прокатов пуст.");
            return;
        }
        for (Rental r : list) {
            System.out.println(r);
        }
    }
}
