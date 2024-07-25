package com.example.clientapp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketClient {
    private final String serverAddress;
    private final int serverPort;

    public SocketClient(String serverAddress, int serverPort) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
    }

    public void sendData(String data) {
        try (Socket socket = new Socket(serverAddress, serverPort);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            out.println(data);

            String response = in.readLine();
            System.out.println("Response from server: " + response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}