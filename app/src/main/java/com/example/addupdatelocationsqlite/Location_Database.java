package com.example.addupdatelocationsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class Location_Database extends SQLiteOpenHelper {
    public final static String db_name ="My_Location";
    public final static String Table_name ="My_Loaction_Table";
    public final static String COL_1 ="Location_Name";
    public final static String COL_2 ="Location_Description";
    public final static String COL_3 ="Location_Image";
    Context context;
    public Location_Database(Context context) {
        super(context, db_name, null, 1);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+Table_name+"(Location_Name TEXT PRIMARY KEY, Location_Description TEXT, Location_Image BLOB)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+Table_name);
    }

    public void InsertData(String lname,String lders,byte[] image)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_1,lname);
        contentValues.put(COL_2,lders);
        contentValues.put(COL_3,image);
        Cursor cursor= db.rawQuery("SELECT * FROM "+Table_name+" WHERE Location_Name=?",new String[] {lname});
        if (cursor.getCount()>0)
        {
            Toast.makeText(context, "Location Name ALready exists", Toast.LENGTH_SHORT).show();
        }
        else
        {
            long a=db.insert(Table_name,null,contentValues);
            if (a==-1)
            {
                Toast.makeText(context, "Insertion failed", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(context, "Record successfully inserted", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public Cursor imgdecs(String lname)
    {
        try
        {
            SQLiteDatabase db=this.getReadableDatabase();
            Cursor cursor=db.rawQuery("SELECT * FROM "+Table_name+" WHERE Location_Name=?", new String[] {lname});
            if (cursor.moveToFirst())
            {
                return cursor;
            }
            else
            {
                return null;
            }
        }catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public void UpdateLocation(String lname,String ldesc, byte[] img)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(COL_2,ldesc);
        cv.put(COL_3,img);
        Cursor cursor=db.rawQuery("SELECT * FROM "+Table_name+" WHERE Location_Name=?",new String[] {lname});
        if (cursor.getCount()>0)
        {
            long a=db.update(Table_name,cv,"Location_Name=?",new String[] {lname});
            if (a==-1)
            {
                Toast.makeText(context, "Error Updating", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(context, "Location Successfully Updated", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(context, "Location not found", Toast.LENGTH_SHORT).show();
        }
    }
    public void DeleteLocation(String Lname)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor= db.rawQuery("SELECT * FROM "+Table_name+" WHERE Location_Name=?",new String[] {Lname});
        if (cursor.getCount()>0)
        {
            long a=db.delete(Table_name,"Location_Name=?", new String[] {Lname});
            if (a==-1)
            {
                Toast.makeText(context, "Location deletion faild", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(context, "Location Successfully Deleted", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(context, "No Location Found", Toast.LENGTH_SHORT).show();
        }
    }
    public Cursor AllRecordDisplay()
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor= db.rawQuery("SELECT * FROM "+Table_name,null);
        if (cursor.getCount()>0)
        {
            return cursor;
        }
        else
        {
            return null;
        }
    }
}
