package com.borodin.a.technotrack_dz_2;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {
    public static String LOG_TAG = "my_log";
    DBHelper dbHelper;
    SQLiteDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        dbHelper = new DBHelper(this);
        //dbHelper.onUpgrade(database,1,2);
        //Log.d(LOG_TAG, "dnawda");
        new ParseTask().execute();



//        ContentValues contentValues = new ContentValues();
//        contentValues.put(DBHelper.TECHNOLOGY_TITLE, "test");
//        database.insert(DBHelper.TABLE_TECHNOLOGY, null, contentValues);
//
//      Cursor cursor = dbHelper.getAllData();
//        Log.d(LOG_TAG, "dnawda");
//
//        Log.d(LOG_TAG, String.valueOf(cursor.getColumnCount()));
//        if(cursor.moveToFirst()){
//            do{
//                int titleIndex = cursor.getColumnIndex(DBHelper.TECHNOLOGY_PICTURE);
//                  Log.d(LOG_TAG,"Image: " + cursor.getString(titleIndex));
//
//            }while (cursor.moveToNext());
//        }
//        else
//             Log.d(LOG_TAG,"ВСЕ ОЧЕНЬ ПЛОХО!!!!!");
//
//       cursor.close();
//        dbHelper.close();

//
        Thread timer = new Thread()
        {
            public void run()
            {
                try
                {
                    int logoTimer = 0;

                    while(logoTimer < 2000) {
                        sleep(100);
                        logoTimer = logoTimer +100;
                    }
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    startActivity(intent);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    finish();
                }
            }
        };
        timer.start();
    }

    private class ParseTask extends AsyncTask<Void, Void, String> {
       // ArrayList<String> idList = new ArrayList<>();
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String resultJson = "";

        @Override
        protected String doInBackground(Void... params) {
            // получаем данные с внешнего ресурса
            try {
                URL url = new URL("http://mobevo.ext.terrhq.ru/shr/j/ru/technology.js");

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();

                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                resultJson = buffer.toString();

            } catch (Exception e) {
                e.printStackTrace();
            }
         //   Log.d(LOG_TAG,"DO in BACKGROUND!!!!!!");
            return resultJson;
        }

        @Override
        protected void onPostExecute(String strJson) {
            super.onPostExecute(strJson);

            database = dbHelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            JSONObject dataJsonObj = null;
            try {
                dataJsonObj = new JSONObject(strJson);
                JSONObject technology = dataJsonObj.getJSONObject("technology");
                Iterator<String> keysItr = technology.keys();
                while(keysItr.hasNext()) {
                    String key = (String)keysItr.next();
                    if ( technology.get(key) instanceof JSONObject ) {
                        JSONObject keysObject = technology.getJSONObject(key);
                        contentValues.put(DBHelper.TECHNOLOGY_TITLE, keysObject.getString("title"));
                        if (keysObject.has("info")) {
                            contentValues.put(DBHelper.TECHNOLOGY_INFO, keysObject.getString("info"));
                        }
                        else {
                            contentValues.put(DBHelper.TECHNOLOGY_INFO, "");
                        }
                        contentValues.put(DBHelper.TECHNOLOGY_PICTURE,"http://mobevo.ext.terrhq.ru/"+ keysObject.getString("picture").toString());
                        database.insert(DBHelper.TABLE_TECHNOLOGY, null, contentValues);

                    }

                }


            } catch (JSONException e) {
                e.printStackTrace();
            }



        }
    }
}

