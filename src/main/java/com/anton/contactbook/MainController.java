package com.anton.contactbook;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import java.io.IOException;

public class MainController {

    @FXML
    private Button btnAuth; // Кнопка "Авторизація"
    @FXML
    private Button btnRegister; // Кнопка "Регістрація"

    // Обработчик кнопки "Авторизація"
    @FXML
    public void onAuthClick(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnAuth.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Scene loginScene = new Scene(fxmlLoader.load());
        stage.setScene(loginScene);
    }

    // Обработчик кнопки "Регістрація"
    @FXML
    public void onRegisterClick(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnRegister.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RegisterUser.fxml"));
        Scene registerScene = new Scene(fxmlLoader.load());
        stage.setScene(registerScene);
    }
}
