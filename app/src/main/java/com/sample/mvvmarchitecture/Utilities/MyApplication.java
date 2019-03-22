package com.sample.mvvmarchitecture.Utilities;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;
import com.sample.mvvmarchitecture.R;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MyApplication extends MultiDexApplication {

    private static MyApplication mInstance;
    public final static String TAG = MyApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        try
        {

            MultiDex.install(this);

            Realm.init(this);
            RealmConfiguration config = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build();
            Realm.setDefaultConfiguration(config);
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }

    public static Dialog showDialog(Context context){
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.progressdialog);
        dialog.setCanceledOnTouchOutside(true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();
        return dialog;
    }
}
