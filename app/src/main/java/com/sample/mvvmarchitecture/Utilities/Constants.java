package com.sample.mvvmarchitecture.Utilities;

public class Constants {

    static {
        System.loadLibrary("keys");
    }

    public static native String getAPIKey();
    public static String API_Key = getAPIKey();
}
