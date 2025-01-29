package com.http;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HTTP {
	private final OkHttpClient client;

	// Constructor to initialize the OkHttpClient
	public HTTP() {
		this.client = new OkHttpClient();
	}

	/**
	 * Makes an HTTP GET request to the specified URL and returns the Response
	 * object.
	 *
	 * @param url The URL to send the request to.
	 * @return The Response object containing the full HTTP response.
	 * @throws IOException if an I/O error occurs during the request.
	 */
	public Response getResponse(String url) throws IOException {
		Request request = new Request.Builder().url(url).build();

		return client.newCall(request).execute();
	}

	// Main method for testing
	public static void main(String[] args) throws JSONException {
		HTTP http = new HTTP();
		try {
			Response response = http.getResponse("https://api.mojang.com/users/profiles/minecraft/ByAkko3063");

			// 1. Handle Status Code
			int statusCode = response.code();
			System.out.println("Status Code: " + statusCode);

			// 2. Handle Headers
			Headers headers = response.headers();
			System.out.println("Headers: " + headers);

			// 3. Handle Body (JSON Parsing)
			if (response.body() != null) {
				String responseBody = response.body().string();
				System.out.println("Body: " + responseBody);

				// Parse JSON body
				JSONObject jsonObject = new JSONObject(responseBody);
				String id = jsonObject.getString("id");
				String name = jsonObject.getString("name");

				// Print extracted values
				System.out.println("ID: " + id);
				System.out.println("Name: " + name);
			}

			response.close(); // Close the response to free resources
		} catch (IOException e) {
			System.err.println("Error making HTTP request: " + e.getMessage());
		}
	}
}
