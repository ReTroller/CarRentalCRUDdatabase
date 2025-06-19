package app.view;

import app.controller.RentalController;
import app.model.Rental;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RentalListPanel extends Application {

    private final RentalController rentalController = new RentalController();

    @Override
    public void start(Stage stage) {
        stage.setTitle("Список всех прокатов");

        TableView<Rental> table = new TableView<>();

        TableColumn<Rental, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Rental, Integer> clientCol = new TableColumn<>("ID клиента");
        clientCol.setCellValueFactory(new PropertyValueFactory<>("clientId"));

        TableColumn<Rental, Integer> autoCol = new TableColumn<>("ID авто");
        autoCol.setCellValueFactory(new PropertyValueFactory<>("autoId"));

        TableColumn<Rental, java.util.Date> issueCol = new TableColumn<>("Дата выдачи");
        issueCol.setCellValueFactory(new PropertyValueFactory<>("issueDate"));

        TableColumn<Rental, java.util.Date> returnCol = new TableColumn<>("Ожидаемая возврата");
        returnCol.setCellValueFactory(new PropertyValueFactory<>("expectedReturnDate"));

        TableColumn<Rental, Integer> discountCol = new TableColumn<>("ID скидки");
        discountCol.setCellValueFactory(new PropertyValueFactory<>("discountId"));

        TableColumn<Rental, Integer> penaltyCol = new TableColumn<>("ID штрафа");
        penaltyCol.setCellValueFactory(new PropertyValueFactory<>("penaltyId"));

        TableColumn<Rental, Integer> totalCol = new TableColumn<>("Стоимость");
        totalCol.setCellValueFactory(new PropertyValueFactory<>("totalCost"));

        table.getColumns().addAll(idCol, clientCol, autoCol, issueCol, returnCol, discountCol, penaltyCol, totalCol);
        table.setItems(rentalController.getAllRentals());
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        Button backButton = new Button("Назад");
        backButton.setOnAction(e -> stage.close());

        VBox layout = new VBox(10, table, backButton);
        layout.setPadding(new Insets(20));

        stage.setScene(new Scene(layout, 800, 400));
        stage.show();
    }
}
