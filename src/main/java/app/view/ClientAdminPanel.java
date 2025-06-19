package app.view;

import app.controller.ClientController;
import app.model.Client;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ClientAdminPanel extends Application {

    private TableView<Client> table;
    private TextField firstNameField;
    private TextField lastNameField;
    private TextField middleNameField;
    private TextField addressField;
    private TextField phoneField;
    private TextField deleteIdField;
    private final ClientController clientController = new ClientController();

    @Override
    public void start(Stage stage) {
        stage.setTitle("Управление клиентами");

        table = new TableView<>();
        TableColumn<Client, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Client, String> firstNameCol = new TableColumn<>("Имя");
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<Client, String> lastNameCol = new TableColumn<>("Фамилия");
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        TableColumn<Client, String> middleNameCol = new TableColumn<>("Отчество");
        middleNameCol.setCellValueFactory(new PropertyValueFactory<>("middleName"));

        TableColumn<Client, String> addressCol = new TableColumn<>("Адрес");
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));

        TableColumn<Client, String> phoneCol = new TableColumn<>("Телефон");
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));

        table.getColumns().addAll(idCol, firstNameCol, lastNameCol, middleNameCol, addressCol, phoneCol);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        refreshTable();

        firstNameField = new TextField();
        firstNameField.setPromptText("Имя");
        lastNameField = new TextField();
        lastNameField.setPromptText("Фамилия");
        middleNameField = new TextField();
        middleNameField.setPromptText("Отчество");
        addressField = new TextField();
        addressField.setPromptText("Адрес");
        phoneField = new TextField();
        phoneField.setPromptText("Телефон");
        Button addButton = new Button("Добавить");
        addButton.setOnAction(e -> handleAddClient());

        deleteIdField = new TextField();
        deleteIdField.setPromptText("ID для удаления");
        Button deleteButton = new Button("Удалить");
        deleteButton.setOnAction(e -> handleDeleteClient());

        Button backButton = new Button("Назад");
        backButton.setOnAction(e -> stage.close());

        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);
        form.setPadding(new Insets(10));
        form.add(new Label("Новый клиент:"), 0, 0);
        form.add(firstNameField, 1, 0);
        form.add(lastNameField, 2, 0);
        form.add(middleNameField, 3, 0);
        form.add(addressField, 4, 0);
        form.add(phoneField, 5, 0);
        form.add(addButton, 6, 0);
        form.add(new Label("Удаление по ID:"), 0, 1);
        form.add(deleteIdField, 1, 1);
        form.add(deleteButton, 6, 1);

        VBox layout = new VBox(10, table, form, backButton);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: linear-gradient(to bottom right, #E0FFFF, #ffffff);");

        stage.setScene(new Scene(layout, 900, 450));
        stage.show();
    }

    private void refreshTable() {
        table.getItems().setAll(clientController.getAllClients().getAll());
    }

    private void handleAddClient() {
        boolean success = clientController.addClient(
                firstNameField.getText(),
                lastNameField.getText(),
                middleNameField.getText(),
                addressField.getText(),
                phoneField.getText()
        );
        if (success) {
            firstNameField.clear();
            lastNameField.clear();
            middleNameField.clear();
            addressField.clear();
            phoneField.clear();
            refreshTable();
        } else {
            showAlert("Ошибка при добавлении клиента.");
        }
    }

    private void handleDeleteClient() {
        boolean success = clientController.deleteClient(deleteIdField.getText());
        if (success) {
            deleteIdField.clear();
            refreshTable();
        } else {
            showAlert("Ошибка при удалении клиента.");
        }
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
