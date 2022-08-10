package it.unicam.cs.pa2122.TomaMatteo116781;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;


public class MainFX extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/TomaMatteo116781.fxml"));
            stage.getIcons().add(new Image(getClass().getResource("/AppIcon.png").toString()));
            stage.setTitle("Logo in Java");
            stage.setScene(new Scene(root, 1280, 720));
            stage.show();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}