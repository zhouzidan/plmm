package com.zgb.plmm.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zgb.plmm.App;
import com.zgb.plmm.model.ImgGroup;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ImgGroupDBManager {
    private SQLiteDatabase database;

    private ImgGroupDBManager() {
    }

    private static ImgGroupDBManager instance;

    public static ImgGroupDBManager get() {
        if (instance == null)
            instance = new ImgGroupDBManager();
        return instance;
    }

    private void initDatabase() {
        if (database == null) {
            database = DBManager.get().getDatabase();
        }
    }


    public List<ImgGroup> getNeedShowImgGroup(int startIndex, int size) {
        initDatabase();
        List<ImgGroup> imgGroups = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * from img_group ORDER BY star DESC LIMIT ?,?", new String[]{String.valueOf(startIndex), String.valueOf(startIndex + size)});
        while (cursor.moveToNext()) {
            String first_url = cursor.getString(cursor.getColumnIndex("first_url"));
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            int star = cursor.getInt(cursor.getColumnIndex("star"));
            String alt = cursor.getString(cursor.getColumnIndex("alt"));
            ImgGroup imgGroup = new ImgGroup();
            imgGroup.setFirstImgUrl(first_url);
            imgGroup.setAlt(alt);
            imgGroup.setStar(star);
            imgGroups.add(imgGroup);
        }
        cursor.close();
        return imgGroups;
    }


/*    //查询
    public City query(SQLiteDatabase sqliteDB, String[] columns, String selection, String[] selectionArgs) {
        City city = null;
        try {
            String table = "city";
            Cursor cursor = sqliteDB.query(table, columns, selection, selectionArgs, null, null, null);
            if (cursor.moveToFirst()) {
                String parentCity = cursor.getString(cursor
                        .getColumnIndex("parent"));
                String phoneCode = cursor.getString(cursor.getColumnIndex("phone_code"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String pinyin = cursor.getString(cursor.getColumnIndex("pinyin"));
                String cityID = cursor.getString(cursor.getColumnIndex("posID"));
                String areaCode = cursor.getString(cursor.getColumnIndex("area_code"));
                city = new City(parentCity, name, pinyin, phoneCode, cityID, areaCode);
                cursor.moveToNext();
                cursor.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return city;
    }*/
}