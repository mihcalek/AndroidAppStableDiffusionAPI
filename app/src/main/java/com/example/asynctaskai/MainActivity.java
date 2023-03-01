package com.example.asynctaskai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    TextInputEditText promptInput;
    ImageView resultImage;
    MaterialButton buttonGenerate;
    MaterialButton buttonCheckNet;
    AsyncTask asyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        promptInput = findViewById(R.id.editTextPrompt);
        resultImage = findViewById(R.id.imageView);
        buttonGenerate = findViewById(R.id.buttonGenerate);
        buttonCheckNet = findViewById(R.id.buttonCheckInternet);


        checkConnection();
        buttonCheckNet.setOnClickListener(click -> {
            checkConnection();
        });
        buttonGenerate.setOnClickListener(click -> {
            asyncTask = new AsyncTask();
            String a = String.valueOf(asyncTask.execute());
            System.out.println(a);
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
        int color;

        if (isConnected) {
            message = "Connected to Internet";

            color = Color.WHITE;

        } else {

            message = "Not Connected to Internet";

            color = Color.RED;
        }

        Snackbar snackbar = Snackbar.make(findViewById(R.id.buttonCheckInternet), message, Snackbar.LENGTH_SHORT);

        View view = snackbar.getView();

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