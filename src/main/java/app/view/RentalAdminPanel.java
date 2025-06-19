package app.view;

import app.controller.RentalController;
import app.db.*;
import app.model.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Date;
import java.time.temporal.ChronoUnit;

public class RentalAdminPanel extends Application {

    private ComboBox<Client> clientBox;
    private ComboBox<Auto> autoBox;
    private DatePicker issueDatePicker;
    private DatePicker returnDatePicker;
    private ComboBox<Discount> discountBox;
    private ComboBox<Penalty> penaltyBox;
    private Label totalCostLabel;
    private Button addButton;
    private Button listViewButton;

    private final RentalController rentalController = new RentalController();

    @Override
    public void start(Stage stage) {
        stage.setTitle("Оформление проката");

        clientBox = new ComboBox<>();
        clientBox.setItems(FXCollections.observableArrayList(rentalController.getAllClients()));
        clientBox.setPromptText("Выберите клиента");
        setupComboBoxDisplay(clientBox);

        autoBox = new ComboBox<>();
        autoBox.setItems(FXCollections.observableArrayList(rentalController.getAllAutos()));
        autoBox.setPromptText("Выберите автомобиль");
        setupComboBoxDisplay(autoBox);

        issueDatePicker = new DatePicker();
        returnDatePicker = new DatePicker();

        discountBox = new ComboBox<>();
        discountBox.setItems(FXCollections.observableArrayList(rentalController.getAllDiscounts()));
        discountBox.setPromptText("Скидка (необязательно)");
        setupComboBoxDisplay(discountBox);

        penaltyBox = new ComboBox<>();
        penaltyBox.setItems(FXCollections.observableArrayList(rentalController.getAllPenalties()));
        penaltyBox.setPromptText("Штраф (необязательно)");
        setupComboBoxDisplay(penaltyBox);

        totalCostLabel = new Label("Итоговая стоимость: -");
        addButton = new Button("Оформить прокат");
        listViewButton = new Button("Таблица прокатов");

        issueDatePicker.valueProperty().addListener((obs, oldV, newV) -> calculateTotal());
        returnDatePicker.valueProperty().addListener((obs, oldV, newV) -> calculateTotal());
        autoBox.valueProperty().addListener((obs, oldV, newV) -> calculateTotal());
        discountBox.valueProperty().addListener((obs, oldV, newV) -> calculateTotal());
        penaltyBox.valueProperty().addListener((obs, oldV, newV) -> calculateTotal());

        addButton.setOnAction(e -> submitRental());
        listViewButton.setOnAction(e -> new RentalListPanel().start(new Stage()));

        GridPane form = new GridPane();
        form.setVgap(10);
        form.setHgap(10);
        form.setPadding(new Insets(20));
        form.add(new Label("Клиент:"), 0, 0);
        form.add(clientBox, 1, 0);
        form.add(new Label("Автомобиль:"), 0, 1);
        form.add(autoBox, 1, 1);
        form.add(new Label("Дата выдачи:"), 0, 2);
        form.add(issueDatePicker, 1, 2);
        form.add(new Label("Ожидаемая дата возврата:"), 0, 3);
        form.add(returnDatePicker, 1, 3);
        form.add(discountBox, 1, 4);
        form.add(penaltyBox, 1, 5);
        form.add(totalCostLabel, 1, 6);
        HBox buttons = new HBox(3, addButton, listViewButton);
        form.add(buttons, 1, 7);

        VBox layout = new VBox(form);
        layout.setStyle("-fx-background-color: linear-gradient(to bottom right, #E0FFFF, #ffffff);");

        stage.setScene(new Scene(layout, 600, 450));
        stage.show();
    }

    private void calculateTotal() {
        if (autoBox.getValue() == null || issueDatePicker.getValue() == null || returnDatePicker.getValue() == null)
            return;

        try {
            long days = ChronoUnit.DAYS.between(issueDatePicker.getValue(), returnDatePicker.getValue());
            if (days <= 0) {
                totalCostLabel.setText("Некорректные даты");
                return;
            }

            int costPerDay = Integer.parseInt(String.valueOf(autoBox.getValue().getCost_per_day()));
            int baseCost = (int) days * costPerDay;

            int discount = discountBox.getValue() != null ? discountBox.getValue().getValue() : 0;
            int penalty = penaltyBox.getValue() != null ? penaltyBox.getValue().getValue() : 0;

            int total = baseCost - (baseCost * discount / 100) + penalty;
            totalCostLabel.setText("Итоговая стоимость: " + total + "₽");

        } catch (Exception e) {
            totalCostLabel.setText("Ошибка при расчёте стоимости");
        }
    }

    private void submitRental() {
        if (clientBox.getValue() == null || autoBox.getValue() == null ||
                issueDatePicker.getValue() == null || returnDatePicker.getValue() == null) {
            showAlert("Заполните все обязательные поля.");
            return;
        }

        boolean success = rentalController.addRental(
                clientBox.getValue().getId(),
                autoBox.getValue().getId(),
                Date.valueOf(issueDatePicker.getValue()),
                Date.valueOf(returnDatePicker.getValue())
        );

        if (success) {
            showAlert("Прокат успешно добавлен!");
        } else {
            showAlert("Ошибка при добавлении проката.");
        }
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Информация");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    private <T> void setupComboBoxDisplay(ComboBox<T> box) {
        box.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(T item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.toString());
                }
            }
        });
        box.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(T item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.toString());
                }
            }
        });
    }
}
