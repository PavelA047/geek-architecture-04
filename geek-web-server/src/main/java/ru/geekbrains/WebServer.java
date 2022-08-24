package ru.geekbrains;

import ru.geekbrains.config.*;
import ru.geekbrains.handler.MethodHandlerFactory;
import ru.geekbrains.service.FileService;
import ru.geekbrains.service.SocketService;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {

    public static void main(String[] args) {
        ServerConfig config = ServerConfigFactory.create(args);
        try (ServerSocket serverSocket = new ServerSocket(config.getPort())) {
            System.out.println("Server started!");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected!");

                SocketService socketService = new SocketService(socket);

                new Thread(new RequestHandler(
                        socketService,
                        new RequestParser(),
                        MethodHandlerFactory.create(
                                socketService,
                                new ResponseSerializer(),
                                config,
                                new FileService(config.getWww()))
                )).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
