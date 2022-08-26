package ru.geekbrains;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
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
            Observable.<Socket>create(emitter -> {
                        try {
                            while (true) {
                                Socket socket = serverSocket.accept();
                                System.out.println("New client connected!");
                                emitter.onNext(socket);
                            }
                        } catch (Exception ex) {
                            emitter.onError(ex);
                        }
                    })
                    .observeOn(Schedulers.io())
                    .subscribeOn(Schedulers.newThread())
                    .map(SocketService::new)
                    .subscribe(
                            socketService -> {
                                new RequestHandler(
                                        socketService,
                                        new RequestParser(),
                                        MethodHandlerFactory.create(
                                                socketService,
                                                new ResponseSerializer(),
                                                config,
                                                new FileService(config.getWww())
                                        )
                                ).run();
                            },
                            err -> System.out.println(err.getMessage())
                    );
            System.out.println("Press any key to close webServer");
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
