package ru.geekbrains.config;

class ConfigFromFixedValues implements ServerConfig {

    static {
        System.out.println("From fixed");
    }

    private static final String PATH = "\"D:\\www\"";
    private static final int PORT = 8088;

    @Override
    public String getWww() {
        return PATH;
    }

    @Override
    public int getPort() {
        return PORT;
    }
}
