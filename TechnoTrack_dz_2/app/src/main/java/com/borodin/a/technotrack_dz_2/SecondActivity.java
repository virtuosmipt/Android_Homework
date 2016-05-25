package com.borodin.a.technotrack_dz_2;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class SecondActivity extends AppCompatActivity {
    public static String LOG_TAG = "my_log";
    private static DBHelper dbHelper;
    private SQLiteDatabase database;
    private Cursor cursorData;
    private ListFragment _listFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        dbHelper = new DBHelper(this);
        cursorData=dbHelper.getAllData();
        ImageData.createInstance(cursorData);
       // ImageData.createInstance(cursorData);
        // используем адаптер данных
        _listFragment = new ListFragment();

    }


    @Override
    protected void onStart() {
        super.onStart();
        super.onStart();
        Fragment frag = getSupportFragmentManager().findFragmentById(R.id.main_fragment);
        if (frag == null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.main_fragment, _listFragment);
            ft.commit();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }




}
