package networking;

import com.google.gson.Gson;
import controllers.Controller;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Map;

public class HandleRequest implements Runnable {
    private Socket clientSocket;
    private Map<String, Controller> controllerMap;

    public HandleRequest(Socket clientSocket, Map<String, Controller> controllerMap) {
        this.clientSocket = clientSocket;
        this.controllerMap = controllerMap;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            Gson gson = new Gson();
            Request request = gson.fromJson(in.readLine(), Request.class);

            String action = request.getAction();
            Controller controller = controllerMap.get(action);

            Response response;
            if (controller != null) {
                response = controller.handleRequest(request);
            } else {
                response = new Response("error", "No controller found for: " + action);
            }

            out.println(gson.toJson(response));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
