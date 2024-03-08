package com.example.kursach;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerRegister {
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private Button regButton;
    @FXML
    private Button exitButton;
    @FXML
    private TextField reg_login_field;
    @FXML
    private PasswordField reg_password_field;
    @FXML
    void initialize() {
        regButton.setOnAction(event -> {
            String loginText = reg_login_field.getText().trim();
            String passwordText = reg_password_field.getText().trim();

            if (!loginText.equals("") && !passwordText.equals("")) {
                try {
                    registerUser(loginText, passwordText);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            else
                System.out.println("Empty login and password");
        });

        exitButton.setOnAction(event -> {
            exitButton.getScene().getWindow().hide();
            System.exit(0);
        });
    }

    public void registerUser(String login_Text, String password_Text) throws IOException {
        System.out.println("Проверка логина и пароля при регистрации");
        Connector.register(login_Text, password_Text);

        regButton.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("login.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
