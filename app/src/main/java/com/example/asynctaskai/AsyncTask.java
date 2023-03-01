package com.example.asynctaskai;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AsyncTask extends android.os.AsyncTask {

    String API_KEY;
    String TAG = "restapi";
    Request request;
    RequestBody requestBody;
    ResponseResult responseResult;
    String promptString;
    ImageView resultImage;
    Context context;
    ProgressBar progressBar;
    private final OkHttpClient client = new OkHttpClient();
    private String urlString = "https://stablediffusionapi.com/api/v3/text2img";

//    public AsyncTask(ResponseResult responseResult) {
//        this.responseResult = responseResult;
//    }
    public AsyncTask(ProgressBar progressBar, ImageView resultImage, String promptString, String API_KEY){
        this.resultImage = resultImage;
        this.promptString = promptString;
        this.progressBar = progressBar;
        this.API_KEY = "uIEJ5PoLL9xG5htZNQErA77kzwdm7v7mqbAZVMh2NvkPyq4g8LeGxLBqdL7m";
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressBar.setVisibility(View.VISIBLE);
    }

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
                .addFormDataPart("key", API_KEY)
                .addFormDataPart("prompt", promptString)
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

        Log.i("lol", promptString + " " + API_KEY);

        request = new Request.Builder()
                .url(urlString)
                .post(requestBody)
                .build();

        try {
            Response response = client.newCall(request).execute();

            Headers responseHeaders = response.headers();
            for (int i = 0; i < responseHeaders.size(); i++) {
                Log.i("Response", "Response: " + responseHeaders.name(i) + ": " + responseHeaders.value(i));
            }

            Gson gson = new Gson();
            responseResult = gson.fromJson(response.body().string(), ResponseResult.class);

            if(responseResult.getStatus() == "error"){
                Log.i("Response", "No result. Probably api expired/used");
            } else {
                Log.i("Response", responseResult.toString());
                publishProgress(responseResult.getOutput()[0], 0);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;

//        try {
//            Response response = client.newCall(request).execute();
//            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

//            Log.i("Response", "Raw response: " + response.body().string());
//            Gson gson = new Gson();
//            responseResult = gson.fromJson(response.body().string(), ResponseResult.class);
//            Log.i("Response", responseResult.toString());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        Log.i("Response", result);
//        return null;

    }

    @Override
    protected void onProgressUpdate(Object[] values) {
        Picasso.get()
                .load(values[0].toString())
                .resize(500, 500)
                .centerCrop()
                .into(resultImage);
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Object o) {
        progressBar.setVisibility(View.GONE);
        super.onPostExecute(o);
    }

}
