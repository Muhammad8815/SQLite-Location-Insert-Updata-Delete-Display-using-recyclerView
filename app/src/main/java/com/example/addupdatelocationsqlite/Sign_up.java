package com.example.addupdatelocationsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Sign_up extends AppCompatActivity {

    EditText username,email,pass,confirmpass;
    Button login1,signUp;
    RegistrationDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        username=findViewById(R.id.user);
        email=findViewById(R.id.email1);
        pass=findViewById(R.id.pass1);
        confirmpass=findViewById(R.id.confirmpass);
        signUp=findViewById(R.id.signup);
        login1=findViewById(R.id.login1);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nuser=username.getText().toString();
                String nemail=email.getText().toString();
                String npass=pass.getText().toString();
                String nconpass=confirmpass.getText().toString();
                if (nemail.isEmpty()||npass.isEmpty()||nconpass.isEmpty())
                {
                    Toast.makeText(Sign_up.this, "All field must be filled", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if (npass.equals(nconpass))
                    {
                        database=new RegistrationDatabase(Sign_up.this);
                        boolean b=database.Registration(nuser,nemail,npass);
                        if (b)
                        {
                            try {
                                SharedPreferences sharedPreferences=getSharedPreferences("status",0);
                                SharedPreferences.Editor editor=sharedPreferences.edit();
                                editor.putString("Signin","yes");
                                editor.apply();

                                Intent intent=new Intent(Sign_up.this,Home.class);
                                startActivity(intent);
                                finish();
                            }catch (Exception e)
                            {
                                Toast.makeText(Sign_up.this, "Something wrong "+ e, Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    else
                    {
                        Toast.makeText(Sign_up.this, "Password doesn't matches", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        login1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Sign_up.this,Login.class);
                startActivity(intent);
                finish();
            }
        });

    }
}