package com.example.listviewdemo;

import android.util.Log;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Httphandler {

    public Httphandler() {
    }

    public String makeRequest(String rUrl) {
        String response = null;

        try {
            URL url = new URL(rUrl);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");

            //Read Response
            InputStream inputStream = new BufferedInputStream(connection.getInputStream());
            response = convertStream(inputStream);
        } catch (IOException e) {
            Log.d("Httphandler", e.getMessage());
        }

        return response;
    }

    public String convertStream(InputStream is) {
        BufferedReader read = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;

        try {

            while ((line = read.readLine()) != null) {
                sb.append(line).append("\n");
            }
        } catch (IOException e) {
            Log.d("HttpHandler", e.getMessage());
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                Log.d("HttpHandler Finally", e.getMessage());
            }
        }

        return sb.toString();

    }
}