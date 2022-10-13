package com.example.demo.utils;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class HttpEntityBuilder {

    private final HttpHeaders headers;

    public HttpEntityBuilder() {
        headers = new HttpHeaders();
    }

    public HttpEntityBuilder comHeadersDefault(){
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        return this;
    }

    public HttpEntity build(){
        return new HttpEntity<String>(headers);
    }
}
