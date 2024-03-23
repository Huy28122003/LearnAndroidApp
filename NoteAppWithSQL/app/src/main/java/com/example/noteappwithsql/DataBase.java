package com.example.noteappwithsql;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBase extends SQLiteOpenHelper {
    public DataBase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    // Làm việc với database thường có 2 dạng truy vấn
    // 1. Truy vấn ko có dữ liệu trả về: create, insert, update, delete,...
    public void QueryData(String str){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(str);
    }
    // 2. Truy vấn có dữ liệu trả về (kiểu con trỏ): select
    public Cursor GetData(String str){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(str,null);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
