package app.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdminPanel extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Панель администратора");

        Label title = new Label("Панель администратора");
        title.setStyle("-fx-text-fill: #191970; -fx-font-size: 14px; -fx-font-weight: bold;");

        Button clientBtn = new Button("Управление клиентами");
        Button autoBtn = new Button("Управление автомобилями");
        Button discountBtn = new Button("Управление скидками");
        Button penaltyBtn = new Button("Управление штрафами");
        Button rentalBtn = new Button("Управление прокатами");

        String buttonStyle = "-fx-border-color: #0D47A1;" + "-fx-background-radius: 4;" + "-fx-border-radius: 4;" + "-fx-background-color: #ffffff;" + "-fx-text-fill: #0D47A1;";

        clientBtn.setStyle(buttonStyle);
        autoBtn.setStyle(buttonStyle);
        discountBtn.setStyle(buttonStyle);
        penaltyBtn.setStyle(buttonStyle);
        rentalBtn.setStyle(buttonStyle);

        clientBtn.setOnAction(e -> new ClientAdminPanel().start(new Stage()));
        autoBtn.setOnAction(e -> new AutoAdminPanel().start(new Stage()));
        discountBtn.setOnAction(e -> new DiscountAdminPanel().start(new Stage()));
        penaltyBtn.setOnAction(e -> new PenaltyAdminPanel().start(new Stage()));
        rentalBtn.setOnAction(e -> new RentalAdminPanel().start(new Stage()));

        VBox layout = new VBox(4);

        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(30));
        layout.getChildren().addAll(title, autoBtn, rentalBtn, clientBtn, penaltyBtn, discountBtn);
        layout.setStyle("-fx-background-color: linear-gradient(to bottom right, #E0FFFF, #ffffff);");

        stage.setScene(new Scene(layout, 600, 300));
        stage.show();
    }
}
