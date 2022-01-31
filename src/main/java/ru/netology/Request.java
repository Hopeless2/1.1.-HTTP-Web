package ru.netology;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.List;

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

    public String getQueryParam(String name) {
        URI uri = URI.create(path);
        List<NameValuePair> list = URLEncodedUtils.parse(uri, StandardCharsets.UTF_8);
        for (NameValuePair nameValuePair : list) {
            if (nameValuePair.getName().equals(name)) {
                return nameValuePair.getValue();
            }
        }
        return "";
    }

    public List<NameValuePair> getQueryParams() {
        return URLEncodedUtils.parse(path, StandardCharsets.UTF_8);
    }

    public String getVersion() {
        return version;
    }
}
