package app.controller;

import app.db.AutoDAO;
import app.model.Auto;
import app.model.Autos;

public class AutoController {

    private final AutoDAO autoDAO;

    public AutoController() {
        this.autoDAO = new AutoDAO();
    }

    public Autos getAllAutos() {
        return autoDAO.getAllAutos();
    }

    public boolean addAuto(String brand, String type, String year, String cost) {
        if (brand == null || brand.isBlank() ||
                type == null || type.isBlank() ||
                year == null || year.isBlank() ||
                cost == null || cost.isBlank()) {
            return false;
        }

        try {
            Integer.parseInt(year);
            Integer.parseInt(cost);
        } catch (NumberFormatException e) {
            return false;
        }
        int costInt = Integer.parseInt(cost.trim());

        Auto auto = new Auto(0, brand.trim(), type.trim(), year.trim(), costInt);
        return autoDAO.addAuto(auto);
    }

    public boolean deleteAuto(String idStr) {
        try {
            int id = Integer.parseInt(idStr.trim());
            return autoDAO.deleteAutoById(id);
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
