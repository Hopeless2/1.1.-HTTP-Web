package ru.netology;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

public class Request {
    private final String requestMethod;
    private final String url;
    private String path;
    private final String version;
    private List<NameValuePair> params = null;

    public Request(String requestMethod, String url, String version) {
        this.requestMethod = requestMethod;
        this.url = url;
        this.version = version;
        params = parseParams();
    }


    public String getRequestMethod() {
        return requestMethod;
    }

    public String getUrl() {
        return url;
    }

    public String getPath(){
        return path;
    }

    public List<NameValuePair> getQueryParam(String name) {
        return params.stream()
                .filter(nvp -> nvp.getName().equals(name))
                .collect(Collectors.toList());
    }

    public List<NameValuePair> getQueryParams(){
        return params;
    }

    private List<NameValuePair> parseParams() {
        URI uri = URI.create(url);
        path = uri.getPath();
        return URLEncodedUtils.parse(uri, StandardCharsets.UTF_8);
    }

    public String getVersion() {
        return version;
    }
}
