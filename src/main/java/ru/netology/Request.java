package ru.netology;

public class Request {
    private final String requestMethod;
    private final String path;
    private final String version;

    public Request(String requestMethod, String path, String version) {
        this.requestMethod = requestMethod;
        this.path = path;
        this.version = version;
    }


    public String getRequestMethod() {
        return requestMethod;
    }

    public String getPath() {
        return path;
    }

    public String getVersion() {
        return version;
    }
}
