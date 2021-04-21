package com.example.chessmatch;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBManager extends SQLiteOpenHelper {
    private static final String dbname = "chessmatchdatabase.db";
    boolean successful = false;

    public DBManager(Context context) {
        super(context, dbname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String qry = "create table recentmove (lastmove varchar(3), playerID integer primary key)";
        db.execSQL(qry);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS recentmove");
        onCreate(db);
    }

    public String addRecord(String p1, int p2) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS recentmove");
        onCreate(db);
        ContentValues cv = new ContentValues();
        cv.put("lastmove", p1);
        cv.put("playerID", p2);

        long res = db.insert("recentmove", null, cv);

        if (res == -1) {
            successful = false;
            return "Failed";
        } else {
            successful = true;
            return "Successfully inserted";
        }
    }

    public Cursor getRecentMove() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor;

        String query = "Select * from " + "recentmove";
        cursor = sqLiteDatabase.rawQuery(query, null);


        return cursor;
    }
}
