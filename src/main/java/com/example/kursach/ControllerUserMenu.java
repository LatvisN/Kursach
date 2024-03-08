package com.example.kursach;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
import com.example.kursach.database.GetInfo;

public class ControllerUserMenu extends Connector {

    @FXML
    private Button change_account_button;

    @FXML
    private Button contacts_button;

    @FXML
    private Button delete_visit_button;

    @FXML
    private Button exit_button;

    @FXML
    private Button promocode_button;

    @FXML
    private Button review_add_button1;

    @FXML
    private Button reviews_button;

    @FXML
    private Button services_list_button;

    @FXML
    private Button visit_add_button;

    @FXML
    void initialize() {
        contacts_button.setOnAction(event -> {
            contacts_button.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("contacts.fxml"));
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

        review_add_button1.setOnAction(event -> {
            review_add_button1.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("review.fxml"));
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

        promocode_button.setOnAction(event -> {
            promocode_button.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("promo.fxml"));
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

        visit_add_button.setOnAction(event -> {
            visit_add_button.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("visitAdd.fxml"));
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

        delete_visit_button.setOnAction(event -> {
            Connector.deleteVisit();
            visit_add_button.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("visitDelete.fxml"));
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

