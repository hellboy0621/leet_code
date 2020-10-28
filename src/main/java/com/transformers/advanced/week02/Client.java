package com.transformers.advanced.week02;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Client {

    public static void main(String[] args) throws IOException {
        String url = "http://localhost:8801";
        String getResponse = new Client().get(url);
        System.out.println(getResponse);
        String postResponse = new Client().post(url, new HashMap<>());
        System.out.println(postResponse);

    }

    public String get(String url) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        try {
            HttpGet httpget = new HttpGet(url);
            System.out.println("Executing get request " + httpget.getRequestLine());
            response = httpclient.execute(httpget);
        } finally {
            httpclient.close();
        }
        return handleResponse(response);
    }

    public String post(String url, Map<String, String> body) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response;
        try {
            HttpPost httpPost = new HttpPost(url);
            System.out.println("Executing post request " + httpPost.getRequestLine());
            handleBody(httpPost, body);
            response = httpclient.execute(httpPost);
        } finally {
            httpclient.close();
        }
        return handleResponse(response);
    }

    private void handleBody(HttpPost httpPost, Map<String, String> body) throws UnsupportedEncodingException {
        if (body != null && !body.isEmpty()) {
            Iterator<Map.Entry<String, String>> iterator = body.entrySet().iterator();
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();
                nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
            httpPost.setEntity(new UrlEncodedFormEntity(nvps));
        }
    }

    private String handleResponse(CloseableHttpResponse response) throws IOException {
        String resultStr = "";
        if (response != null) {
            try {
                System.out.println("----------------------------------------");
                System.out.println(response.getStatusLine());

                // Get hold of the response entity
                HttpEntity entity = response.getEntity();

                // If the response does not enclose an entity, there is no need
                // to bother about connection release
                if (entity != null) {
                    InputStream inStream = entity.getContent();
                    try {
                        // inStream.read();
                        // do something useful with the response
                        ByteArrayOutputStream result = new ByteArrayOutputStream();
                        byte[] buffer = new byte[1024];
                        int length;
                        while ((length = inStream.read(buffer)) != -1) {
                            result.write(buffer, 0, length);
                        }
                        resultStr = result.toString(StandardCharsets.UTF_8.name());
                    } catch (IOException ex) {
                        // In case of an IOException the connection will be released
                        // back to the connection manager automatically
                        throw ex;
                    } finally {
                        // Closing the input stream will trigger connection release
                        inStream.close();
                    }
                }
            } finally {
                response.close();
            }
        }
        return resultStr;
    }
}
