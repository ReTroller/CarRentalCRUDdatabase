package app;

import java.sql.*;
import java.util.Date;

public class RentalDAO {

    public Rentals getAllRentals() {
        Rentals rentals = new Rentals();

        String query = "SELECT * FROM прокат";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id_проката");
                int clientId = rs.getInt("id_клиента");
                int autoId = rs.getInt("id_автомобиля");
                Date issueDate = rs.getDate("дата_выдачи");
                Date expectedReturn = rs.getDate("ожидаемая_дата_возврата");
                Integer discountId = rs.getObject("id_скидки") != null ? rs.getInt("id_скидки") : null;
                Integer penaltyId = rs.getObject("id_штрафа") != null ? rs.getInt("id_штрафа") : null;
                int total = rs.getInt("стоимость_проката");

                Rental rental = new Rental(id, clientId, autoId, issueDate, expectedReturn, discountId, penaltyId, total);
                rentals.add(rental);
            }

        } catch (SQLException e) {
            System.out.println("Ошибка при получении прокатов: " + e.getMessage());
        }

        return rentals;
    }

    public boolean addRental(int clientId, int autoId, Date issueDate, Date expectedReturn) {
        String query = "INSERT INTO прокат (id_клиента, id_автомобиля, дата_выдачи, ожидаемая_дата_возврата) VALUES (?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, clientId);
            stmt.setInt(2, autoId);
            stmt.setDate(3, new java.sql.Date(issueDate.getTime()));
            stmt.setDate(4, new java.sql.Date(expectedReturn.getTime()));

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Ошибка при добавлении проката: " + e.getMessage());
            return false;
        }
    }

    public boolean completeRental(int rentalId, Integer discountId, Integer penaltyId) {
        String getRentalQuery = "SELECT id_автомобиля, дата_выдачи, ожидаемая_дата_возврата FROM прокат WHERE id_проката = ?";
        String getPriceQuery = "SELECT стоимость_в_сутки FROM автомобиль WHERE id_автомобиля = ?";
        String getDiscountQuery = "SELECT значение FROM скидка WHERE id = ?";
        String getPenaltyQuery = "SELECT значение FROM штраф WHERE id = ?";
        String updateRentalQuery = "UPDATE прокат SET id_скидки = ?, id_штрафа = ?, стоимость_проката = ? WHERE id_проката = ?";

        try (Connection connection = DBConnection.getConnection()) {

            int autoId;
            Date startDate, endDate;

            try (PreparedStatement stmt = connection.prepareStatement(getRentalQuery)) {
                stmt.setInt(1, rentalId);
                ResultSet rs = stmt.executeQuery();

                if (!rs.next()) {
                    System.out.println("Прокат с таким ID не найден.");
                    return false;
                }

                autoId = rs.getInt("id_автомобиля");
                startDate = rs.getDate("дата_выдачи");
                endDate = rs.getDate("ожидаемая_дата_возврата");
            }

            int dailyPrice;
            try (PreparedStatement stmt = connection.prepareStatement(getPriceQuery)) {
                stmt.setInt(1, autoId);
                ResultSet rs = stmt.executeQuery();
                if (!rs.next()) return false;
                dailyPrice = rs.getInt("стоимость_в_сутки");
            }

            long diffMs = endDate.getTime() - startDate.getTime();
            int days = (int) (diffMs / (1000 * 60 * 60 * 24));
            if (days < 1) days = 1; // Минимум 1 день

            int total = dailyPrice * days;

            if (discountId != null) {
                try (PreparedStatement stmt = connection.prepareStatement(getDiscountQuery)) {
                    stmt.setInt(1, discountId);
                    ResultSet rs = stmt.executeQuery();
                    if (rs.next()) {
                        int discountPercent = rs.getInt("значение");
                        total -= total * discountPercent / 100;
                    }
                }
            }

            if (penaltyId != null) {
                try (PreparedStatement stmt = connection.prepareStatement(getPenaltyQuery)) {
                    stmt.setInt(1, penaltyId);
                    ResultSet rs = stmt.executeQuery();
                    if (rs.next()) {
                        int penaltyPercent = rs.getInt("значение");
                        total += total * penaltyPercent / 100;
                    }
                }
            }

            try (PreparedStatement stmt = connection.prepareStatement(updateRentalQuery)) {
                if (discountId != null) {
                    stmt.setInt(1, discountId);
                } else {
                    stmt.setNull(1, Types.INTEGER);
                }

                if (penaltyId != null) {
                    stmt.setInt(2, penaltyId);
                } else {
                    stmt.setNull(2, Types.INTEGER);
                }

                stmt.setInt(3, total);
                stmt.setInt(4, rentalId);

                return stmt.executeUpdate() > 0;
            }

        } catch (SQLException e) {
            System.out.println("Ошибка при завершении проката: " + e.getMessage());
            return false;
        }
    }



    public boolean deleteRentalById(int id) {
        String query = "DELETE FROM прокат WHERE id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Ошибка при удалении проката: " + e.getMessage());
            return false;
        }
    }
}
