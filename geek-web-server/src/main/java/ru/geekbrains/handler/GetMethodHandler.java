package ru.geekbrains.handler;

import ru.geekbrains.ResponseSerializer;
import ru.geekbrains.config.ServerConfig;
import ru.geekbrains.domain.HttpRequest;
import ru.geekbrains.domain.HttpResponse;
import ru.geekbrains.service.FileService;
import ru.geekbrains.service.SocketService;

@Handler(method = "GET")
class GetMethodHandler extends MethodHandlerImpl {

    public GetMethodHandler(String method,
                            MethodHandlerImpl next,
                            SocketService socketService,
                            ResponseSerializer responseSerializer,
                            ServerConfig serverConfig,
                            FileService fileService) {
        super(method, next, socketService, responseSerializer, serverConfig, fileService);
    }

    @Override
    protected HttpResponse handleInternal(HttpRequest request) {
        if (!fileService.exists(request.getUrl())) {
            return HttpResponse.createBuilder()
                    .withStatusCode(ResponseStatus.NOT_FOUND.getCode())
                    .withStatusCodeName(ResponseStatus.NOT_FOUND.getName())
                    .withHeader("Content-Type", "text/html; charset=utf-8")
                    .build();
        }

        return HttpResponse.createBuilder()
                .withStatusCode(ResponseStatus.OK.getCode())
                .withStatusCodeName(ResponseStatus.OK.getName())
                .withHeader("Content-Type", "text/html; charset=utf-8")
                .withBody(fileService.readFile(request.getUrl()))
                .build();
    }
}
