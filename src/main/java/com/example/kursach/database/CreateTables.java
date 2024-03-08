package com.example.kursach.database;

import java.sql.SQLException;
import java.sql.Statement;

public class CreateTables
{

    public static void main(String[] args) throws SQLException {
        Create();
    }

    public static void Create() throws SQLException
    {
        Statement stmt = null;
        try
        {
            JDBC.connect();
            stmt = JDBC.connection.createStatement();
            String drop2 = "DROP TABLE IF EXISTS посещения";
            stmt.executeUpdate(drop2);
            String drop3 = "DROP TABLE IF EXISTS клиент";
            stmt.executeUpdate(drop3);
            String drop4 = "DROP TABLE IF EXISTS сотрудник";
            stmt.executeUpdate(drop4);
            String drop5 = "DROP TABLE IF EXISTS услуги";
            stmt.executeUpdate(drop5);
            String drop1 = "DROP TABLE IF EXISTS пользователь";
            stmt.executeUpdate(drop1);
            String drop6 = "DROP TABLE IF EXISTS reviews";
            stmt.executeUpdate(drop6);
            String User = "CREATE TABLE пользователь " + "(ID_пользователя INT(10) NOT NULL AUTO_INCREMENT, " + " login CHAR(50), " + " password CHAR(50), " + " role INT(10), " + " PRIMARY KEY (ID_пользователя))";
            stmt.executeUpdate(User);
            System.out.println("Таблица Пользователь создана.");
            String Client = "CREATE TABLE клиент " +
                    "(ID_клиента INT(10) NOT NULL AUTO_INCREMENT, " +
                    " ID_пользователя INT(10), " +
                    " Имя CHAR(50), " +
                    " PRIMARY KEY (ID_клиента), " +
                    " CONSTRAINT FK_клиент_пользователь FOREIGN KEY (ID_пользователя) REFERENCES пользователь (ID_пользователя) ON UPDATE NO ACTION ON DELETE NO ACTION)";
            stmt.executeUpdate(Client);
            System.out.println("Таблица Клиент создана.");
            String Services = "CREATE TABLE услуги " +
                    "(ID_услуги INT(10) NOT NULL AUTO_INCREMENT, " +
                    " Название_услуги CHAR(50), " +
                    " Стоимость INT(10), " +
                    " PRIMARY KEY (ID_услуги))";
            stmt.executeUpdate(Services);
            System.out.println("Таблица Услуги создана.");
            String Employee = "CREATE TABLE сотрудник " +
                    "(ID_сотрудника INT(10) NOT NULL AUTO_INCREMENT, " +
                    " Имя CHAR(50), " +
                    " Зарплата CHAR(50), " +
                    " PRIMARY KEY (ID_сотрудника))";
            stmt.executeUpdate(Employee);
            System.out.println("Таблица Сотрудник создана.");
            String Visits = "CREATE TABLE посещения " +
                    "(ID_посетителя INT(10) NOT NULL AUTO_INCREMENT, " +
                    " ID_сотрудника INT(10), " +
                    " ID_клиента INT(10), " +
                    " ID_услуги INT(10), " +
                    " Дата CHAR(10), " +
                    " Время CHAR(10), " +
                    " PRIMARY KEY (ID_посетителя), " +
                    " CONSTRAINT FK_посещения_клиент FOREIGN KEY (ID_клиента) REFERENCES клиент (ID_клиента) ON UPDATE NO ACTION ON DELETE NO ACTION, " +
                    " CONSTRAINT FK_посещения_сотрудник FOREIGN KEY (ID_сотрудника) REFERENCES сотрудник (ID_сотрудника) ON UPDATE NO ACTION ON DELETE NO ACTION, " +
                    " CONSTRAINT FK_посещения_услуги FOREIGN KEY (ID_услуги) REFERENCES услуги (ID_услуги) ON UPDATE NO ACTION ON DELETE NO ACTION)";
            stmt.executeUpdate(Visits);
            System.out.println("Таблица Посещения создана.");
            String BasicAdmin = "INSERT INTO пользователь (login, password, role)" + "VALUES ('" + "admin"
                    + "', '" + "admin" + "', '" + "2" + "');";
            stmt.executeUpdate(BasicAdmin);
            String BasicEmpUser = "INSERT INTO пользователь (login, password, role)" + "VALUES ('" + "emp"
                    + "', '" + "emp" + "', '" + "1" + "');";
            stmt.executeUpdate(BasicEmpUser);
            String BasicUser = "INSERT INTO пользователь (login, password, role)" + "VALUES ('" + "user"
                    + "', '" + "user" + "', '" + "0" + "');";
            stmt.executeUpdate(BasicUser);
            String BasicService1 = "INSERT INTO услуги (Название_услуги, Стоимость)" + "VALUES ('" + "Стрижка детская"
                    + "', '" + "5" + "');";
            stmt.executeUpdate(BasicService1);
            String BasicService2 = "INSERT INTO услуги (Название_услуги, Стоимость)" + "VALUES ('" + "Стрижка мужская"
                    + "', '" + "10" + "');";
            stmt.executeUpdate(BasicService2);
            String BasicService3 = "INSERT INTO услуги (Название_услуги, Стоимость)" + "VALUES ('" + "Стрижка женская"
                    + "', '" + "12" + "');";
            stmt.executeUpdate(BasicService3);
            String BasicEmployee = "INSERT INTO сотрудник (Имя, Зарплата)" + "VALUES ('" + "Татьяна"
                    + "', '" + "800" + "');";
            stmt.executeUpdate(BasicEmployee);
            String Reviews = "CREATE TABLE reviews " +
                    "(reviews CHAR(200), " +
                    " PRIMARY KEY (reviews))";
            stmt.executeUpdate(Reviews);
            System.out.println("Таблица reviews создана.");
            //InsertTestData.updateTables();
        }
        catch(SQLException se)
        {
              se.printStackTrace();
        }
        finally
        {
              if(stmt != null)
              {
                  JDBC.close();
              }
        }
    }
}
