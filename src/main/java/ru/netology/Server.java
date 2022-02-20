package ru.netology;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server extends Thread {
    public static final String GET = "GET";
    public static final String POST = "POST";
    public static final int PORT = 8080;
    final List<String> allowedMethods = List.of(GET, POST);
    private final Map<String, Handler> handlersGet = new ConcurrentHashMap<>();
    private final Map<String, Handler> handlersPost = new ConcurrentHashMap<>();

    @Override
    public void run() {
        try (final var serverSocket = new ServerSocket(PORT)) {
            ExecutorService service = Executors.newFixedThreadPool(64);
            while (true) {
                service.submit(new ServerThread(serverSocket.accept(), handlersGet, handlersPost));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void addHandler(String method, String path, Handler handler) {
        if (method.equals(allowedMethods.get(0))) {
            handlersGet.put(path, handler);
        } else if (method.equals(allowedMethods.get(1))) {
            handlersPost.put(path, handler);
        }
    }

}
