package app;

import java.util.ArrayList;

public class Penalties {
    private ArrayList<Penalty> list = new ArrayList<>();

    public void add(Penalty penalty) {
        list.add(penalty);
    }

    public void printAll() {
        if (list.isEmpty()) {
            System.out.println("Список штрафов пуст.");
            return;
        }
        for (Penalty p : list) {
            System.out.println(p);
        }
    }
}
