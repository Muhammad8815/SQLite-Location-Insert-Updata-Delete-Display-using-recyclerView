package com.example.addupdatelocationsqlite;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    Handler handler=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sharedPreferences=getSharedPreferences("status",0);
                String checklogin=sharedPreferences.getString("Signin","");
                if (checklogin.equals("yes"))
                {
                    Intent intentHome=new Intent(MainActivity.this,Home.class);
                    startActivity(intentHome);
                    finish();
                }
                else
                {
                    Intent intentLogin=new Intent(MainActivity.this,Login.class);
                    startActivity(intentLogin);
                    finish();
                }
            }
        },3000);
    }
}