package app.view;

import app.controller.PenaltyController;
import app.model.Penalty;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PenaltyAdminPanel extends Application {

    private TableView<Penalty> table;
    private TextField valueField;
    private TextField descField;
    private TextField deleteIdField;
    private final PenaltyController penaltyController = new PenaltyController();

    @Override
    public void start(Stage stage) {
        stage.setTitle("Управление штрафами");

        table = new TableView<>();
        TableColumn<Penalty, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Penalty, Integer> valueCol = new TableColumn<>("Штраф ($)");
        valueCol.setCellValueFactory(new PropertyValueFactory<>("value"));

        TableColumn<Penalty, String> descCol = new TableColumn<>("Описание");
        descCol.setCellValueFactory(new PropertyValueFactory<>("description"));

        table.getColumns().addAll(idCol, valueCol, descCol);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        refreshTable();

        valueField = new TextField();
        valueField.setPromptText("Штраф ($)");
        descField = new TextField();
        descField.setPromptText("Описание");
        Button addButton = new Button("Добавить");
        addButton.setOnAction(e -> handleAddPenalty());

        deleteIdField = new TextField();
        deleteIdField.setPromptText("ID для удаления");
        Button deleteButton = new Button("Удалить");
        deleteButton.setOnAction(e -> handleDeletePenalty());

        Button backButton = new Button("Назад");
        backButton.setOnAction(e -> stage.close());

        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);
        form.setPadding(new Insets(10));
        form.add(new Label("Новый штраф:"), 0, 0);
        form.add(valueField, 1, 0);
        form.add(descField, 2, 0);
        form.add(addButton, 3, 0);
        form.add(new Label("Удаление по ID:"), 0, 1);
        form.add(deleteIdField, 1, 1);
        form.add(deleteButton, 3, 1);

        VBox layout = new VBox(10, table, form, backButton);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: linear-gradient(to bottom right, #E0FFFF, #ffffff);");

        stage.setScene(new Scene(layout, 700, 400));
        stage.show();
    }

    private void refreshTable() {
        table.getItems().setAll(penaltyController.getAllPenalties().getList());
    }

    private void handleAddPenalty() {
        boolean success = penaltyController.addPenalty(valueField.getText(), descField.getText());
        if (success) {
            valueField.clear();
            descField.clear();
            refreshTable();
        } else {
            showAlert("Ошибка при добавлении штрафа.");
        }
    }

    private void handleDeletePenalty() {
        boolean success = penaltyController.deletePenalty(deleteIdField.getText());
        if (success) {
            deleteIdField.clear();
            refreshTable();
        } else {
            showAlert("Ошибка при удалении штрафа.");
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Информация");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
