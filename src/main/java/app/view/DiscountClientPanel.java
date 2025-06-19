package app.view;

import app.db.DiscountDAO;
import app.model.Discount;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DiscountClientPanel extends Application {

    @Override
    public void start(Stage stage) {
        Label title = new Label("Просмотр доступных скидок");

        TableView<Discount> table = new TableView<>();

        TableColumn<Discount, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Discount, Integer> valueCol = new TableColumn<>("Скидка (%)");
        valueCol.setCellValueFactory(new PropertyValueFactory<>("value"));

        TableColumn<Discount, String> descCol = new TableColumn<>("Описание");
        descCol.setCellValueFactory(new PropertyValueFactory<>("description"));

        table.getColumns().addAll(idCol, valueCol, descCol);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        Button backButton = new Button("Назад");
        backButton.setOnAction(e -> stage.close());

        VBox layout = new VBox(10, title, table, backButton);
        layout.setPadding(new Insets(20));

        DiscountDAO dao = new DiscountDAO();
        table.getItems().addAll(dao.getAllDiscounts().getList());

        Scene scene = new Scene(layout, 450, 300);
        stage.setTitle("Скидки - Пользователь");
        stage.setScene(scene);
        stage.show();
    }
}


//package app.view;
//
//import app.db.DiscountDAO;
//
//public class DiscountClientPanel {
//    public static void show() {
//        DiscountDAO dao = new DiscountDAO();
//        System.out.println("\n=== Доступные скидки ===");
//        dao.getAllDiscounts().printAll();
//    }
//}
