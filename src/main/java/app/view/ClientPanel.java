package app.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ClientPanel extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Панель пользователя");

        Label title = new Label("Панель пользователя");
        title.setStyle("-fx-text-fill: #0D47A1; -fx-font-size: 14px; -fx-font-weight: bold;");

        Button viewAutosButton = new Button("Посмотреть доступные автомобили");
        Button viewDiscountsButton = new Button("Посмотреть доступные скидки");
        Button backButton = new Button("Выход");

        String buttonStyle = "-fx-border-color: #0D47A1;" +
                "-fx-background-radius: 4;" +
                "-fx-border-radius: 4;" +
                "-fx-background-color: #ffffff;" +
                "-fx-text-fill: #0D47A1;";

        viewAutosButton.setStyle(buttonStyle);
        viewDiscountsButton.setStyle(buttonStyle);
        backButton.setStyle(buttonStyle);

        viewAutosButton.setOnAction(e -> new AutoClientPanel().start(new Stage()));

        viewDiscountsButton.setOnAction(e -> new DiscountClientPanel().start(new Stage()));
        backButton.setOnAction(e -> {
            new LoginPanel().start(new Stage());
            stage.close();});

        VBox layout = new VBox(6, title, viewAutosButton, viewDiscountsButton, backButton);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(30));
        layout.setStyle("-fx-background-color: linear-gradient(to bottom right, #ffffff, #E0FFFF);");

        stage.setScene(new Scene(layout, 400, 220));
        stage.show();
    }
}
