package com.sample.mvvmarchitecture;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import com.sample.mvvmarchitecture.Genres.HomePageActivity;
import com.sample.mvvmarchitecture.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

        ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        Thread myThread = new Thread()
        {
            @Override
            public void run() {
                try {
                    sleep(4000);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            startApp();
                        }
                    });

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        myThread.start();
    }

    public void startApp()
    {
        Intent intent = new Intent(MainActivity.this, HomePageActivity.class);
        startActivity(intent);
        finish();
    }
}
