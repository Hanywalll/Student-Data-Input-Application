package com.example.inputmahasiswa;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DatabaseName = "data_mhs.db";
    public static final String TableName = "table_mhs";
    public static final int DatabaseVersion = 1;

    public static final String C_1 = "nim";
    public static final String C_2 = "nama";

    public DatabaseHelper(Context context){
        super(context,DatabaseName,null,DatabaseVersion);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE table_mhs (nim text null, nama text null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TableName);
        onCreate(db);
    }

    // Insert
    public boolean InsertData(String nim, String nama){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(C_1,nim);
        contentValues.put(C_2,nama);

        long result = db.insert(TableName,null,contentValues);
        if (result ==-1){
            return false;
        }else{
            return true;
        }
    }

    public boolean updateData(String nim,String nama,String bu){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(C_1,nim);
        contentValues.put(C_2,nama);

        db.update(TableName,contentValues,"nim = ?", new String[]{nim});
        return true;
    }

    public int deleteData(String nim){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TableName,"nim = ?",new String[]{nim});
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from table_mhs", null);
        return res;
    }

    // Update
}