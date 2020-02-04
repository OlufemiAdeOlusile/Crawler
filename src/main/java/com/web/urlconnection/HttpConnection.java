package com.web.urlconnection;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;


public class HttpConnection {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpConnection.class);
    private static final String REQUEST_METHOD = "HEAD";


    public boolean verifyStatusCode(String url, int code) {
        int statusCode;

        try {
            HttpURLConnection urlConnection = (HttpURLConnection) (new URL(url).openConnection());
            urlConnection.setRequestMethod(REQUEST_METHOD);
            urlConnection.connect();
            statusCode = urlConnection.getResponseCode();
            urlConnection.disconnect();
        } catch (IOException e) {
            LOGGER.error("Connection issues on {}", url);
            return false;
        }


        return statusCode == code;
    }
}
