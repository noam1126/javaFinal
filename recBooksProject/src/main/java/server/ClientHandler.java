package server;

import com.google.gson.Gson;
import controllers.Controller;
import networking.Request;
import networking.Response;

import java.io.*;
import java.net.Socket;
import java.util.Map;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;
    private final Map<String, Controller> controllerMap;
    private final Gson gson;

    public ClientHandler(Socket clientSocket, Map<String, Controller> controllerMap, Gson gson) {
        this.clientSocket = clientSocket;
        this.controllerMap = controllerMap;
        this.gson = gson;
    }

    @Override
    public void run() {
        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))
        ) {
            // Read request JSON from client
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            String jsonRequest = sb.toString();

            Request request = gson.fromJson(jsonRequest, Request.class);

            String action = request.getAction();

            Controller controller = controllerMap.get(action);
            if (controller != null) {
                Response response = controller.handleRequest(request);

                String jsonResponse = gson.toJson(response);

                writer.write(jsonResponse);
                writer.newLine();
                writer.flush();
            } else {
                Response response = new Response("error", "No controller found for: " + action);
                String jsonResponse = gson.toJson(response);
                writer.write(jsonResponse);
                writer.newLine();
                writer.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
