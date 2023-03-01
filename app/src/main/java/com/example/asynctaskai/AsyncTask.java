package com.example.asynctaskai;

import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.Headers;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AsyncTask extends android.os.AsyncTask {

    String TAG = "restapi";
    Request request;
    RequestBody requestBody;
    private final OkHttpClient client = new OkHttpClient();
    private String urlString = "https://stablediffusionapi.com/api/v3/text2img";

    @Override
    protected Object doInBackground(Object[] objects) {
        Log.i(TAG, "Start doInBackground()");

//        try {
//            url = new URL(urlString);
//            Log.i(TAG, "Catched url: " + url);
//        } catch (MalformedURLException e) {
//            throw new RuntimeException(e);
//        }


        requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("key", "WBdPtqrNeitik3uo1HoCQFg7kCxj5HULSoxcoF4ecX7pTRH52eVHWRMIxUot")
                .addFormDataPart("prompt", "dog watching a sunset")
                .addFormDataPart("width", "512")
                .addFormDataPart("height", "512")
                .addFormDataPart("samples", "1")
                .addFormDataPart("num_inference_steps", "20")
//                .addFormDataPart("seed", null)
                .addFormDataPart("guidance_scale", "7.5")
                .addFormDataPart("safety_checker", "yes")
//                .addFormDataPart("webhook", null)
//                .addFormDataPart("track_id", null)
                .build();

        request = new Request.Builder()
                .url(urlString)
                .post(requestBody)
                .build();

        String result;
//        Response response;

        try {
            Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            Headers responseHeaders = response.headers();
            for (int i = 0; i < responseHeaders.size(); i++) {
                System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
                Log.i(TAG, "Response: " + responseHeaders.name(i) + ": " + responseHeaders.value(i));
            }

//            System.out.println(response.body().string());
            Log.i("Response", "Raw response: " + response.body().string());

//            result = response.body().string();
//            Log.i("Response", result);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        Log.i("Response", result);
        return null;
    }
}
