package com.example.addupdatelocationsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;

public class Home extends AppCompatActivity {

    Button insertd,updated,displayd,deleted,RecyclerVieww,logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        insertd=findViewById(R.id.insertd);
        displayd=findViewById(R.id.displayd);
        updated=findViewById(R.id.updated);
        logout=findViewById(R.id.logout);
        deleted=findViewById(R.id.deleted);
        RecyclerVieww=findViewById(R.id.Recycler_Vieww);

        insertd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Home.this,Insert_class.class);
                startActivity(intent);
            }
        });
        displayd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Home.this,Display_Location.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences=getSharedPreferences("status",0);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("Signin","no");
                editor.apply();

                Intent intent=new Intent(Home.this, Login.class);
                startActivity(intent);
                finish();
            }
        });
        updated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Home.this, Update_location.class);
                startActivity(intent);
            }
        });
        deleted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Home.this, Delete_location.class);
                startActivity(intent);
            }
        });
        RecyclerVieww.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Home.this, Recycler_View.class);
                startActivity(intent);
            }
        });

    }
}