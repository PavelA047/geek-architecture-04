package ru.geekbrains.handler;

import ru.geekbrains.ResponseSerializer;
import ru.geekbrains.config.ServerConfig;
import ru.geekbrains.domain.HttpRequest;
import ru.geekbrains.domain.HttpResponse;
import ru.geekbrains.service.FileService;
import ru.geekbrains.service.SocketService;

@Handler(method = "PUT")
class PutMethodHandler extends MethodHandlerImpl {
    public PutMethodHandler(String method,
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
                .withStatusCode(ResponseStatus.OK.getCode())
                .withStatusCodeName(ResponseStatus.OK.getName())
                .withHeader("Content-Type", "text/html; charset=utf-8")
                .withBody("<h1>PUT method is handled</h>")
                .build();
    }
}
