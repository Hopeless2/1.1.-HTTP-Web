package ru.netology;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.URI;
import java.util.List;
import java.util.Map;

public class ServerThread implements Runnable {
    private final Socket socket;
    private final Map<String, Handler> handlersGet;
    private final Map<String, Handler> handlersPost;
    public static final String GET = "GET";
    public static final String POST = "POST";
    final List<String> allowedMethods = List.of(GET, POST);
    private BufferedReader in = null;
    private BufferedOutputStream out = null;
    private final List<String> validPaths = List.of("/index.html", "/spring.svg", "/spring.png", "/resources.html", "/styles.css", "/app.js", "/links.html", "/forms.html", "/classic.html", "/events.html", "/events.js");


    public ServerThread(Socket socket, Map<String, Handler> handlersGet, Map<String, Handler> handlersPOST) {
        this.socket = socket;
        this.handlersGet = handlersGet;
        this.handlersPost = handlersPOST;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                final var requestLine = in.readLine();
                final var parts = requestLine.split(" ");

                if (parts.length != 3) {
                    socket.close();
                    break;
                }

                URI uri = URI.create(parts[1]);
                final var path = uri.getPath();
                if (!validPaths.contains(path)) {
                    out.write((
                            "HTTP/1.1 404 Not Found\r\n" +
                                    "Content-Length: 0\r\n" +
                                    "Connection: close\r\n" +
                                    "\r\n"
                    ).getBytes());
                    out.flush();
                    continue;
                }
                Request request = new Request(parts[0], path, parts[2]);
                handle(request, out);

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void handle(Request request, BufferedOutputStream responseStream) throws IOException {
        if (request.getRequestMethod().equals(allowedMethods.get(0))) {
            handlersGet.get(request.getPath()).handle(request, responseStream);
        } else if (request.getRequestMethod().equals(allowedMethods.get(1))) {
            handlersPost.get(request.getPath()).handle(request, responseStream);
        }
    }
}
