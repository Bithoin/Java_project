package com.anton.contactbook;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import javafx.scene.shape.Circle;
import java.nio.file.*;
import javafx.scene.control.TextField;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.animation.FadeTransition;
import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import javafx.scene.Node;
import java.util.stream.Collectors;
import javafx.application.Platform;

public class ContactsWindowController {

    private boolean flag;
    private String userLogin;  // Для хранения логина
    public String nameSurname;
    public String telephoneNumber;
    public String placeOfResidence;
    public String dateOfBirth;
    private Image contactImage;
    private HashMap<String, String> dataList = new HashMap<>();

    @FXML
    private Label Label1;
    @FXML
    private Label Label2;
    @FXML
    private Label Label3;
    @FXML
    private Label Label4;
    @FXML
    private Label Label5;
    @FXML
    private Button iconButton;
    @FXML
    private Button iconButton1;
    @FXML
    private Button iconButton2;
    @FXML
    private Button iconButton3;
    @FXML
    private Button iconButton4;
    @FXML
    private Button iconButton5;
    @FXML
    private Button iconButton6;
    @FXML
    private Button iconButton7;
    @FXML
    private Button SaveButton;
    @FXML
    private Button buttonBack;
    @FXML
    private Button SaveButton2;
    @FXML
    private Button buttonBack2;
    @FXML
    private Button quit;
    @FXML
    private Pane Pane1;
    @FXML
    private Pane Pane2;
    @FXML
    private Pane Pane3;
    @FXML
    private Pane Pane4;
    @FXML
    private Pane Pane5;
    @FXML
    private Pane Pane6;
    @FXML
    private ImageView iconImageView;
    @FXML
    private ImageView iconImageView1;
    @FXML
    private ImageView iconImageView2;
    @FXML
    private ImageView iconImageView3;
    @FXML
    private ImageView iconImageView4;
    @FXML
    private Rectangle Line1;
    @FXML
    private Circle Circle;
    @FXML
    private Circle Circle2;
    @FXML
    private Circle Circle3;
    @FXML
    private TextField TelephoneNumber;
    @FXML
    private TextField PlaceOfResidence;
    @FXML
    private TextField DateOfBirth;
    @FXML
    private TextField NameSurname;
    @FXML
    private TextField TelephoneNumber2;
    @FXML
    private TextField NameSurname2;
    @FXML
    private TextField PlaceOfResidence2;
    @FXML
    private TextField DateOfBirth2;
    @FXML
    private ScrollBar ScrollBar1;
    @FXML
    private ScrollBar ScrollBar2;
    @FXML
    private VBox buttonContainer;
    @FXML
    private VBox buttonContainer2;

    public void setUserLogin(String login) {
        this.userLogin = login;  // Устанавливаем логин пользователя
        loadUserAvatar();
        loadContacts();
    }

    @FXML
    private void initialize() {
        Image icon = new Image(getClass().getResourceAsStream("/images/Add.png"));
        iconImageView.setImage(icon);
        Image icon1 = new Image(getClass().getResourceAsStream("/images/Settings.png"));
        iconImageView1.setImage(icon1);
        Image icon2 = new Image(getClass().getResourceAsStream("/images/Favorites.png"));
        iconImageView2.setImage(icon2);
        Image icon3 = new Image(getClass().getResourceAsStream("/images/Contacts.png"));
        iconImageView3.setImage(icon3);
        Image icon4 = new Image(getClass().getResourceAsStream("/images/Profile.png"));
        iconImageView4.setImage(icon4);
        Image icon5 = new Image(getClass().getResourceAsStream("/images/ProfileAvatar.png"));
        Circle.setFill(new ImagePattern(icon5));
        Image icon6 = new Image(getClass().getResourceAsStream("/images/ProfileAvatar.png"));
        Circle2.setFill(new ImagePattern(icon6));
    }

    @FXML
    private void onIconButtonClick() {
        // Скрыть панель 1
        Pane1.setVisible(false);
        // Показать панель 2
        Pane2.setVisible(true);
        Pane3.setVisible(false);
        Label1.setText("    Новий контакт");
        Line1.setVisible(false);
    }

