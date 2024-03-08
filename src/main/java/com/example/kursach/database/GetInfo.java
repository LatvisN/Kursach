package com.example.kursach.database;

import com.example.kursach.Employee;
import com.example.kursach.Reviews;
import com.example.kursach.Services;
import com.example.kursach.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class GetInfo extends JDBC {

    static int user_id;
    static int employee_id = 1;
    static int service_id;

    static int client_id;

    public static int loginCheck(String login_check, String password_check)
    {
        int role = -1;
        Statement stmt = null;
        try
        {
            JDBC.connect();
            stmt = JDBC.connection.createStatement();
            String exampleQuery1 = "SELECT * FROM пользователь";
            ResultSet rs1 = stmt.executeQuery(exampleQuery1);
            while (rs1.next())
            {
                String login = rs1.getString("login");
                String password = rs1.getString("password");
                if (Objects.equals(login, login_check) && Objects.equals(password, password_check)){
                    System.out.println("Пользователь найден");
                    role = rs1.getInt("role");
                    user_id = rs1.getInt("ID_пользователя");
                    System.out.println("ID пользователя - " + user_id);
                    return role;
                }
                else {
                    //System.out.println("Пользователь не найден");
                }
            }


        }
        catch(SQLException se) {
            se.printStackTrace();
        }
        finally {
            JDBC.close();
        }
        return role;
    }

    public static void serviceCheck(String service)
    {
        Statement stmt = null;
        try
        {
            JDBC.connect();
            stmt = JDBC.connection.createStatement();
            String exampleQuery1 = "SELECT * FROM услуги";
            ResultSet rs1 = stmt.executeQuery(exampleQuery1);
            System.out.println("Поиск услуги");
            while (rs1.next())
            {
                String service_name = rs1.getString("Название_услуги");
                if (Objects.equals(service_name, service)){
                    System.out.println("Услуга найдена");
                    service_id = rs1.getInt("ID_услуги");
                    break;
                }
                else {
                    service_id = 0;
                }
            }


        }
        catch(SQLException se) {
            se.printStackTrace();
        }
        finally {
            JDBC.close();
        }
    }

    public static void register(String regLogin, String regPass)
    {
        Statement stmt = null;
        try
        {
            JDBC.connect();
            stmt = JDBC.connection.createStatement();
            String BasicAdmin = "INSERT INTO пользователь (login, password, role)" + "VALUES ('" + regLogin
                    + "', '" + regPass + "', '" + "0" + "');";
            stmt.executeUpdate(BasicAdmin);
            String exampleQuery1 = "SELECT * FROM пользователь";
            ResultSet rs1 = stmt.executeQuery(exampleQuery1);
            while (rs1.next())
            {
                String reg_login = rs1.getString("login");
                if (Objects.equals(reg_login, regLogin)){
                    user_id = rs1.getInt("ID_пользователя");
                    System.out.println("ID пользователя - " + user_id);
                }
                else {
                    //System.out.println("Пользователь не найден");
                }
            }
        }
        catch(SQLException se) {
            se.printStackTrace();
        }
        finally {
            JDBC.close();
        }
    }

    public static void visitAdd(String date, String time, String service)
    {
        Statement stmt = null;
        try
        {
            JDBC.connect();
            stmt = JDBC.connection.createStatement();
            String visitAdd = "INSERT INTO посещения (ID_сотрудника, ID_клиента, ID_услуги, Дата, Время)" +
                    "VALUES ('" +
                    employee_id + "', '" +
                    client_id + "', '" +
                    service_id + "', '" +
                    date + "', '" +
                    time + "');";
            stmt.executeUpdate(visitAdd);
        }
        catch(SQLException se) { se.printStackTrace(); }
        finally { JDBC.close(); }
    }

    public static void empAdd(String name, String money)
    {
        Statement stmt = null;
        try
        {
            JDBC.connect();
            stmt = JDBC.connection.createStatement();
            String empAdd = "INSERT INTO посещения (Имя, Зарплата)" +
                    "VALUES ('" +
                    name + "', '" +
                    money + "');";
            stmt.executeUpdate(empAdd);
        }
        catch(SQLException se) { se.printStackTrace(); }
        finally { JDBC.close(); }
    }

    public static void addService(String name, String money)
    {
        Statement stmt = null;
        try
        {
            JDBC.connect();
            stmt = JDBC.connection.createStatement();
            String addService = "INSERT INTO услуги (Название_услуги, Стоимость)" +
                    "VALUES ('" +
                    name + "', '" +
                    money + "');";
            stmt.executeUpdate(addService);
        }
        catch(SQLException se) { se.printStackTrace(); }
        finally { JDBC.close(); }
    }

    public static void clientAdd(String name)
    {
        Statement stmt = null;
        try
        {
            JDBC.connect();
            stmt = JDBC.connection.createStatement();
            System.out.println("ID пользователя - " + user_id);
            String clientAdd = "INSERT INTO клиент (ID_пользователя, Имя)" + "VALUES ('" + user_id
                    + "', '" + name + "');";
            stmt.executeUpdate(clientAdd);
            String exampleQuery1 = "SELECT * FROM клиент";
            ResultSet rs1 = stmt.executeQuery(exampleQuery1);
            while (rs1.next())
            {
                String user = rs1.getString("Имя");
                if (Objects.equals(user, name)){
                    client_id = rs1.getInt("ID_клиента");
                    System.out.println("ID клиента - " + user_id);
                }
                else {
                    //System.out.println("Пользователь не найден");
                }
            }
        }
        catch(SQLException se) { se.printStackTrace(); }
        finally { JDBC.close(); }
    }

    public static void addReview(String review)
    {
        Statement stmt = null;
        try
        {
            JDBC.connect();
            stmt = JDBC.connection.createStatement();
            String reviewAdd = "INSERT INTO reviews (reviews)" + "VALUES ('" + review
                    + "');";
            stmt.executeUpdate(reviewAdd);
        }
        catch(SQLException se) { se.printStackTrace(); }
        finally { JDBC.close(); }
    }

    public static void Text()
    {
        Statement stmt = null;
        try
        {
            JDBC.connect();
            stmt = JDBC.connection.createStatement();
            String exampleQuery1 = "SELECT * FROM reviews";
            ResultSet rs1 = stmt.executeQuery(exampleQuery1);
            PrintWriter writer = new PrintWriter("Reviews.txt", "UTF-8");
            while (rs1.next())
            {
                String review = rs1.getString(1);
                writer.println(review);
            }
            writer.close();
        }
        catch(SQLException se) { se.printStackTrace(); } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } finally { JDBC.close(); }
    }

    public static ObservableList<Services> serviceList ()
    {
        Statement stmt = null;
        try
        {
            JDBC.connect();
            stmt = JDBC.connection.createStatement();
            String exampleQuery1 = "SELECT * FROM услуги";
            ResultSet rs1 = stmt.executeQuery(exampleQuery1);
            ObservableList<Services> services = FXCollections.observableArrayList();
            while (rs1.next())
            {
                String name = rs1.getString("Название_услуги");
                int price = rs1.getInt("Стоимость");
                Services servicesTemp = new Services(name, price);
                services.add(servicesTemp);
                System.out.println(name + "\t" + price);
            }
            return services;
        }
        catch(SQLException se) {
            se.printStackTrace();
        }
        finally {
            JDBC.close();
        }
        return null;
    }

    public static ObservableList<Employee> employeeList ()
    {
        Statement stmt = null;
        try
        {
            JDBC.connect();
            stmt = JDBC.connection.createStatement();
            String exampleQuery1 = "SELECT * FROM сотрудник";
            ResultSet rs1 = stmt.executeQuery(exampleQuery1);
            ObservableList<Employee> employee = FXCollections.observableArrayList();
            while (rs1.next())
            {
                String id = rs1.getString("ID_сотрудника");
                String name = rs1.getString("Имя");
                Employee employeeTemp = new Employee(id, name);
                employee.add(employeeTemp);
                System.out.println(id + "\t" + name);
            }
            return employee;
        }
        catch(SQLException se) {
            se.printStackTrace();
        }
        finally {
            JDBC.close();
        }
        return null;
    }

    public static ObservableList<User> userList ()
    {
        Statement stmt = null;
        try
        {
            JDBC.connect();
            stmt = JDBC.connection.createStatement();
            String exampleQuery1 = "SELECT * FROM пользователь";
            ResultSet rs1 = stmt.executeQuery(exampleQuery1);
            ObservableList<User> user = FXCollections.observableArrayList();
            while (rs1.next())
            {
                String login = rs1.getString("login");
                String role = rs1.getString("role");
                User userTemp = new User(login, role);
                user.add(userTemp);
                System.out.println(login + "\t" + role);
            }
            return user;
        }
        catch(SQLException se) {
            se.printStackTrace();
        }
        finally {
            JDBC.close();
        }
        return null;
    }

    public static ObservableList<Reviews> reviewsList ()
    {
        Statement stmt = null;
        try
        {
            JDBC.connect();
            stmt = JDBC.connection.createStatement();
            String exampleQuery1 = "SELECT * FROM reviews";
            ResultSet rs1 = stmt.executeQuery(exampleQuery1);
            ObservableList<Reviews> reviews = FXCollections.observableArrayList();
            while (rs1.next())
            {
                String text = rs1.getString("reviews");
                Reviews reviewsTemp = new Reviews(text);
                reviews.add(reviewsTemp);
                System.out.println(text);
            }
            return reviews;
        }
        catch(SQLException se) {
            se.printStackTrace();
        }
        finally {
            JDBC.close();
        }
        return null;
    }

    public static void visitDelete()
    {
        Statement stmt = null;
        try
        {
            JDBC.connect();
            stmt = JDBC.connection.createStatement();
            String deleteVisit = "DELETE FROM посещения WHERE ID_клиента = '" + client_id + "'";
            stmt.executeUpdate(deleteVisit);
        }
        catch(SQLException se) { se.printStackTrace(); }
        finally { JDBC.close(); }
    }

    public static void deleteService(String service)
    {
        Statement stmt = null;
        try
        {
            JDBC.connect();
            stmt = JDBC.connection.createStatement();
            String deleteService = "DELETE FROM услуги WHERE Название_услуги = '" + service + "'";
            stmt.executeUpdate(deleteService);
        }
        catch(SQLException se) { se.printStackTrace(); }
        finally { JDBC.close(); }
    }

    public static void deleteUser(String login)
    {
        Statement stmt = null;
        try
        {
            JDBC.connect();
            stmt = JDBC.connection.createStatement();
            String deleteUser = "DELETE FROM пользователь WHERE login = '" + login + "'";
            stmt.executeUpdate(deleteUser);
        }
        catch(SQLException se) { se.printStackTrace(); }
        finally { JDBC.close(); }
    }

    public static void clientDelete()
    {
        Statement stmt = null;
        try
        {
            JDBC.connect();
            stmt = JDBC.connection.createStatement();
            String deleteVisit = "DELETE FROM клиент WHERE ID_клиента = '" + client_id + "'";
            stmt.executeUpdate(deleteVisit);
        }
        catch(SQLException se) { se.printStackTrace(); }
        finally { JDBC.close(); }
    }

    public static void changeRole(String login, String role)
    {
        Statement stmt = null;
        try
        {
            JDBC.connect();
            stmt = JDBC.connection.createStatement();
            String exampleQuery1 = "UPDATE пользователь SET role = '"+ role +"' WHERE login = '" + login + "'";
            stmt.executeUpdate(exampleQuery1);
        }
        catch(SQLException se) { se.printStackTrace(); }
        finally { JDBC.close(); }
    }




    public static void selectFrom()
    {
        Statement stmt = null;
        try
        {
            JDBC.connect();
            stmt = JDBC.connection.createStatement();
            String exampleQuery1 = "SELECT * FROM Authors ORDER BY firstName";
            System.out.println("Authors:");
            ResultSet rs1 = stmt.executeQuery(exampleQuery1);
            while (rs1.next())
            {
                int id = rs1.getInt("authorID");
                String firstName = rs1.getString("firstName");
                String lastName = rs1.getString("lastName");
                System.out.println(id + "\t" + firstName + "\t" + lastName);
            }
        }
        catch(SQLException se) { se.printStackTrace(); }
        finally { JDBC.close(); }
    }


    public static void update()
    {
        Statement stmt = null;
        try
        {
            JDBC.connect();
            stmt = JDBC.connection.createStatement();
            String exampleQuery1 = "UPDATE authors SET firstName = 'Андрей' WHERE firstName = 'Анатолий'";
            stmt.executeUpdate(exampleQuery1);
        }
        catch(SQLException se) { se.printStackTrace(); }
        finally { JDBC.close(); }
    }

    public static void delete()
    {
        Statement stmt = null;
        try
        {
            JDBC.connect();
            stmt = JDBC.connection.createStatement();
            String exampleQuery1 = "DELETE FROM authors WHERE firstName = 'Dan'";
            stmt.executeUpdate(exampleQuery1);
        }
        catch(SQLException se) { se.printStackTrace(); }
        finally { JDBC.close(); }
    }
}

