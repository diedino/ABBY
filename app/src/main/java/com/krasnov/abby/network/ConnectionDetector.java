package com.krasnov.abby.network;

import android.app.Service;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ConnectionDetector {

    private Context context;

    public ConnectionDetector(Context context) {
        this.context = context;
    }

    public boolean isConnected() {
        ConnectivityManager connectivity = (ConnectivityManager)
                context.getSystemService(Service.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null) {
                return info.getState() == NetworkInfo.State.CONNECTED;
            }
        }
        return false;
    }
}
