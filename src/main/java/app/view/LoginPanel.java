package app.view;

import app.controller.UserAuthenticalController;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class LoginPanel extends Application {

    private final UserAuthenticalController userController = new UserAuthenticalController();

    private void alert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Информация");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Вход в систему");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);
        grid.setStyle("-fx-background-color: linear-gradient(to bottom right, #ffffff, #a0e7e5);");

        Label loginLabel = new Label("Логин:");
        loginLabel.setStyle("-fx-text-fill: #191970");
        TextField loginField = new TextField();
        loginField.setStyle("-fx-border-color: #191970; -fx-background-radius: 4; -fx-border-radius: 4");

        Label passLabel = new Label("Пароль:");
        passLabel.setStyle("-fx-text-fill: #191970");
        PasswordField passField = new PasswordField();
        passField.setStyle("-fx-border-color: #191970; -fx-background-radius: 4; -fx-border-radius: 4");

        Button loginButton = new Button("Войти");
        Button viewButton = new Button("Режим просмотра");
        Label messageLabel = new Label();

        loginButton.setStyle("-fx-background-color:#FFFFFF; -fx-border-color: #191970; -fx-background-radius: 4; -fx-border-radius: 4; -fx-text-fill: #191970");
        viewButton.setStyle("-fx-background-color:#FFFFFF; -fx-border-color: #191970; -fx-background-radius: 4; -fx-border-radius: 4; -fx-text-fill: #191970");

        HBox buttons = new HBox(10, loginButton, viewButton);

        grid.add(loginLabel, 0, 0);
        grid.add(loginField, 1, 0);
        grid.add(passLabel, 0, 1);
        grid.add(passField, 1, 1);
        grid.add(buttons, 1, 2);
        grid.add(messageLabel, 1, 3);
        grid.setHgap(1);

        viewButton.setOnAction(e -> {
            new ClientPanel().start(new Stage());
            primaryStage.close();
        });

        loginButton.setOnAction(e -> {
            String login = loginField.getText().trim();
            String password = passField.getText().trim();

            if (login.isEmpty() || password.isEmpty()) {
                messageLabel.setText("Введите логин и пароль.");
                return;
            }

            String status = userController.checkUser(login, password);

            if ("администратор".equals(status)) {
                new AdminPanel().start(new Stage());
                primaryStage.close();
            } else if ("пользователь".equals(status)) {
                alert("Администратор не найден");
            } else {
                alert("Неверные данные для входа");
            }
        });

        Scene scene = new Scene(grid, 400, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
