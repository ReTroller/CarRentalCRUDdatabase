package app.controller;

import app.db.*;
import app.model.*;
import javafx.collections.ObservableList;

import java.sql.Date;
import java.util.List;

public class RentalController {

    private final RentalDAO rentalDAO = new RentalDAO();
    private final ClientDAO clientDAO = new ClientDAO();
    private final AutoDAO autoDAO = new AutoDAO();
    private final DiscountDAO discountDAO = new DiscountDAO();
    private final PenaltyDAO penaltyDAO = new PenaltyDAO();

    public List<Client> getAllClients() {
        return clientDAO.getAllClients().getAll();
    }

    public List<Auto> getAllAutos() {
        return autoDAO.getAllAutos().getList();
    }

    public List<Discount> getAllDiscounts() {
        return discountDAO.getAllDiscounts().getList();
    }

    public List<Penalty> getAllPenalties() {
        return penaltyDAO.getAllPenalties().getList();
    }
    public ObservableList<Rental> getAllRentals() {
        return rentalDAO.getAllRentals().getList();
    }

    public boolean addRental(int clientId, int autoId, Date issueDate, Date returnDate) {
        return rentalDAO.addRental(clientId, autoId, issueDate, returnDate);
    }

    public boolean completeRental(String rentalIdStr, String discountIdStr, String penaltyIdStr, int totalCost) {
        try {
            int rentalId = Integer.parseInt(rentalIdStr.trim());
            Integer discountId = (discountIdStr == null || discountIdStr.isBlank()) ? null : Integer.parseInt(discountIdStr.trim());
            Integer penaltyId = (penaltyIdStr == null || penaltyIdStr.isBlank()) ? null : Integer.parseInt(penaltyIdStr.trim());
            return rentalDAO.completeRental(rentalId, discountId, penaltyId, totalCost);
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: Неверный формат ID или стоимости.");
            return false;
        }
    }

    public boolean deleteRental(String idStr) {
        try {
            int id = Integer.parseInt(idStr.trim());
            return rentalDAO.deleteRentalById(id);
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: ID должен быть числом.");
            return false;
        }
    }
}
