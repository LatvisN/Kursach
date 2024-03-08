package com.example.kursach;

import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerVisitAdd {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addVisitButton;

    @FXML
    private TextField date_field;

    @FXML
    private Button exitButton;

    @FXML
    private TextField name_field;

    @FXML
    private TextField service_field;

    @FXML
    private TextField time_field;

    @FXML
    void initialize() {
        addVisitButton.setOnAction(event -> {
            String name = name_field.getText().trim();
            String date = date_field.getText().trim();
            String time = time_field.getText().trim();
            String service = service_field.getText().trim();

            if (!name.equals("") && !date.equals("") && !time.equals("")&& !service.equals("")) {
                try {
                    AddVisit(name, date, time, service);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            else
                System.out.println("Empty field");
        });

        exitButton.setOnAction(event -> {
            exitButton.getScene().getWindow().hide();
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
        });
    }

    public void AddVisit(String name, String date, String time, String service) throws IOException {
        System.out.println("Проверка услуги");
        Connector.checkService(service);

        System.out.println("Добавление записи");
        Connector.addVisit(name, date, time, service);

        addVisitButton.getScene().getWindow().hide();
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
}
