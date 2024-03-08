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

public class ControllerAdminMenu extends Connector {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button change_account_button;

    @FXML
    private Button employee_add_button;

    @FXML
    private Button employee_list_button;

    @FXML
    private Button exit_button;

    @FXML
    private Button role_change_button;

    @FXML
    private Button user_delete_button;

    @FXML
    private Button user_list_button;

    @FXML
    void initialize() {
        employee_add_button.setOnAction(event -> {
            employee_add_button.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("empAdd.fxml"));
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

        role_change_button.setOnAction(event -> {
            role_change_button.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("roleChange.fxml"));
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

        user_delete_button.setOnAction(event -> {
            user_delete_button.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("userDelete.fxml"));
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




        employee_list_button.setOnAction(event -> {
            ObservableList<Employee> employee = GetInfo.employeeList();
            TableView<Employee> table = new TableView<>(employee);
            table.setPrefWidth(600);
            table.setPrefHeight(400);

            TableColumn<Employee, String> IDColumn = new TableColumn<>("ID сотрудника");
            IDColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("id"));
            IDColumn.setPrefWidth(300);
            table.getColumns().add(IDColumn);

            TableColumn<Employee, String> nameColumn = new TableColumn<>("Имя");
            nameColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("name"));
            nameColumn.setPrefWidth(300);
            table.getColumns().add(nameColumn);

            FlowPane root = new FlowPane(10,10,table);
            Scene scene = new Scene(root,600,400);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Список сотрудников");
            stage.show();
        });

        user_list_button.setOnAction(event -> {
            ObservableList<User> user = GetInfo.userList();
            TableView<User> table = new TableView<>(user);
            table.setPrefWidth(600);
            table.setPrefHeight(400);

            TableColumn<User, String> LoginColumn = new TableColumn<>("Логин пользователя");
            LoginColumn.setCellValueFactory(new PropertyValueFactory<User, String>("login"));
            LoginColumn.setPrefWidth(300);
            table.getColumns().add(LoginColumn);

            TableColumn<User, String> roleColumn = new TableColumn<>("Роль");
            roleColumn.setCellValueFactory(new PropertyValueFactory<User, String>("role"));
            roleColumn.setPrefWidth(300);
            table.getColumns().add(roleColumn);

            FlowPane root = new FlowPane(10,10,table);
            Scene scene = new Scene(root,600,400);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Список пользователей");
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
