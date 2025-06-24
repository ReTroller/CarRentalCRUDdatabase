package app.controller;

import app.db.DiscountDAO;

import app.model.Discounts;

public class DiscountController {

    private final DiscountDAO discountDAO;

    public DiscountController() {
        this.discountDAO = new DiscountDAO();
    }

    public Discounts getAllDiscounts() {
        return discountDAO.getAllDiscounts();
    }

    public boolean addDiscount(String valueStr, String desc) {
        try {
            int value = Integer.parseInt(valueStr.trim());
            if (desc == null || desc.isBlank()) {
                desc = "Без описания";
            }
            return discountDAO.addDiscount(value, desc);
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: значение скидки должно быть числом.");
            return false;
        }
    }

    public boolean deleteDiscount(String idStr) {
        try {
            int id = Integer.parseInt(idStr.trim());
            return discountDAO.deleteDiscountById(id);
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: ID должен быть числом.");
            return false;
        }
    }
}

