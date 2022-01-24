package ru.netology;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server extends Thread {

    public static final int PORT = 8080;

    @Override
    public void run() {
        try (final var serverSocket1 = new ServerSocket(PORT)) {
            ExecutorService service = Executors.newFixedThreadPool(48);
            while (true) {
                service.submit(new ServerThread(serverSocket1.accept()));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
