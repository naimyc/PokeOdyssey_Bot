package com.http;

import java.io.IOException;

import org.json.JSONObject;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HTTP {
    private final OkHttpClient client;
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    // Constructor to initialize the OkHttpClient
    public HTTP() {
        this.client = new OkHttpClient.Builder()
                .connectTimeout(30, java.util.concurrent.TimeUnit.SECONDS)
                .readTimeout(120, java.util.concurrent.TimeUnit.SECONDS)
                .writeTimeout(120, java.util.concurrent.TimeUnit.SECONDS)
                .build();
    }

    /**
     * Makes an HTTP GET request to the specified URL and returns the Response object.
     *
     * @param url The URL to send the request to.
     * @return The Response object containing the full HTTP response.
     * @throws IOException if an I/O error occurs during the request.
     */
    public Response GET(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();
        return client.newCall(request).execute();
    }

    /**
     * Sends an HTTP POST request with JSON data and returns the response body as a string.
     *
     * @param url  The URL to send the request to.
     * @param json The JSON object to send.
     * @return The response body as a string.
     * @throws IOException if an I/O error occurs during the request.
     */
    public Response POST(String url, JSONObject json) throws IOException {
        RequestBody body = RequestBody.create(json.toString(), JSON);
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Content-Type", "application/json; utf-8")
                .addHeader("Accept", "application/json")
                .post(body)
                .build();
        
        return client.newCall(request).execute();
        		
    }

//    public static void main(String[] args) {
//        try {
//            HTTP rest = new HTTP();
//            
//            String url = "http://localhost:11434/api/generate";
//            String modelName = "deepseek-r1:1.5b";
//            String prompt = "Are you chatgpt?";
//
//            JSONObject json = new JSONObject();
//            json.put("model", modelName);
//            json.put("prompt", prompt);
//            json.put("stream", false);
//
//            Response response = rest.POST(url, json);
//            System.out.println("Response: " + response.body().string());
//        } catch (IOException | JSONException e) {
//            e.printStackTrace();
//        }
//    }
}
