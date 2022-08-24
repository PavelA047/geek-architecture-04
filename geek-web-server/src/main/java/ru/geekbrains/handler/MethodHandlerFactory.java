package ru.geekbrains.handler;

import org.reflections.Reflections;
import ru.geekbrains.ResponseSerializer;
import ru.geekbrains.config.ServerConfig;
import ru.geekbrains.service.FileService;
import ru.geekbrains.service.SocketService;

import java.util.*;
import java.util.stream.Collectors;

public final class MethodHandlerFactory {

    public static MethodHandler create(SocketService socketService,
                                       ResponseSerializer responseSerializer,
                                       ServerConfig serverConfig,
                                       FileService fileService) {
        PutMethodHandler putMethodHandler = new PutMethodHandler(null, socketService, responseSerializer, serverConfig);
        PostMethodHandler postMethodHandler = new PostMethodHandler(putMethodHandler, socketService, responseSerializer, serverConfig);
        return new GetMethodHandler(postMethodHandler, socketService, responseSerializer, serverConfig, fileService);
    }

    public static MethodHandler createAnnotated(SocketService socketService,
                                                ResponseSerializer responseSerializer,
                                                ServerConfig serverConfig,
                                                FileService fileService) {

        Reflections reflections = new Reflections("ru.geekbrains.handler");
        List<Class<?>> sortedHandlerClasses = reflections
                .getTypesAnnotatedWith(Handler.class)
                .stream()
                .sorted(Comparator.comparingInt(x -> x.getAnnotation(Handler.class).order()))
                .collect(Collectors.toList());

        return null;
    }
}