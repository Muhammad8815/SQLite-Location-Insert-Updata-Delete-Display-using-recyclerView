package com.example.addupdatelocationsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Display_Location extends AppCompatActivity {

    EditText lname;
    TextView ldes;
    ImageView lpic;
    Button display;                                                                                                         
    Location_Database location_database1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_location);
        lname=findViewById(R.id.locationNameD);
        ldes=findViewById(R.id.locationDescD);
        lpic=findViewById(R.id.locationImageD);
        display=findViewById(R.id.DisplayLocation);

        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nlname=lname.getText().toString();
                if (nlname.isEmpty())
                {
                    Toast.makeText(Display_Location.this, "Location Name can't be empty!!!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    location_database1=new Location_Database(Display_Location.this);
                    Cursor cursor=location_database1.imgdecs(nlname);
                    if (cursor!=null)
                    {
                        ldes.setText(cursor.getString(1));
                        byte[] bytes=cursor.getBlob(2);
                        Bitmap bitmap= byteTobitmap(bytes);
                        lpic.setImageBitmap(bitmap);
                    }
                    else
                    {
                        Toast.makeText(Display_Location.this, "Not Location found", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
    private Bitmap byteTobitmap(byte[] bytes)
    {
        return BitmapFactory.decodeByteArray(bytes,0,bytes.length);
    }
}