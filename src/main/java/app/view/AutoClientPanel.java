package app.view;

import app.db.AutoDAO;
import app.model.Auto;
import app.model.Autos;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AutoClientPanel extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Автомобили - Пользователь");

        Label title = new Label("Доступные автомобили");

        TableView<Auto> table = new TableView<>();

        TableColumn<Auto, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Auto, String> brandCol = new TableColumn<>("Марка");
        brandCol.setCellValueFactory(new PropertyValueFactory<>("brandName"));

        TableColumn<Auto, String> typeCol = new TableColumn<>("Тип");
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));

        TableColumn<Auto, String> yearCol = new TableColumn<>("Год выпуска");
        yearCol.setCellValueFactory(new PropertyValueFactory<>("manufacture_year"));

        TableColumn<Auto, String> costCol = new TableColumn<>("Стоимость/сутки");
        costCol.setCellValueFactory(new PropertyValueFactory<>("cost_per_day"));

        table.getColumns().addAll(idCol, brandCol, typeCol, yearCol, costCol);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        Autos autos = new AutoDAO().getAllAutos();
        table.getItems().addAll(autos.getList());

        Button backButton = new Button("Назад");
        backButton.setOnAction(e -> stage.close());

        VBox layout = new VBox(10, title, table, backButton);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: linear-gradient(to bottom right, #E0FFFF, #ffffff);");

        stage.setScene(new Scene(layout, 600, 400));
        stage.show();
    }
}
