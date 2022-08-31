package ru.geekbrains.handler;

import ru.geekbrains.domain.HttpRequest;
import ru.geekbrains.domain.HttpResponse;

public interface MethodHandler {
    HttpResponse handle(HttpRequest request);
}
