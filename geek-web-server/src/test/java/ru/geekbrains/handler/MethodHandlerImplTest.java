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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class MethodHandlerImplTest {

    private final PostMethodHandler postMethodHandlerMock = Mockito.mock(PostMethodHandler.class);
    private final PutMethodHandler putMethodHandlerMock = Mockito.mock(PutMethodHandler.class);
    private final SocketService socketService = Mockito.mock(SocketService.class);
    private final ResponseSerializer responseSerializer = Mockito.mock(ResponseSerializer.class);
    private final ServerConfig serverConfig = Mockito.mock(ServerConfig.class);
    private final FileService fileService = Mockito.mock(FileService.class);

    @Test
    public void getHandleTest() {
        when(fileService.exists(any())).thenReturn(true);

        GetMethodHandler getMethodHandler = new GetMethodHandler(
                "GET",
                postMethodHandlerMock,
                socketService,
                responseSerializer,
                serverConfig,
                fileService
        );

        HttpRequest httpRequest = HttpRequest.createBuilder()
                .withMethod("GET")
                .withUrl("/index.html")
                .withHeader("1", "1")
                .build();

        HttpResponse expected = HttpResponse.createBuilder()
                .withStatusCode(200)
                .withStatusCodeName("OK")
                .withHeader("Content-Type", "text/html; charset=utf-8")
                .withBody(fileService.readFile(httpRequest.getUrl()))
                .build();

        Assert.assertEquals(expected.toString(), getMethodHandler.handle(httpRequest).toString());
    }

    @Test
    public void postHandleTest() {

        PostMethodHandler postMethodHandler = new PostMethodHandler(
                "POST",
                putMethodHandlerMock,
                socketService,
                responseSerializer,
                serverConfig,
                fileService
        );

        HttpRequest httpRequest = HttpRequest.createBuilder()
                .withMethod("POST")
                .withUrl("/index.html")
                .withHeader("1", "1")
                .build();

        HttpResponse expected = HttpResponse.createBuilder()
                .withStatusCode(200)
                .withStatusCodeName("OK")
                .withHeader("Content-Type", "text/html; charset=utf-8")
                .withBody("<h1>POST method is handled</h>")
                .build();

        Assert.assertEquals(expected.toString(), postMethodHandler.handle(httpRequest).toString());
    }

    @Test
    public void putHandleTest() {

        PutMethodHandler putMethodHandler = new PutMethodHandler(
                "PUT",
                null,
                socketService,
                responseSerializer,
                serverConfig,
                fileService
        );

        HttpRequest httpRequest = HttpRequest.createBuilder()
                .withMethod("PUT")
                .withUrl("/index.html")
                .withHeader("1", "1")
                .build();

        HttpResponse expected = HttpResponse.createBuilder()
                .withStatusCode(200)
                .withStatusCodeName("OK")
                .withHeader("Content-Type", "text/html; charset=utf-8")
                .withBody("<h1>PUT method is handled</h>")
                .build();

        Assert.assertEquals(expected.toString(), putMethodHandler.handle(httpRequest).toString());
    }

    @Test
    public void deleteHandleTest() {

        GetMethodHandler getMethodHandler = new GetMethodHandler(
                "GET",
                null,
                socketService,
                responseSerializer,
                serverConfig,
                fileService
        );

        HttpRequest httpRequest = HttpRequest.createBuilder()
                .withMethod("DELETE")
                .withUrl("/index.html")
                .withHeader("1", "1")
                .build();

        HttpResponse expected = HttpResponse.createBuilder()
                .withStatusCode(405)
                .withStatusCodeName("METHOD_NOT_ALLOWED")
                .withHeader("Content-Type", "text/html; charset=utf-8")
                .withBody("<h1>Method is not supported</h1>")
                .build();

        Assert.assertEquals(expected.toString(), getMethodHandler.handle(httpRequest).toString());
    }
}