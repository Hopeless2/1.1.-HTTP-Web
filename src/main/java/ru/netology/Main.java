package ru.netology;

<<<<<<< HEAD
import java.io.BufferedOutputStream;
import java.io.IOException;
=======
>>>>>>> c35b668635ec5e6c88bb0974069c3bae2b90f87d
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        final var server = new Server();

        Handler standardCase = (request, responseStream) -> {
            final var filePath = Path.of(".", "public", request.getHead());
            final var mimeType = Files.probeContentType(filePath);
            final var length = Files.size(filePath);
            responseStream.write((
                    "HTTP/1.1 200 OK\r\n" +
                            "Content-Type: " + mimeType + "\r\n" +
                            "Content-Length: " + length + "\r\n" +
                            "Connection: close\r\n" +
                            "\r\n"
            ).getBytes());
            Files.copy(filePath, responseStream);
            responseStream.flush();
        };

        server.addHandler("GET", "/classic.html", (request, responseStream) -> {
            final var filePath = Path.of(".", "public", request.getHead());
            final var mimeType = Files.probeContentType(filePath);
            final var template = Files.readString(filePath);
            final var content = template.replace(
                    "{time}",
                    LocalDateTime.now().toString()
            ).getBytes();
            responseStream.write((
                    "HTTP/1.1 200 OK\r\n" +
                            "Content-Type: " + mimeType + "\r\n" +
                            "Content-Length: " + content.length + "\r\n" +
                            "Connection: close\r\n" +
                            "\r\n"
            ).getBytes());
            responseStream.write(content);
            responseStream.flush();
        });

        server.addHandler("GET", "/events.html", standardCase);
        server.addHandler("GET", "/app.js", standardCase);
        server.addHandler("GET", "/events.js", standardCase);
        server.addHandler("GET", "/forms.html", standardCase);
        server.addHandler("GET", "/index.html", standardCase);
        server.addHandler("GET", "/links.html", standardCase);
        server.addHandler("GET", "/resources.html", standardCase);
        server.addHandler("GET", "/spring.png", standardCase);
        server.addHandler("GET", "/spring.svg", standardCase);
        server.addHandler("GET", "/styles.css", standardCase);

        server.start();
    }
}
