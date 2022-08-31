package ru.geekbrains.handler;

import ru.geekbrains.ResponseSerializer;
import ru.geekbrains.config.ServerConfig;
import ru.geekbrains.domain.HttpRequest;
import ru.geekbrains.domain.HttpResponse;
import ru.geekbrains.service.FileService;
import ru.geekbrains.service.SocketService;

abstract class MethodHandlerImpl implements MethodHandler {

    private final String method;

    private final MethodHandlerImpl next;

    protected final SocketService socketService;

    protected final ResponseSerializer responseSerializer;

    protected final ServerConfig serverConfig;

    protected final FileService fileService;

    public MethodHandlerImpl(String method,
                             MethodHandlerImpl next,
                             SocketService socketService,
                             ResponseSerializer responseSerializer,
                             ServerConfig serverConfig,
                             FileService fileService) {
        this.method = method;
        this.next = next;
        this.socketService = socketService;
        this.responseSerializer = responseSerializer;
        this.serverConfig = serverConfig;
        this.fileService = fileService;
    }

    public HttpResponse handle(HttpRequest request) {
        HttpResponse response;
        if (method.equals(request.getMethod())) {
            response = handleInternal(request);
        } else if (next != null) {
            next.handle(request);
            return null;
        } else {
            response = HttpResponse.createBuilder()
                    .withStatusCode(405)
                    .withStatusCodeName("METHOD_NOT_ALLOWED")
                    .withHeader("Content-Type", "text/html; charset=utf-8")
                    .withBody("<h1>Method is not supported</h1>")
                    .build();
        }
        String rawResponse = responseSerializer.serialize(response);
        socketService.writeResponse(rawResponse);
        return response;
    }

    protected abstract HttpResponse handleInternal(HttpRequest request);
}
