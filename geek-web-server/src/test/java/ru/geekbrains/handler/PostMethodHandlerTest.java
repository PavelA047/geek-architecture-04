package ru.geekbrains.handler;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import ru.geekbrains.ResponseSerializer;
import ru.geekbrains.config.ServerConfig;
import ru.geekbrains.domain.HttpRequest;
import ru.geekbrains.domain.HttpResponse;
import ru.geekbrains.service.FileService;
import ru.geekbrains.service.SocketService;

public class PostMethodHandlerTest {

    private final SocketService socketService = Mockito.mock(SocketService.class);
    private final ResponseSerializer responseSerializer = Mockito.mock(ResponseSerializer.class);
    private final ServerConfig serverConfig = Mockito.mock(ServerConfig.class);
    private final HttpRequest httpRequest = Mockito.mock(HttpRequest.class);

    @Test
    public void handleInternalTest() {
        PostMethodHandler postMethodHandler = new PostMethodHandler(
                "POST",
                Mockito.mock(PutMethodHandler.class),
                socketService,
                responseSerializer,
                serverConfig,
                null
        );

        HttpResponse httpResponse = postMethodHandler.handleInternal(httpRequest);

        HttpResponse expected = HttpResponse.createBuilder()
                .withStatusCode(200)
                .withStatusCodeName("OK")
                .withHeader("Content-Type", "text/html; charset=utf-8")
                .withBody("<h1>POST method is handled</h>")
                .build();

        Assert.assertEquals(expected.toString(), httpResponse.toString());
    }
}