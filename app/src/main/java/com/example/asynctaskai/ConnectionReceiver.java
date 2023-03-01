package com.example.asynctaskai;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ConnectionReceiver extends BroadcastReceiver {
    public static ReceiverListener Listener;

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (Listener != null) {

            boolean isConnected = networkInfo != null && networkInfo.isConnectedOrConnecting();

            Listener.onNetworkChange(isConnected);
        }
    }

    public interface ReceiverListener {
        void onNetworkChange(boolean isConnected);
    }
}