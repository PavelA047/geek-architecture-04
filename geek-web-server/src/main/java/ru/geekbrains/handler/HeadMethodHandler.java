package ru.geekbrains.handler;

import ru.geekbrains.ResponseSerializer;
import ru.geekbrains.config.ServerConfig;
import ru.geekbrains.domain.HttpRequest;
import ru.geekbrains.domain.HttpResponse;
import ru.geekbrains.service.FileService;
import ru.geekbrains.service.SocketService;

@Handler(method = "HEAD")
class HeadMethodHandler extends MethodHandlerImpl {
    public HeadMethodHandler(String method,
                             MethodHandlerImpl next,
                             SocketService socketService,
                             ResponseSerializer responseSerializer,
                             ServerConfig serverConfig,
                             FileService fileService) {
        super(method, next, socketService, responseSerializer, serverConfig, null);
    }

    @Override
    protected HttpResponse handleInternal(HttpRequest request) {
        return HttpResponse.createBuilder()
                .withStatusCode(200)
                .withStatusCodeName("OK")
                .withHeader("Content-Type", "text/html; charset=utf-8")
                .withBody("<h1>HEAD method is handled</h>")
                .build();
    }
}
