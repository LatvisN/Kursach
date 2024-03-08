package com.example.kursach;

import com.example.kursach.database.GetInfo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;

class Server extends GetInfo {
    static int client_counter = 0;
    public static void main(String[] args) {
        ServerSocket server = null;
        try {
            server = new ServerSocket(1234);
            server.setReuseAddress(true);
            System.out.println("Server started. \n");
            while (true) {
                Socket client = server.accept();
                System.out.println("New client connected: " + client.getInetAddress().getHostAddress());
                ClientHandler clientSock = new ClientHandler(client);
                new Thread(clientSock).start();
                client_counter++;
                System.out.println("Clients online:" + client_counter + " \n");
            }
        }
        catch (IOException e) {e.printStackTrace();}
        finally {
            if (server != null) {
                try {server.close();}
                catch (IOException e) {e.printStackTrace();}
            }
        }
    }

    private static class ClientHandler implements Runnable {
        private final Socket clientSocket;
        public ClientHandler(Socket socket)
        {
            this.clientSocket = socket;
        }

        public void run()
        {
            PrintWriter out = null;
            BufferedReader in = null;
            try {
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                int role = -1;
                String get;
                while (!"0".equalsIgnoreCase(get = in.readLine())) {
                    System.out.println("Sent from the client: " + get);
                    String[] message = get.split("\\.");
                    if(Objects.equals(message[0], "1")){
                        role = GetInfo.loginCheck(message[1], message[2]);
                        System.out.println(role);
                        out.println(role);
                    }
                    if(Objects.equals(message[0], "2")){
                        GetInfo.register(message[1], message[2]);
                        System.out.println(role);
                        out.println(role);
                    }
                    if(Objects.equals(message[0], "3")){
                        GetInfo.serviceCheck(message[1]);
                    }
                    if(Objects.equals(message[0], "4")){
                        GetInfo.clientAdd(message[1]);
                        GetInfo.visitAdd(message[2],message[3], message[4]);
                    }
                    if(Objects.equals(message[0], "5")){
                        GetInfo.visitDelete();
                        GetInfo.clientDelete();
                    }
                    if(Objects.equals(message[0], "6")){
                        GetInfo.empAdd(message[1], message[2]);
                    }
                    if(Objects.equals(message[0], "7")){
                        GetInfo.deleteUser(message[1]);
                    }
                    if(Objects.equals(message[0], "8")){
                        GetInfo.changeRole(message[1], message[2]);
                    }
                    if(Objects.equals(message[0], "9")){
                        GetInfo.addService(message[1], message[2]);
                    }
                    if(Objects.equals(message[0], "10")){
                        GetInfo.deleteService(message[1]);
                    }
                    if(Objects.equals(message[0], "11")){
                        GetInfo.addReview(message[1]);
                    }
                    /*switch (message[0]){
                        case "1":
                            role = GetInfo.loginCheck(message[1], message[2]);
                            System.out.println(role);
                            out.println(role);
                        case "2":
                            GetInfo.register(message[1], message[2]);
                            System.out.println(role);
                            out.println(role);
                        case "3":
                            GetInfo.serviceCheck(message[1]);
                        case "4":
                            GetInfo.clientAdd(message[1]);
                            GetInfo.visitAdd(message[2],message[3], message[4]);
                        case "5":
                            GetInfo.visitDelete();
                            GetInfo.clientDelete();
                        case "6":
                            GetInfo.empAdd(message[1], message[2]);
                        case "7":
                            GetInfo.deleteUser(message[1]);
                        case "8":
                            GetInfo.changeRole(message[1], message[2]);
                        case "9":
                            GetInfo.addService(message[1], message[2]);
                        case "10":
                            GetInfo.deleteService(message[1]);
                        case "11":
                            GetInfo.addReview(message[1]);
                    }*/
                }
                System.out.println("Client disconnected.");
                client_counter--;
                System.out.println("Clients online:" + client_counter + " \n");
            }

            catch (IOException e) {e.printStackTrace();}
            finally {
                try {
                    if (out != null) {out.close();}
                    if (in != null) {
                        in.close();
                        clientSocket.close();
                        System.out.println("Socket closed.");
                    }
                }
                catch (IOException e) {e.printStackTrace();}
            }
        }
    }
}

/*if(Objects.equals(message[0], "1")){
                        role = GetInfo.loginCheck(message[1], message[2]);
                        System.out.println(role);
                        out.println(role);
                    }
                    if(Objects.equals(message[0], "2")){
                        GetInfo.register(message[1], message[2]);
                        System.out.println(role);
                        out.println(role);
                    }
                    if(Objects.equals(message[0], "3")){
                        GetInfo.serviceCheck(message[1]);
                    }
                    if(Objects.equals(message[0], "4")){
                        GetInfo.clientAdd(message[1]);
                        GetInfo.visitAdd(message[2],message[3], message[4]);
                    }
                    if(Objects.equals(message[0], "5")){
                        GetInfo.visitDelete();
                        GetInfo.clientDelete();
                    }
                    if(Objects.equals(message[0], "6")){
                        GetInfo.empAdd(message[1], message[2]);
                    }
                    if(Objects.equals(message[0], "7")){
                        GetInfo.deleteUser(message[1]);
                    }
                    if(Objects.equals(message[0], "8")){
                        GetInfo.changeRole(message[1], message[2]);
                    }
                    if(Objects.equals(message[0], "9")){
                        GetInfo.addService(message[1], message[2]);
                    }
                    if(Objects.equals(message[0], "10")){
                        GetInfo.deleteService(message[1]);
                    }
                    if(Objects.equals(message[0], "11")){
                        GetInfo.addReview(message[1]);
                    }*/