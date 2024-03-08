package com.example.kursach;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.kursach.Connector;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerLogin extends ControllerRegister {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;


    @FXML
    private Button exitButton;

    @FXML
    private Button loginButton;

    @FXML
    private TextField login_field;

    @FXML
    private PasswordField password_field;

    @FXML
    private Button registerButton;

    @FXML
    void initialize() {
        loginButton.setOnAction(event -> {
            String loginText = login_field.getText().trim();
            String passwordText = password_field.getText().trim();

            if (!loginText.equals("") && !passwordText.equals("")) {
                try {
                    loginUser(loginText, passwordText);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            else
                System.out.println("Empty login and password");
        });

        registerButton.setOnAction(event -> {
            registerButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("register.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        });

        exitButton.setOnAction(event -> {
            exitButton.getScene().getWindow().hide();
            System.exit(0);
        });
    }

    public void loginUser(String login_Text, String password_Text) throws IOException {
        System.out.println("Проверка логина и пароля");
        int role = Connector.login(login_Text, password_Text);

        if (role == -1){
            System.out.println("неправильный ввод");
        }

        if (role == 0){
            System.out.println("Клиент парикмахерской");
            registerButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("userView.fxml"));
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

        if (role == 1){
            System.out.println("Работник парикмахерской");
            registerButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("empView.fxml"));
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

        if (role == 2){
            System.out.println("админ");
            registerButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("adminView.fxml"));
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
}

