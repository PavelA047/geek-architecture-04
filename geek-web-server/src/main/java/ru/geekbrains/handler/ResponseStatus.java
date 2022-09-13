package ru.geekbrains.handler;

public enum ResponseStatus {

    OK(200, "OK"),
    NOT_FOUND(404, "NOT_FOUND"),
    SERVER_ERROR(500, "SERVER_ERROR");

    private final int code;
    private final String name;

    ResponseStatus(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
