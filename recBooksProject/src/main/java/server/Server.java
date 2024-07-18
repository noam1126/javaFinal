package server;

import com.google.gson.Gson;
import controllers.BookController;
import controllers.Controller;
import services.BookService;
import dao.IDao;
import dao.MyDMFileImpl;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Server implements Runnable {
    private final int port;
    private final Gson gson;
    private final Map<String, Controller> controllerMap;

    public Server(int port, BookService bookService) {
        this.port = port;
        this.gson = new Gson();
        this.controllerMap = new HashMap<>();
        initializeControllers(bookService);
    }

    public Server(int port) {
        this.port = port;
        this.gson = new Gson();
        this.controllerMap = new HashMap<>();
    }

    private void initializeControllers(BookService bookService) {
        controllerMap.put("save", new BookController(bookService));
        controllerMap.put("get", new BookController(bookService));
        controllerMap.put("update", new BookController(bookService));
        controllerMap.put("delete", new BookController(bookService));
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);
            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(new ClientHandler(socket, controllerMap, gson)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        IDao dao = new MyDMFileImpl(Server.class.getResource("/datafile.txt").getPath());
        BookService bookService = new BookService(dao);
        Server server = new Server(8080, bookService);
        server.start();
    }

    @Override
    public void run() {

    }
}
