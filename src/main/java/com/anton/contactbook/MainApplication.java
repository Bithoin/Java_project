package com.anton.contactbook;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import java.io.InputStream;

public class MainApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginRegister.fxml"));
        Scene scene = new Scene(loader.load());
        InputStream iconStream = getClass().getResourceAsStream("/images/HomeIcon.png");
        if (iconStream == null) {
            System.out.println("Icon not found");
        } else {
            Image icon = new Image(iconStream);
            primaryStage.getIcons().add(icon);
        }
        primaryStage.setTitle("Контактна Книга");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}