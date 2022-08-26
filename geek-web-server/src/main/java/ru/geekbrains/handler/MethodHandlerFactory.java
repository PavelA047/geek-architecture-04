package ru.geekbrains.handler;

import org.reflections.Reflections;
import ru.geekbrains.ResponseSerializer;
import ru.geekbrains.config.ServerConfig;
import ru.geekbrains.service.FileService;
import ru.geekbrains.service.SocketService;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

public final class MethodHandlerFactory {

    private static int sizeOfList;
    private static int count;

    public static MethodHandler create(SocketService socketService,
                                       ResponseSerializer responseSerializer,
                                       ServerConfig serverConfig,
                                       FileService fileService) {
        PutMethodHandler putMethodHandler = new PutMethodHandler("PUT", null, socketService, responseSerializer, serverConfig, null);
        PostMethodHandler postMethodHandler = new PostMethodHandler("POST", putMethodHandler, socketService, responseSerializer, serverConfig, null);
        return new GetMethodHandler("GET", postMethodHandler, socketService, responseSerializer, serverConfig, fileService);
    }

    public static MethodHandler createAnnotated(
            SocketService socketService,
            ResponseSerializer responseSerializer,
            ServerConfig serverConfig,
            FileService fileService
    ) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        Reflections reflections = new Reflections("ru.geekbrains.handler");
        List<Class<?>> listOfClasses = reflections
                .getTypesAnnotatedWith(Handler.class)
                .stream()
                .sorted(Comparator.comparingInt(x -> x.getAnnotation(Handler.class).order()))
                .collect(Collectors.toList());

        sizeOfList = listOfClasses.size();
        count = 0;

        return getNextMethodHandlerImpl(
                listOfClasses,
                socketService,
                responseSerializer,
                serverConfig,
                fileService
        );
    }

    private static MethodHandlerImpl getNextMethodHandlerImpl(
            List<Class<?>> listOfClasses,
            SocketService socketService,
            ResponseSerializer responseSerializer,
            ServerConfig serverConfig,
            FileService fileService
    ) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if (count < sizeOfList) {
            Class<?> handlerClass = listOfClasses.get(count++);

            return (MethodHandlerImpl) handlerClass.getConstructor(
                    String.class,
                    MethodHandlerImpl.class,
                    SocketService.class,
                    ResponseSerializer.class,
                    ServerConfig.class,
                    FileService.class
            ).newInstance(
                    handlerClass.getAnnotation(Handler.class).method(),
                    getNextMethodHandlerImpl(listOfClasses, socketService, responseSerializer, serverConfig, fileService),
                    socketService,
                    responseSerializer,
                    serverConfig,
                    fileService
            );
        } else return null;
    }
}