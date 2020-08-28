package com.globomed.julyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


public class MainActivity extends AppCompatActivity{
/** Duration of wait**/
private final int SPLASH_DISPLAY_LENGHT = 1000;
//Called when the activity is first created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //New Handler to start next activity and closes this splash screen after some seconds
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Create an Intent that will start the next activity
                Intent intent = new Intent(MainActivity.this, OnboardingActivity.class);
                MainActivity.this.startActivity(intent);
                MainActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGHT);

    }
}
