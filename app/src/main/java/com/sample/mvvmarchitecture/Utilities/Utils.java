package com.sample.mvvmarchitecture.Utilities;

import android.content.Context;
import android.net.ConnectivityManager;

import com.google.gson.Gson;

public class Utils {

    public static boolean isNetworkAvailable(Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    public static Object fromJson(String storedValue)
    {
        Gson gson = new Gson();
        Object objectModel = gson.fromJson(storedValue, Object.class);
        return objectModel;
    }

    public static String toJson(Object object)
    {
        Gson gson = new Gson();
        String stringValue = gson.toJson(object);
        return stringValue;
    }
}


