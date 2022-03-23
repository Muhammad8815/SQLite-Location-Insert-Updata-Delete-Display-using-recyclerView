package com.example.addupdatelocationsqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class Recycler_View extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<String> LocationNameArray = new ArrayList<>();
    ArrayList<String> LocationDescriptionArry = new ArrayList<>();
    ArrayList<Bitmap> bitmap = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        recyclerView=findViewById(R.id.Recycle);

        recyclerView.setHasFixedSize(true);
        Location_Database location_database=new Location_Database(Recycler_View.this);
        Cursor cursor=location_database.AllRecordDisplay();
        if (cursor==null)
        {
            Toast.makeText(this, "No Location Stored yet", Toast.LENGTH_SHORT).show();
        }
        else
        {
            try {
                while (cursor.moveToNext()) {
                    LocationNameArray.add(cursor.getString(0));
                    LocationDescriptionArry.add(cursor.getString(1));
                    bitmap.add(byteTobitmap(cursor.getBlob(2)));
                }
                Recycler_Adapter recycler_adapter = new Recycler_Adapter(LocationNameArray, LocationDescriptionArry, bitmap, Recycler_View.this);
                recyclerView.setAdapter(recycler_adapter);
            }catch (Exception e)
            {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private Bitmap byteTobitmap(byte[] bytes)
    {
        return BitmapFactory.decodeByteArray(bytes,0,bytes.length);
    }
}