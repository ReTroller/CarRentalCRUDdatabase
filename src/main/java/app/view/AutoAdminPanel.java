package app.view;

import app.controller.AutoController;
import app.model.Auto;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AutoAdminPanel extends Application {

    private TableView<Auto> table;
    private TextField brandField;
    private TextField typeField;
    private TextField yearField;
    private TextField costField;
    private TextField deleteIdField;
    private final AutoController autoController = new AutoController();

    @Override
    public void start(Stage stage) {
        stage.setTitle("Управление автомобилями");

        table = new TableView<>();
        TableColumn<Auto, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Auto, String> brandCol = new TableColumn<>("Марка");
        brandCol.setCellValueFactory(new PropertyValueFactory<>("brandName"));

        TableColumn<Auto, String> typeCol = new TableColumn<>("Тип");
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));

        TableColumn<Auto, String> yearCol = new TableColumn<>("Год выпуска");
        yearCol.setCellValueFactory(new PropertyValueFactory<>("manufacture_year"));

        TableColumn<Auto, String> costCol = new TableColumn<>("Стоимость/день");
        costCol.setCellValueFactory(new PropertyValueFactory<>("cost_per_day"));

        table.getColumns().addAll(idCol, brandCol, typeCol, yearCol, costCol);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        refreshTable();

        brandField = new TextField();
        brandField.setPromptText("Марка");
        typeField = new TextField();
        typeField.setPromptText("Тип");
        yearField = new TextField();
        yearField.setPromptText("Год выпуска");
        costField = new TextField();
        costField.setPromptText("Цена в сутки");
        Button addButton = new Button("Добавить");
        addButton.setOnAction(e -> handleAddAuto());

        deleteIdField = new TextField();
        deleteIdField.setPromptText("ID для удаления");
        Button deleteButton = new Button("Удалить");
        deleteButton.setOnAction(e -> handleDeleteAuto());

        Button backButton = new Button("Назад");
        backButton.setOnAction(e -> stage.close());

        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);
        form.setPadding(new Insets(10));
        form.add(new Label("Новый автомобиль:"), 0, 0);
        form.add(brandField, 1, 0);
        form.add(typeField, 2, 0);
        form.add(yearField, 3, 0);
        form.add(costField, 4, 0);
        form.add(addButton, 5, 0);
        form.add(new Label("Удаление по ID:"), 0, 1);
        form.add(deleteIdField, 1, 1);
        form.add(deleteButton, 2, 1);

        VBox layout = new VBox(10, table, form, backButton);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: linear-gradient(to bottom right, #E0FFFF, #ffffff);");

        stage.setScene(new Scene(layout, 900, 450));
        stage.show();
    }

    private void refreshTable() {
        table.getItems().setAll(autoController.getAllAutos().getList());
    }

    private void handleAddAuto() {
        boolean success = autoController.addAuto(
                brandField.getText(),
                typeField.getText(),
                yearField.getText(),
                costField.getText()
        );
        if (success) {
            brandField.clear();
            typeField.clear();
            yearField.clear();
            costField.clear();
            refreshTable();
        } else {
            showAlert("Ошибка при добавлении автомобиля.");
        }
    }

    private void handleDeleteAuto() {
        boolean success = autoController.deleteAuto(deleteIdField.getText());
        if (success) {
            deleteIdField.clear();
            refreshTable();
        } else {
            showAlert("Ошибка при удалении автомобиля.");
        }
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Информация");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
