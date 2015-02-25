package com.leagapps.secretcal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Sam on 7/16/2014.
 */
public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "SecretCal";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NOTES_NAME = "Notes";



    private static final String DATABASE_CREATE = "";
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTES_NAME);
    }
}
