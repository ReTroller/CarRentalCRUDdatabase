package app.model;

import java.util.ArrayList;
import java.util.List;

public class Autos {
    private final List<Auto> autos = new ArrayList<>();

    public void add(Auto auto) {
        autos.add(auto);
    }

    public List<Auto> getList() {
        return autos;
    }


    public void printAll() {
        for (Auto auto : autos) {
            System.out.println(auto);
        }
    }

    public int size() {
        return autos.size();
    }
}
