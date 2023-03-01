package com.example.asynctaskai;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class HTTPRequest {
    public static void POSTrequest() throws IOException {
        URL reqURL;
        try {
            reqURL = new URL("http://www.stackoverflow.com/");  //the URL we will send the request to
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        HttpURLConnection request = (HttpURLConnection) (reqURL.openConnection());

        String post = "this will be the post data that you will send";

        request.setDoOutput(true);
        request.addRequestProperty("Content-Length", Integer.toString(post.length())); //add the content length of the post data
        request.addRequestProperty("Content-Type", "application/x-www-form-urlencoded"); //add the content type of the request, most post data is of this type
        request.setRequestMethod("POST");
        request.connect();
        OutputStreamWriter writer = new OutputStreamWriter(request.getOutputStream()); //we will write our request data here
        writer.write(post);
        writer.flush();
    }

    public static void GETrequest() {
        URL reqURL;
        try {
            reqURL = new URL("http://www.stackoverflow.com/");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        HttpURLConnection request = null;
        try {
            request = (HttpURLConnection) (reqURL.openConnection());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            request.setRequestMethod("GET");
        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        }
        try {
            request.connect();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        
    }
}
