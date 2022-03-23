package com.example.addupdatelocationsqlite;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class Update_location extends AppCompatActivity {

    EditText LocationName,description;
    ImageView locationPic;
    Button updatedlocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_location);
        LocationName=findViewById(R.id.locationNameU);
        description=findViewById(R.id.locationDescU);
        locationPic=findViewById(R.id.locationImageU);
        updatedlocation=findViewById(R.id.UpdateLocation);
        Location_Database location_database=new Location_Database(Update_location.this);

        locationPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,1);
            }
        });
        updatedlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nlname=LocationName.getText().toString();
                String nldesc=description.getText().toString();
                if (nlname.isEmpty()) {
                    Toast.makeText(Update_location.this, "Location Name can not be empty!!!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    try {
                        byte[] bb =imgtobyesconvert(locationPic);
                        location_database.UpdateLocation(nlname, nldesc, bb);
                    }catch (Exception e)
                    {
                        Toast.makeText(Update_location.this, "Please Select an Image", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private byte[] imgtobyesconvert(ImageView locationPic) {
        Bitmap bitmap=((BitmapDrawable) locationPic.getDrawable()).getBitmap();
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,80,byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1)
        {
            if (resultCode==RESULT_OK)
            {
                Uri uri=data.getData();
                locationPic.setImageURI(uri);
            }
        }
    }
}