package com.example.addupdatelocationsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText email1,password1;
    Button login,Signup1;
    RegistrationDatabase database;
    float v=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email1=findViewById(R.id.email);
        password1=findViewById(R.id.pass);
        login=findViewById(R.id.login);
        Signup1=findViewById(R.id.signup1);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database=new RegistrationDatabase(Login.this);
                String nemail=email1.getText().toString();
                String npassword=password1.getText().toString();
                boolean b=database.LoginAccount(nemail,npassword);
                if (b)
                {
                    SharedPreferences sharedPreferences=getSharedPreferences("status",0);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("Signin","yes");
                    editor.apply();

                    Toast.makeText(Login.this, "Successfully Login", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(Login.this,Home.class);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Toast.makeText(Login.this, "Incorrect email or password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Signup1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Login.this,Sign_up.class);
                startActivity(intent);
                finish();
            }
        });
    }
}