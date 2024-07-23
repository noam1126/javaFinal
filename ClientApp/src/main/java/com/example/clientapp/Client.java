package com.example.clientapp;


import java.io.*;
import java.net.Socket;

public class Client {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 8080;

    public String sendRequest(String requestJson) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            // Send request to server
            out.println(requestJson);

            // Receive response from server
            return in.readLine();

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}