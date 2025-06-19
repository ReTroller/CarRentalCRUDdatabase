package app.model;

import java.util.Date;

public class Rental {
    private int id;
    private int clientId;
    private int autoId;
    private Date issueDate;
    private Date expectedReturnDate;
    private Date actualReturnDate;
    private Integer discountId;
    private Integer penaltyId;
    private int totalCost;

    public Rental(int id, int clientId, int autoId, Date issueDate, Date expectedReturnDate,
                  Integer discountId, Integer penaltyId, int totalCost) {
        this.id = id;
        this.clientId = clientId;
        this.autoId = autoId;
        this.issueDate = issueDate;
        this.expectedReturnDate = expectedReturnDate;
        this.actualReturnDate = actualReturnDate;
        this.discountId = discountId;
        this.penaltyId = penaltyId;
        this.totalCost = totalCost;
    }

    public int getId() { return id; }
    public int getClientId() { return clientId; }
    public int getAutoId() { return autoId; }
    public Date getIssueDate() { return issueDate; }
    public Date getExpectedReturnDate() { return expectedReturnDate; }
    public Date getActualReturnDate() { return actualReturnDate; }
    public Integer getDiscountId() { return discountId; }
    public Integer getPenaltyId() { return penaltyId; }
    public int getTotalCost() { return totalCost; }

    @Override
    public String toString() {
        return "Прокат #" + id +
                " | Клиент: " + clientId +
                " | Авто: " + autoId +
                " | Выдан: " + issueDate +
                " | План. возврат: " + expectedReturnDate +
                " | Факт. возврат: " + (actualReturnDate != null ? actualReturnDate : "-") +
                " | Скидка: " + (discountId != null ? discountId : "-") +
                " | Штраф: " + (penaltyId != null ? penaltyId : "-") +
                " | Сумма: " + totalCost + "₽";
    }
}
