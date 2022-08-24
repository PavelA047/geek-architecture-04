package ru.geekbrains.config;

class ConfigFromFixedValues implements ServerConfig {

    static {
        System.out.println("From fixed");
    }

    @Override
    public String getWww() {
        return "\"D:\\www\"";
    }

    @Override
    public int getPort() {
        return 8088;
    }
}
