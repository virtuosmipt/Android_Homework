package com.borodin.a.technotrack_dz_2;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
    private Context context;
    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "dbtechnology";
    public static final String TABLE_TECHNOLOGY = "technology";


    // Поля
    public static final String TECHNOLOGY_ID = "_id";
    public static final String TECHNOLOGY_PICTURE = "picture";
    public static final String TECHNOLOGY_TITLE = "title";
    public static final String TECHNOLOGY_INFO = "info";

    // Скрипт создания таблицы
    static final String DB_CREATE = "create table " + TABLE_TECHNOLOGY + "("
            + TECHNOLOGY_ID + " integer primary key autoincrement, "
            + TECHNOLOGY_TITLE + " text, "
            + TECHNOLOGY_INFO  + " text, "
            + TECHNOLOGY_PICTURE  + " text " + ")";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = getWritableDatabase();
        return db.query(TABLE_TECHNOLOGY, null, null, null, null, null, null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DB_CREATE);
        Log.d(MainActivity.LOG_TAG,"onCreateBD Starting...");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_TECHNOLOGY);

        onCreate(db);
    }
}
