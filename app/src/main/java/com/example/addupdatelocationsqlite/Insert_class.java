package com.example.addupdatelocationsqlite;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class Insert_class extends AppCompatActivity {

    EditText lname;
    EditText ldesc;
    Button insertl;
    ImageView locationpic;
    Location_Database location_database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_class);
        ldesc=findViewById(R.id.locationDesc);
        lname=findViewById(R.id.locationName);
        insertl=findViewById(R.id.insertLocation);
        locationpic=findViewById(R.id.locationImage);

        locationpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(Insert_class.this);
                LayoutInflater inflater=getLayoutInflater();
                View view1=inflater.inflate(R.layout.choose_galler_camera,null);
                builder.setView(view1);
                builder.setCancelable(true);

                AlertDialog alertDialog=builder.create();
                alertDialog.show();
                ImageView cam=view1.findViewById(R.id.cam);
                ImageView pic=view1.findViewById(R.id.pic);

                pic.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        picClick();
                        alertDialog.cancel();
                    }
                });
                cam.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (checkperm())
                        {
                            camClick();
                        }
                        alertDialog.cancel();
                    }
                });

            }
        });
        insertl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nlname=lname.getText().toString();
                String nldes=ldesc.getText().toString();
                if (nlname.isEmpty()||nldes.isEmpty())
                {
                    Toast.makeText(Insert_class.this, "All fields must be filled dont left anything empty", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    try {
                        byte[] b=imgToByt(locationpic);
                        location_database = new Location_Database(Insert_class.this);
                        location_database.InsertData(nlname, nldes, b);
                    }catch (Exception e)
                    {
                        Toast.makeText(Insert_class.this, "Please Select an Image", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private byte[] imgToByt(ImageView imageView)
    {
        Bitmap bitmap=((BitmapDrawable) imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,80,byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    private void camClick() {
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager())!=null)
        {
            startActivityForResult(intent,2);
        }
    }

    private void picClick() {
        Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode)
        {
            case 1:
                if (resultCode==RESULT_OK)
                {
                    Uri uri=data.getData();
                    locationpic.setImageURI(uri);
                }
                break;
            case 2:
                if (resultCode==RESULT_OK)
                {
                    Bundle bundle=data.getExtras();
                    Bitmap bitmap1=(Bitmap) bundle.get("data");
                    locationpic.setImageBitmap(bitmap1);
                }
        }
    }
    public boolean checkperm()
    {
        if (Build.VERSION.SDK_INT>=23)
        {
            int c= ActivityCompat.checkSelfPermission(Insert_class.this, Manifest.permission.CAMERA);
            if (c==PackageManager.PERMISSION_DENIED)
            {
                ActivityCompat.requestPermissions(Insert_class.this, new String[] {Manifest.permission.CAMERA},33);
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==33 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
        {
            camClick();
        }
        else
        {
            Toast.makeText(this, "Please Grant Camera Permission", Toast.LENGTH_SHORT).show();
        }
    }
}