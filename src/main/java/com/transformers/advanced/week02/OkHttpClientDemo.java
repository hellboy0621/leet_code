package com.transformers.advanced.week02;

import okhttp3.*;

import java.io.IOException;
import java.util.Objects;

public class OkHttpClientDemo {

    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();

    public static void main(String[] args) throws IOException {
        String url = "http://localhost:8801";
        String response = new OkHttpClientDemo().run(url);
        System.out.println(response);
        String postResponse = new OkHttpClientDemo().post(url, "");
        System.out.println(postResponse);
    }

    String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return Objects.requireNonNull(response.body()).string();
        }
    }

    String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return Objects.requireNonNull(response.body()).string();
        }
    }
}