    @FXML
    private void onIconButton1Click() {
        flag = !flag;
        Pane3.setVisible(flag);
    }

    @FXML
    private void onIconButton2Click() {
        Pane2.setVisible(false);
        Pane3.setVisible(false);
        Pane1.setVisible(false);
        Pane5.setVisible(false);
        Pane6.setVisible(false);
        Pane4.setVisible(true);
        Label1.setText("    Обране");
        Line1.setVisible(true);
        TranslateTransition transition = new TranslateTransition(Duration.millis(200), Line1);
        transition.setToX(-147);
        transition.play();
    }

    @FXML
    private void onIconButton3Click() {
        Pane2.setVisible(false);
        Pane3.setVisible(false);
        Pane5.setVisible(false);
        Pane4.setVisible(false);
        Pane6.setVisible(false);
        Pane1.setVisible(true);
        Label1.setText("    Список контактів");
        Line1.setVisible(true);
        TranslateTransition transition = new TranslateTransition(Duration.millis(200), Line1);
        transition.setToX(0);
        transition.play();
    }

    @FXML
    private void onIconButton4Click() {
        Pane2.setVisible(false);
        Pane3.setVisible(false);
        Pane4.setVisible(false);
        Pane6.setVisible(false);
        Pane1.setVisible(false);
        Pane5.setVisible(true);
        Label1.setText("    Профіль");
        Line1.setVisible(true);
        TranslateTransition transition = new TranslateTransition(Duration.millis(200), Line1);
        transition.setToX(147);
        transition.play();
    }

    @FXML
    private void onButtonBackClick() {
        Pane2.setVisible(false);
        Pane1.setVisible(true);
        Line1.setVisible(true);
        Label1.setText("    Список контактів");
    }

    @FXML
    private void onButtonBackClick2() {
        Pane6.setVisible(false);
        Pane1.setVisible(true);
        Line1.setVisible(true);
        Label1.setText("    Список контактів");
    }

    @FXML
    private void onSaveButtonClick() {
        // Сначала собираем данные пользователя
        takeUserData();

        // Если все поля валидны, выполняем сохранение данных и другие действия
        if (validateFields()) {  // validateFields() возвращает true или false
            savingContactInformation();  // Сохранение контактных данных
            changingAvatar();  // Обновление аватара
            createContactWithImage();  // Создание кнопки с именем контакта
            clearInputFields();  // Очистка полей ввода
            onIconButton3Click();  // Переход в раздел "Контакты"
        }
    }

    private void createContactWithImage() {
        String contactName = takeUserData();
        // Создаем кнопку для контакта
        Button contactButton = new Button(contactName);
        contactButton.setPrefSize(420, 55);
        contactButton.setStyle("""
    -fx-background-color: linear-gradient(to right, #FF8C00, #E65100);  
    -fx-text-fill: white;
    -fx-font-size: 20px;
    -fx-padding: 10 20;
    -fx-background-radius: 20;
    -fx-cursor: hand;
    -fx-font-weight: bold;
    -fx-border-width: 0.5;  
    -fx-border-radius: 20;
    -fx-border-color: #E65100;  
    -fx-shadow-highlight-color: transparent;
    """);

        // Контейнер для кнопки и изображения (если оно есть)
        HBox hBox = new HBox(10.0);
        hBox.setAlignment(Pos.CENTER_LEFT);

        // Если изображение для контакта загружено, показываем его рядом с кнопкой
        if (contactImage != null) {
            Circle contactAvatar = new Circle(30);  // Размер круга для аватара
            contactAvatar.setFill(new ImagePattern(contactImage));  // Устанавливаем изображение
            hBox.getChildren().addAll(contactAvatar, contactButton);
        } else {
            hBox.getChildren().add(contactButton);
        }

        // Добавляем этот HBox в контейнер для контактов
        buttonContainer.getChildren().add(hBox);

        // Привязываем контекстное меню к HBox (не кнопке)
        addContextMenu(hBox, buttonContainer);

        contactButton.setOnAction(event -> {
            Pane1.setVisible(false);
            Pane2.setVisible(false);
            Pane4.setVisible(false);
            Pane6.setVisible(true);
            String Name = dataList.get(contactName);
            String telephone = dataList.get(contactName + "telephone");
            String place = dataList.get(contactName + "place");
            String date = dataList.get(contactName + "date");
            NameSurname2.setText(Name);
            TelephoneNumber2.setText(telephone);
            PlaceOfResidence2.setText(place);
            DateOfBirth2.setText(date);
        });
    }

