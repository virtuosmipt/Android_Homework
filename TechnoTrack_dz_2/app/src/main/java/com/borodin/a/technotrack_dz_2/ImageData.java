package com.borodin.a.technotrack_dz_2;

import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vlad on 07/04/16.
 */
public class ImageData {
   Cursor cursor;
   public static String LOG_TAG = "my_log";
   public ImageData (Cursor cursor){
      this.cursor=cursor;
   }
   public static class Image {
      private String url;
      private String descr;
      private String title;

      public Image(String url,String title,String descr) {
         this.url = url;
         this.descr = descr;
         this.title= title;
      }

      public String getImage() {
         return url;
      }

      public String getText() {
         return title;
      }
   }

   public static ArrayList<Image> list ;

   private static ImageData _instance;

   protected ImageData() {
   }

   public static void createInstance(Cursor cursor) {
      if (null == _instance) {

         _instance = new ImageData(cursor);
         _instance.init(_instance.cursor);
      }
   }
   public  void seeAllData(){
      for(Image image: list){
         Log.d(LOG_TAG,image.url);
      }
   }

   static public ImageData instance() {
      return _instance;
   }

   public ArrayList<Image> getImages() {
      return list;
   }

   public Image getImage(int pos) {
      return list.get(pos);
   }

   private void init(Cursor cursor) {
      list = new ArrayList<>();
      if (cursor.moveToFirst()) {

         int pictureIndex = cursor.getColumnIndex(DBHelper.TECHNOLOGY_PICTURE);
         int titleIndex = cursor.getColumnIndex(DBHelper.TECHNOLOGY_TITLE);
         int infoIndex = cursor.getColumnIndex(DBHelper.TECHNOLOGY_INFO);

         do {

            list.add(new Image(cursor.getString(pictureIndex),cursor.getString(titleIndex),cursor.getString(infoIndex)));
           // Log.d(LOG_TAG, cursor.getString(pictureIndex));

            // holder.imageView
         } while (cursor.moveToNext());

      }
      else {
         Log.d(LOG_TAG,"NEEEEEEEEEEEEEEEEEEEEEEEEEEee");
      }
   }



}
