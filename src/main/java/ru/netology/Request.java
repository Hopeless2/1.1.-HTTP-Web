package ru.netology;

public class Request {
    private final String requestMethod;
    private final String head;
    private final String body;

    public Request(String requestMethod, String head, String body) {
        this.requestMethod = requestMethod;
        this.head = head;
        this.body = body;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public String getHead() {
        return head;
    }

    public String getBody() {
        return body;
    }
}