    private void addContextMenu(HBox hBox, VBox container) {
        ContextMenu contextMenu = new ContextMenu();

        // Пункт для удаления контакта
        MenuItem deleteItem = new MenuItem("Видалити");
        deleteItem.setOnAction(event -> {
            String contactName = null;
            for (Node child : hBox.getChildren()) {
                if (child instanceof Button) {
                    contactName = ((Button) child).getText();
                    break;
                }
            }
            // Удаляем контакт и его аватарку
            removeContactWithAvatar(contactName, hBox, container);
        });
        contextMenu.getItems().add(deleteItem);  // Добавляем пункт "Удалить"

        // Пункт для перемещения контакта в избранное (если контакт в списке контактов)
        if (container == buttonContainer) {
            MenuItem moveToFavoritesItem = new MenuItem("Перемістити у обране");
            moveToFavoritesItem.setOnAction(event -> {
                String buttonName = null;
                for (Node child : hBox.getChildren()) {
                    if (child instanceof Button) {
                        buttonName = ((Button) child).getText();
                        break;
                    }
                }
                String a = userLogin + "/dataContacts.txt";
                updateContactStatus(a, buttonName, "Favorites");
                buttonContainer.getChildren().remove(hBox);
                buttonContainer2.getChildren().add(hBox);
                addContextMenu(hBox, buttonContainer2);
            });
            contextMenu.getItems().add(moveToFavoritesItem);
        }

        // Пункт "Отменить" для закрытия меню
        MenuItem cancelItem = new MenuItem("Скасувати");
        cancelItem.setOnAction(event -> contextMenu.hide());
        contextMenu.getItems().add(cancelItem);

        // Привязываем контекстное меню к HBox
        hBox.setOnContextMenuRequested(e -> contextMenu.show(hBox, e.getScreenX(), e.getScreenY()));
    }

