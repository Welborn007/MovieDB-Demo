package com.sample.mvvmarchitecture.Utilities;

import io.realm.RealmObject;

public class CommonRealmPOJO extends RealmObject {
    String Data,JSON;

    public String getData() {
        return Data;
    }

    public void setData(String data) {
        Data = data;
    }

    public String getJSON() {
        return JSON;
    }

    public void setJSON(String JSON) {
        this.JSON = JSON;
    }
}
