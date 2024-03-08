package com.example.kursach;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Connector extends Server.ClientHandler {
    static int client_role;
    static Socket socket;

    static {
        try {
            socket = new Socket("localhost", 1234);
        } catch (IOException e) {throw new RuntimeException(e);}
    }

    static PrintWriter out;

    static {
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {throw new RuntimeException(e);}
    }

    static BufferedReader in;

    static {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {throw new RuntimeException(e);}
    }

    public Connector() throws IOException {}

    public static int login(String login, String password) throws IOException {
        String send = 1 + "." + login + "." + password;
        out.println(send);
        System.out.println(login + " " + password);
        System.out.println("Данные для входа отправлены");
        client_role = Integer.parseInt(in.readLine());
        System.out.println(client_role);
        return client_role;
    }

    public static void register(String login, String password) throws IOException {
        String send = 2 + "." + login + "." + password;
        out.println(send);
        System.out.println(login + " " + password);
        System.out.println("Данные для регистрации отправлены");
        client_role = Integer.parseInt(in.readLine());
    }

    public static void checkService(String service) throws IOException {
        String send = 3 + "." + service;
        out.println(send);
        System.out.println(service);
        System.out.println("Данные для проверки отправлены");
    }

    public static void addVisit(String name, String date, String time, String service) throws IOException {
        String send = 4 + "." + name + "." + date + "." + time + "." + service;
        out.println(send);
        System.out.println(name + " " + date  + " " + time + " " + service);
        System.out.println("Данные для записи отправлены");
    }

    public static void deleteVisit() {
        String send = "5";
        out.println(send);
        System.out.println("Запрос на отмену записи отправлен");
    }

    public static void addEmp(String name, String money) throws IOException {
        String send = 6 + "." + name + "." + money;
        out.println(send);
        System.out.println(name + " " + money);
    }

    public static void deleteUser(String login) throws IOException {
        String send = 7 + "." + login;
        out.println(send);
        System.out.println(login);
    }

    public static void changeRole(String login, String newrole) throws IOException {
        String send = 8 + "." + login + "." + newrole;
        out.println(send);
        System.out.println(login + " " + newrole);
    }

    public static void addService(String name, String price) throws IOException {
        String send = 9 + "." + name + "." + price;
        out.println(send);
        System.out.println(name + " " + price);
    }

    public static void deleteService(String service) throws IOException {
        String send = 10 + "." + service;
        out.println(send);
        System.out.println(service);
    }

    public static void addReview(String review) throws IOException {
        String send = 11 + "." + review;
        out.println(send);
        System.out.println(review);
    }
}
