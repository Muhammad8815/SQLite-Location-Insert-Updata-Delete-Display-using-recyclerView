package com.example.addupdatelocationsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class RegistrationDatabase extends SQLiteOpenHelper {
    public final static String Registration_db_name ="My_Registration_new";
    public final static String Registration_Table_name ="My_Table_new";
    public final static String Registration_COL_1 ="Username";
    public final static String Registration_COL_2 ="Email";
    public final static String Registration_COL_3 ="Password";
    Context context;

    public RegistrationDatabase(Context context) {
        super(context, Registration_db_name, null, 1);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+Registration_Table_name+"(Username TEXT, Email TEXT, Password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("DROP TABLE IF EXISTS "+Registration_Table_name);
    }
    public boolean Registration(String username,String email,String password)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(Registration_COL_1,username);
        contentValues.put(Registration_COL_2,email);
        contentValues.put(Registration_COL_3,password);
        Cursor cursor= db.rawQuery("SELECT * FROM "+Registration_Table_name+" WHERE Username=? OR Email=?",new String[] {username,email});
        if (cursor.getCount()>0)
        {
            Toast.makeText(context, "Username or Email ALready exists Please try different", Toast.LENGTH_SHORT).show();
            return false;
        }
        else
        {
            long a=db.insert(Registration_Table_name,null,contentValues);
            if (a==-1)
            {
                Toast.makeText(context, "Account creation failed", Toast.LENGTH_SHORT).show();
                return false;
            }
            else {
                Toast.makeText(context, "Account successfully created", Toast.LENGTH_SHORT).show();
                return true;
            }
        }
    }

    public boolean LoginAccount(String Email, String Password)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM "+Registration_Table_name+" WHERE Email=? AND Password=?", new String[] {Email, Password});
        if (cursor.getCount()>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
