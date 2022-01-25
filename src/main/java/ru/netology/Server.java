package ru.netology;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server extends Thread {

    public static final int PORT = 8080;
<<<<<<< HEAD
    private final Map<String, Handler> handlersGet = new ConcurrentHashMap<>();
=======
    private final Map<String, Handler> handlersGet = new ConcurrentHashMap<>();//TODO выбрать map для многопотока(в качестве ключа http метод, в качестве второго ключа путь файла
>>>>>>> c35b668635ec5e6c88bb0974069c3bae2b90f87d
    private final Map<String, Handler> handlersPost = new ConcurrentHashMap<>();

    @Override
    public void run() {
        try (final var serverSocket = new ServerSocket(PORT)) {
            ExecutorService service = Executors.newFixedThreadPool(64);
            while (true) {
<<<<<<< HEAD
                service.submit(new ServerThread(serverSocket.accept(), this));
=======
                service.submit(new ServerThread(serverSocket1.accept(), this));
>>>>>>> c35b668635ec5e6c88bb0974069c3bae2b90f87d
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void addHandler(String method, String path, Handler handler) {
        if (method.equals("GET")) {
            handlersGet.put(path, handler);
        } else if (method.equals("POST")) {
            handlersPost.put(path, handler);
        }
    }

    public void handle(Request request, BufferedOutputStream responseStream) throws IOException {
<<<<<<< HEAD
        if (request.getRequestMethod().equals("GET")) {
            handlersGet.get(request.getHead()).handle(request, responseStream);
        } else if (request.getRequestMethod().equals("POST")) {
            handlersPost.get(request.getHead()).handle(request, responseStream);
        }

=======
        if (request.getHead().equals("/classic.html")) {
            if (request.getRequestMethod().equals("GET")) {
                handlersGet.get(request.getHead()).handle(request, responseStream);
            } else if (request.getRequestMethod().equals("POST")) {
                handlersPost.get(request.getHead()).handle(request, responseStream);
            }
        } else {
            if (request.getRequestMethod().equals("GET")) {
                handlersGet.get("standardCase").handle(request, responseStream);
            } else if (request.getRequestMethod().equals("POST")) {
                handlersPost.get("standardCase").handle(request, responseStream);
            }
        }
>>>>>>> c35b668635ec5e6c88bb0974069c3bae2b90f87d
    }

}
