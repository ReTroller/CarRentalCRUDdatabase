package app.model;

import java.util.ArrayList;
import java.util.List;

public class Penalties {
    private ArrayList<Penalty> list = new ArrayList<>();

    public void add(Penalty penalty) {
        list.add(penalty);
    }
    public List<Penalty> getList() {
        return list;
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
