package com.example.asynctaskai;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationManager;
import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {


    String API_KEY = "tSY4wB8OgC3YXUXmDab9vZXFAAScK3kY0ETXnN8fbVLr59lTupF5Dd101HIk";
    String promptString = "Dog watching a sunset";

    TextInputEditText editTextPrompt;
    TextInputEditText editTextApiKey;
    ImageView resultImage;
    MaterialButton buttonGenerate;
    MaterialButton buttonCheckNet;
    ResponseResult responseResult;
    AsyncTask asyncTask;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextPrompt = findViewById(R.id.editTextPrompt);
        editTextApiKey = findViewById(R.id.editTextAPIKey);
        resultImage = findViewById(R.id.imageView);
        buttonGenerate = findViewById(R.id.buttonGenerate);
        buttonCheckNet = findViewById(R.id.buttonCheckInternet);
        progressBar = findViewById(R.id.progressBar);

        responseResult = new ResponseResult();


        checkConnection();
        buttonCheckNet.setOnClickListener(click -> {
            checkConnection();
        });
        buttonGenerate.setOnClickListener(click -> {
            if (!editTextPrompt.getText().toString().isEmpty()){
                if(!editTextApiKey.getText().toString().isEmpty()){
                    API_KEY = editTextApiKey.getText().toString();
                }
                Log.i("apikey", promptString + " " + API_KEY);
                promptString = editTextPrompt.getText().toString();
                asyncTask = new AsyncTask(progressBar ,resultImage, promptString, API_KEY);
                asyncTask.execute();
            }
//            Log.i("lol", responseResult.getOutput()[0]);
//            String imageURL = responseResult.getOutput()[0];
//            Log.i("lol", imageURL);
        });
    }


    private void checkConnection() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.new.conn.CONNECTIVITY_CHANGE");

        registerReceiver(new ConnectionReceiver(), intentFilter);

        ConnectivityManager manager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        boolean isConnected = networkInfo != null && networkInfo.isConnectedOrConnecting();

        showSnackBar(isConnected);
    }

    private void showSnackBar(boolean isConnected) {
        String message;

        if (isConnected) {
            message = "Connected to Internet";
        } else {
            message = "Not connected to Internet";
        }
        Snackbar snackbar = Snackbar.make(findViewById(R.id.buttonCheckInternet), message, Snackbar.LENGTH_SHORT);
        snackbar.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkConnection();
    }

    @Override
    protected void onPause() {
        super.onPause();
        checkConnection();
    }
}