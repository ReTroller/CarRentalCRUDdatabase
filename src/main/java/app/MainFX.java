package app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import app.view.*;

public class MainFX extends Application {

    @Override
    public void start(Stage primaryStage) {
        new LoginPanel().start(primaryStage);
    }

    public static void main(String[] args) {
        launch();
    }
}