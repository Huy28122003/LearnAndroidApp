package com.example.sqlitewithimage;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

public class DataBase extends SQLiteOpenHelper {
    public DataBase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void setData(String query){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(query);
    }

    public Cursor getData(String query){
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery(query, null);
    }

    public void insertData(String name, String status, double price, byte[] image){
        SQLiteDatabase db = getWritableDatabase();
        String str = "insert into products (name,status,price,image) values (?,?,?,?)";
        SQLiteStatement stmt = db.compileStatement(str);
        stmt.clearBindings();
        stmt.bindString(1,name);
        stmt.bindString(2,status);
        stmt.bindDouble(3,price);
        stmt.bindBlob(4,image);
        stmt.executeInsert();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
