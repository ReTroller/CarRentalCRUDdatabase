package app.controller;

import app.db.PenaltyDAO;
import app.model.Penalty;
import app.model.Penalties;

public class PenaltyController {

    private final PenaltyDAO penaltyDAO;

    public PenaltyController() {
        this.penaltyDAO = new PenaltyDAO();
    }

    public Penalties getAllPenalties() {
        return penaltyDAO.getAllPenalties();
    }

    public boolean addPenalty(String valueStr, String desc) {
        try {
            int value = Integer.parseInt(valueStr.trim());
            if (desc == null || desc.isBlank()) {
                desc = "Без описания";
            }
            return penaltyDAO.addPenalty(value, desc);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean deletePenalty(String idStr) {
        try {
            int id = Integer.parseInt(idStr.trim());
            return penaltyDAO.deletePenaltyById(id);
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

