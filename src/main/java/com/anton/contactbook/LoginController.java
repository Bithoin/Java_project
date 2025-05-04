package com.anton.contactbook;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Label;

public class LoginController {

    @FXML
    private Button btnBack; // Кнопка "Назад"
    @FXML
    private Button btnFurther; // Кнопка Далі
    @FXML
    private Label messageLabel4;
    @FXML
    private Label messageLabel5;
    @FXML
    private TextField TextField4;
    @FXML
    private PasswordField TextField5;

    // Обработчик кнопки "Назад" - возвращает на главное меню
    @FXML
    public void onBackClick(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnBack.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LoginRegister.fxml"));
        Scene mainScene = new Scene(fxmlLoader.load());
        stage.setScene(mainScene);
    }

    @FXML
    public void onFurtherClick(ActionEvent event) throws IOException {
        String login = TextField4.getText();
        String password = TextField5.getText();

        messageLabel4.setText("");
        messageLabel5.setText("");

        // Проверка на пустые поля
        if (login.isEmpty() || password.isEmpty()) {
            if (login.isEmpty()) {
                messageLabel4.setText("Логін не може бути порожнім!");
            }
            if (password.isEmpty()) {
                messageLabel5.setText("Пароль не може бути порожнім!");
            }
            return;
        }

        // Проверка пароля
        if (!isValidPassword(password)) {
            messageLabel5.setText("Пароль повинен починатися з великої літери та містити від 6 до 20 символів.");
            return;
        }

        // Проверка логина и пароля
        if (isValidLogin(login, password)) {
            // Переход на окно контактов и передаем логин
            Stage stage = (Stage) btnFurther.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ConctactsWindow.fxml"));
            Scene mainScene = new Scene(fxmlLoader.load());

            // Получаем контроллер и передаем логин
            ContactsWindowController controller = fxmlLoader.getController();
            controller.setUserLogin(login); // Передаем логин пользователя в следующий экран

            stage.setScene(mainScene);
        } else {
            messageLabel5.setText("Невірний логін або пароль.");
        }
    }

    private boolean isValidLogin(String login, String password) {
        File file = new File("fileFolder/users.txt");

        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.contains("Login: " + login + ",") && line.contains("Password: " + password + ",")) {
                        return true;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false; // Логин или пароль неверные
    }

    // Метод для проверки пароля
    private boolean isValidPassword(String password) {
        return password.length() >= 6 && password.length() <= 20 && password.matches(".*[A-Z].*");
    }
}
