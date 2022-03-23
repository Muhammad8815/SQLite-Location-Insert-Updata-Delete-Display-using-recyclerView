package com.example.addupdatelocationsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Delete_location extends AppCompatActivity {

    EditText DeleteLo;
    Button Deletebtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_location);
        DeleteLo=findViewById(R.id.DeleteLocationName);
        Deletebtn=findViewById(R.id.DeleteBTN);

        Deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dname=DeleteLo.getText().toString();
                if (dname.isEmpty())
                {
                    Toast.makeText(Delete_location.this, "Location Name is Empty", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Location_Database location_database=new Location_Database(Delete_location.this);
                    location_database.DeleteLocation(dname);
                }
            }
        });
    }
}