    private void removeWithAnimation(HBox hBox, VBox container) {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(300), hBox);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);
        fadeTransition.setOnFinished(event -> container.getChildren().remove(hBox)); // Удаляем элемент после анимации
        fadeTransition.play();
    }

    private String takeUserData() {
        nameSurname = NameSurname.getText();
        telephoneNumber = TelephoneNumber.getText();
        placeOfResidence = PlaceOfResidence.getText();
        dateOfBirth = DateOfBirth.getText();
        String a = nameSurname;
        dataList.put(a, nameSurname);
        dataList.put(a + "telephone", telephoneNumber);
        dataList.put(a + "place", placeOfResidence);
        dataList.put(a + "date", dateOfBirth);
        return nameSurname;
    }

    // Метод для очистки всех полей
    private void clearInputFields() {
        NameSurname.clear();
        TelephoneNumber.clear();
        PlaceOfResidence.clear();
        DateOfBirth.clear();Image icon6 = new Image(getClass().getResourceAsStream("/images/ProfileAvatar.png"));
        Circle2.setFill(new ImagePattern(icon6));
    }

    // Метод для загрузки аватарки пользователя
    private void loadUserAvatar() {
        if (userLogin != null && !userLogin.isEmpty()) {
            File avatarFile = new File("UserPhotos/" + userLogin + "_avatar.png");

            // Если файл существует, то загружаем изображение
            if (avatarFile.exists()) {
                Image image = new Image(avatarFile.toURI().toString());
                Circle.setFill(new ImagePattern(image));  // Устанавливаем изображение в Circle
            }
        }
    }

    @FXML
    private void handleLoadImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png", "*.jpeg"));

        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            try {
                if (userLogin != null && !userLogin.isEmpty()) {
                    // Путь к файлу аватарки
                    File destination = new File("UserPhotos/" + userLogin + "_avatar.png");
                    // Если старое фото существует, удаляем его
                    if (destination.exists()) {
                        destination.delete();  // Удаляем старую аватарку
                    }
                    // Создаем родительскую папку, если она не существует
                    File parentDir = destination.getParentFile();
                    if (!parentDir.exists()) {
                        parentDir.mkdirs(); // Создаем папку, если она отсутствует
                    }
                    // Копируем новый файл в нужную директорию (вместо перемещения)
                    Path sourcePath = file.toPath();
                    Path destinationPath = destination.toPath();
                    Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING); // Копируем файл, если файл уже существует — заменяем его

                    Image image = new Image(destination.toURI().toString());
                    Circle.setFill(new ImagePattern(image));  // Установка изображения в Circle
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void handleLoadImage2() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png", "*.jpeg"));

        // Открытие диалогового окна для выбора файла
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            try {
                // Сохранение изображения в переменную
                contactImage = new Image(file.toURI().toString());

                // Устанавливаем изображение в Circle
                Circle contactAvatarCircle = new Circle(30);  // Создаем круг для аватара
                contactAvatarCircle.setFill(new ImagePattern(contactImage));  // Устанавливаем изображение в круг

                Circle2.setFill(new ImagePattern(contactImage));  // Устанавливаем аватар в Circle2 (или в нужный Circle)

                // Получаем путь к директории, куда нужно сохранить изображение
                String directory = userLogin + "/avatars";
                File destinationFolder = new File(directory);
                if (!destinationFolder.exists()) {
                    destinationFolder.mkdirs(); // Создаем папку, если она не существует
                }
                String newFileName = "1.png";;
                // Формируем путь для нового файла с новым именем
                File destinationFile = new File(destinationFolder, newFileName);
                // Копируем файл в указанную папку с новым именем
                Files.copy(file.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void savingContactInformation() {
        String data1 = nameSurname;
        String data2 = telephoneNumber;
        String data3 = placeOfResidence;
        String data4 = dateOfBirth;

        // Здесь мы добавляем статус контакта, например, "Contacts" или "Favorites"
        String contactData = "Имя контакта: " + data1 + ", Номер телефона: " + data2 + ", Место нахождения: " + data3 + ", День рождения: " + data4 + ", Status: Contacts";
        String directory = userLogin + "/dataContacts.txt";
        Path path = Paths.get(directory);

        try {
            Files.write(path, (contactData + "\n").getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void changingAvatar() {
        String directory1 = userLogin + "/avatars/1.png";
        String directory2 = userLogin + "/avatars/" + nameSurname + ".png";
        File oldAvatar = new File(directory1);
        File newAvatar = new File(directory2);
        try {
            Files.move(oldAvatar.toPath(), newAvatar.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch(Exception ignored) {
        }

    }

    private void loadContacts() {
        // Путь к файлу с контактами
        String contactFile = userLogin + "/dataContacts.txt";
        File file = new File(contactFile);

        if (file.exists()) {
            try {
                List<String> lines = Files.readAllLines(file.toPath()); // Читаем все строки файла

                // Проходим по каждой строке (контакту)
                for (String line : lines) {
                    // Разбиваем строку на составляющие
                    String[] parts = line.split(", ");
                    String name = parts[0].split(": ")[1];
                    String phone = parts[1].split(": ")[1];
                    String address = parts[2].split(": ")[1];
                    String birthDate = parts[3].split(": ")[1];
                    String status = parts[4].split(": ")[1];  // Статус (Contacts, Favorites и т.д.)

                    // Загружаем аватар для контакта (если есть)
                    File avatarFile = new File(userLogin + "/avatars/" + name + ".png");
                    Image contactAvatarImage = null;
                    if (avatarFile.exists()) {
                        contactAvatarImage = new Image(avatarFile.toURI().toString());
                    }

                    // Создаем кнопку для контакта
                    Button contactButton = new Button(name);
                    contactButton.setPrefSize(420, 55);
                    contactButton.setStyle("""
                        -fx-background-color: linear-gradient(to right, #FF8C00, #E65100);  
                        -fx-text-fill: white;
                        -fx-font-size: 20px;
                        -fx-padding: 10 20;
                        -fx-background-radius: 20;
                        -fx-cursor: hand;
                        -fx-font-weight: bold;
                        -fx-border-width: 0.5;  
                        -fx-border-radius: 20;
                        -fx-border-color: #E65100;  
                        -fx-shadow-highlight-color: transparent;
                    """);

                    // Контейнер для кнопки и изображения (если оно есть)
                    HBox hBox = new HBox(10.0);
                    hBox.setAlignment(Pos.CENTER_LEFT);

                    if (contactAvatarImage != null) {
                        Circle contactAvatar = new Circle(30);  // Размер круга для аватара
                        contactAvatar.setFill(new ImagePattern(contactAvatarImage));  // Устанавливаем изображение
                        hBox.getChildren().addAll(contactAvatar, contactButton);
                    } else {
                        hBox.getChildren().add(contactButton);
                    }

                    // Проверяем статус контакта и добавляем в нужный контейнер
                    if ("Favorites".equals(status)) {
                        // Добавляем в контейнер для избранных
                        buttonContainer2.getChildren().add(hBox);
                    } else {
                        // Добавляем в контейнер для обычных контактов
                        buttonContainer.getChildren().add(hBox);
                    }

                    // Привязываем контекстное меню
                    addContextMenu(hBox, buttonContainer);

                    // Обработка нажатия на кнопку
                    contactButton.setOnAction(event -> {
                        // Переход к подробному виду контакта
                        Pane1.setVisible(false);
                        Pane2.setVisible(false);
                        Pane4.setVisible(false);
                        Pane6.setVisible(true);
                        NameSurname2.setText(name);
                        TelephoneNumber2.setText(phone);
                        PlaceOfResidence2.setText(address);
                        DateOfBirth2.setText(birthDate);
                    });
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateContactStatus(String filePath, String contactName, String newStatus) {
        Path path = Paths.get(filePath);

        try {
            List<String> lines = Files.readAllLines(path);

            List<String> updatedLines = lines.stream()
                    .map(line -> {
                        if (line.contains(contactName)) {
                            return line.replaceFirst("Status: .+", "Status: " + newStatus);  // Обновляем статус
                        }
                        return line;
                    })
                    .collect(Collectors.toList());

            Files.write(path, updatedLines, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void removeContactWithAvatar(String contactName, HBox contactHBox, VBox container) {
        // Путь к файлу с данными контакта
        String contactFile = userLogin + "/dataContacts.txt";

        // Удаление контакта из файла
        removeContactData(contactFile, contactName);

        // Удаление аватарки (если файл существует)
        File avatarFile = new File(userLogin + "/avatars/" + contactName + ".png");
        if (avatarFile.exists()) {
            avatarFile.delete();  // Удаляем файл изображения
        }

        // Удаляем элемент из UI с анимацией
        removeWithAnimation(contactHBox, container);
    }

    private void removeContactData(String filePath, String contactName) {
        Path path = Paths.get(filePath);
        try {
            if (Files.notExists(path)) {
                return;
            }

            // Читаем все строки файла
            List<String> lines = Files.readAllLines(path);

            // Обрабатываем строки: ищем контакт по имени и удаляем его
            List<String> updatedLines = lines.stream()
                    .filter(line -> !line.contains(contactName))  // Убираем строку с данным контактом
                    .collect(Collectors.toList());

            // Записываем обновленные строки обратно в файл
            Files.write(path, updatedLines, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void quit(){
        Platform.exit();
    }

    private boolean validateFields() {
        boolean isValid = true;
        Label2.setText("");
        Label3.setText("");
        Label4.setText("");
        Label5.setText("");

        if (NameSurname.getText().trim().isEmpty()) {
            Label2.setText("Введіть ім'я та прізвище.");
            isValid = false;
        }

        if (!TelephoneNumber.getText().matches("\\+?\\d{10,15}")) {
            Label3.setText("Введіть номер телефону.");
            isValid = false;
        }

        if (PlaceOfResidence.getText().trim().isEmpty()) {
            Label4.setText("Введіть місце коректне проживання.");
            isValid = false;
        }

        if (!DateOfBirth.getText().matches("\\d{4}-\\d{2}-\\d{2}")) {
            Label5.setText("Введіть коректну дату народження (РРРР-ММ-ДД).");
            isValid = false;
        }

        return isValid;
    }

}