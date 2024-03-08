package com.example.kursach;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.kursach.database.GetInfo;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class ControllerEmpMenu extends Connector {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button change_account_button;

    @FXML
    private Button file_button;

    @FXML
    private Button exit_button;

    @FXML
    private Button reviews_button;

    @FXML
    private Button service_add_button;

    @FXML
    private Button service_delete_button;

    @FXML
    private Button services_list_button;

    @FXML
    void initialize() {
        file_button.setOnAction(event -> {
            GetInfo.Text();
        });

        service_add_button.setOnAction(event -> {
            service_add_button.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("serviceAdd.fxml"));
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

        reviews_button.setOnAction(event -> {
            ObservableList<Reviews> reviews = GetInfo.reviewsList();
            TableView<Reviews> table = new TableView<>(reviews);
            table.setPrefWidth(600);
            table.setPrefHeight(400);

            TableColumn<Reviews, String> nameColumn = new TableColumn<>("Отзывы");
            nameColumn.setCellValueFactory(new PropertyValueFactory<Reviews, String>("reviews"));
            nameColumn.setPrefWidth(600);
            table.getColumns().add(nameColumn);


            FlowPane root = new FlowPane(10,10,table);
            Scene scene = new Scene(root,600,400);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Отзывы");
            stage.show();
        });

        service_delete_button.setOnAction(event -> {
            service_delete_button.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("serviceDelete.fxml"));
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

        services_list_button.setOnAction(event -> {
            ObservableList<Services> services = GetInfo.serviceList();
            TableView<Services> table = new TableView<>(services);
            table.setPrefWidth(600);
            table.setPrefHeight(400);

            TableColumn<Services, String> nameColumn = new TableColumn<>("Название услуги");
            nameColumn.setCellValueFactory(new PropertyValueFactory<Services, String>("name"));
            nameColumn.setPrefWidth(300);
            table.getColumns().add(nameColumn);

            TableColumn<Services, Integer> priceColumn = new TableColumn<>("Стоимость");
            priceColumn.setCellValueFactory(new PropertyValueFactory<Services, Integer>("price"));
            priceColumn.setPrefWidth(300);
            table.getColumns().add(priceColumn);

            FlowPane root = new FlowPane(10,10,table);
            Scene scene = new Scene(root,600,400);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Список услуг");
            stage.show();
        });

        change_account_button.setOnAction(event -> {
            change_account_button.getScene().getWindow().hide();
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
        });

        exit_button.setOnAction(event -> {
            exit_button.getScene().getWindow().hide();
            System.exit(0);
        });
    }

}
