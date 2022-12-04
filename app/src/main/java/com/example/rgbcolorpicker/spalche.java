package com.example.rgbcolorpicker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class spalche extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalche);

        Thread thread = new Thread() {
            @Override
            public void run() {
                try {

                    sleep(1000);
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    finish();
                    startActivity(i);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();

    }
}
