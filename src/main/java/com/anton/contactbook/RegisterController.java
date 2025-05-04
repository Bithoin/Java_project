package com.anton.contactbook;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

public class RegisterController {

    @FXML
    private Button btnBack; // Кнопка "Назад"
    @FXML
    private Button btnFurther1; // Кнопка Далі
    @FXML
    private Label messageLabel1;
    @FXML
    private Label messageLabel2;
    @FXML
    private Label messageLabel3;
    @FXML
    private TextField TextField1;
    @FXML
    private TextField TextField2;
    @FXML
    private PasswordField TextField3;

    // Обработчик кнопки "Назад" - возвращает на главное меню
    @FXML
    public void onBackClick(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnBack.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LoginRegister.fxml"));
        Scene mainScene = new Scene(fxmlLoader.load());
        stage.setScene(mainScene);
    }

    // Обработчик кнопки "Далі" - проверяет данные и записывает в файл
    @FXML
    public void onFurtherClick1(ActionEvent event) throws IOException {
        String name = TextField1.getText();
        String login = TextField2.getText();
        String password = TextField3.getText();

        boolean isValid = true;  // Флаг, который будет указывать, прошли ли все проверки

        // Очистка сообщений об ошибках
        messageLabel1.setText("");
        messageLabel2.setText("");
        messageLabel3.setText("");

        // Проверка, что поля не пустые
        if (name.isEmpty() || login.isEmpty() || password.isEmpty()) {
            messageLabel1.setText("Заповніть поле!");
            messageLabel2.setText("Заповніть поле!");
            messageLabel3.setText("Заповніть поле!");
            isValid = false; // Если поле пустое, то проверка не прошла
        }

        // Проверка логина на уникальность
        if (isLoginExists(login)) {
            messageLabel2.setText("Такий логін вже існує.");
            isValid = false;
        }

        // Проверка других условий (например, длина логина, пароль и т.д.)
        if (!name.matches("[A-Za-zА-Яа-яЁё]{2,30}")) {
            messageLabel1.setText("Ім'я має містити лише літери, від 2 до 30 символів.");
            isValid = false;
        }

        if (login.length() < 6 || login.length() > 20) {
            messageLabel2.setText("Логін має бути від 6 до 20 символів.");
            isValid = false;
        }

        if (!password.matches("[A-Z][A-Za-z0-9]{5,19}")) {
            messageLabel3.setText("Пароль повинен починатися з великої літери та містити від 6 до 20 символів.");
            isValid = false;
        }

        // Если все проверки прошли
        if (isValid) {
            // Запись данных в файл
            writeToFile(name, login, password);

            // Переход на окно с контактами и передача логина
            Stage stage = (Stage) btnFurther1.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ConctactsWindow.fxml"));
            Scene mainScene = new Scene(fxmlLoader.load());
            ContactsWindowController controller = fxmlLoader.getController();
            controller.setUserLogin(login); // Передаем логин в следующий экран

            stage.setScene(mainScene);
            creatingImportantFolders();
        }
    }

    private void writeToFile(String name, String login, String password) {
        // Указываем путь к папке fileFolder, в корневой директории проекта
        File folder = new File("fileFolder");

        // Если папка не существует, создаем её
        if (!folder.exists()) {
            folder.mkdirs();  // Создаём папку
        }

        // Указываем путь к файлу users.txt
        File file = new File(folder, "users.txt");

        // Открываем BufferedWriter для записи в файл (с добавлением новых строк)
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            // Записываем данные нового пользователя в файл с новой строки
            writer.write("Name: " + name + ", Login: " + login + ", Password: " + password + "," + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isLoginExists(String login) {
        // Путь к файлу, где хранятся данные пользователей
        File file = new File("fileFolder/users.txt");

        // Если файл существует, проверяем его содержимое
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                // Читаем файл построчно
                while ((line = reader.readLine()) != null) {
                    // Проверяем, если логин уже существует в файле
                    if (line.contains("Login: " + login + ",")) {
                        return true;  // Логин найден, значит, он уже существует
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private void creatingImportantFolders() {
        String nameSurname = TextField2.getText().trim(); // Убираем лишние пробелы
        // Формируем путь для папки пользователя
        String baseDirectory = System.getProperty("user.dir"); // Путь к папке ContactBook
        String userDirectory = baseDirectory + "/" + nameSurname; // Папка с именем пользователя
        String avatarsDirectory = userDirectory + "/avatars"; // Папка avatars внутри папки пользователя
        // Путь к текстовому файлу в папке пользователя
        String textFilePath = userDirectory + "/dataContacts.txt";
        try {
            // Создаём папки для пользователя и для аватаров, если они ещё не существуют
            Files.createDirectories(Paths.get(avatarsDirectory));
            // Создаём текстовый файл в папке пользователя
            Files.createFile(Paths.get(textFilePath));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
