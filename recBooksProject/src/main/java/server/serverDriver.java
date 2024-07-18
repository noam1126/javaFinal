package server;

public class serverDriver {
    public static void main(String[] args) {
        Server server = new Server(34567);
        new Thread(server).start();
        System.out.println("Server started on port " + server);
    }
}
