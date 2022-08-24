package ru.geekbrains.handler;

import ru.geekbrains.ResponseSerializer;
import ru.geekbrains.config.ServerConfig;
import ru.geekbrains.service.FileService;
import ru.geekbrains.service.SocketService;

public final class MethodHandlerFactory {

    public static MethodHandler create(SocketService socketService,
                                           ResponseSerializer responseSerializer,
                                           ServerConfig serverConfig,
                                           FileService fileService) {
        PutMethodHandler putMethodHandler = new PutMethodHandler(null, socketService, responseSerializer, serverConfig);
        PostMethodHandler postMethodHandler = new PostMethodHandler(putMethodHandler, socketService, responseSerializer, serverConfig);
        return new GetMethodHandler(postMethodHandler, socketService, responseSerializer, serverConfig, fileService);
    }
}