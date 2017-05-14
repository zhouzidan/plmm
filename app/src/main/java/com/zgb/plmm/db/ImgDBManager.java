package com.zgb.plmm.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zgb.plmm.model.ImgGroup;
import com.zgb.plmm.model.ImgModel;

import java.util.ArrayList;
import java.util.List;

public class ImgDBManager {
    private SQLiteDatabase database;

    private ImgDBManager() {
    }

    private static ImgDBManager instance;

    public static ImgDBManager get() {
        if (instance == null)
            instance = new ImgDBManager();
        return instance;
    }

    private void initDatabase() {
        if (database == null) {
            database = DBManager.get().getDatabase();
        }
    }


    public List<ImgModel> getNeedShowImgModelList(ImgGroup imgGroup) {
        initDatabase();
        List<ImgModel> imgModels = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * from img WHERE group_id = ? ;", new String[]{String.valueOf(imgGroup.getId())});
        while (cursor.moveToNext()) {
            String url = cursor.getString(cursor.getColumnIndex("url"));
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            int star = cursor.getInt(cursor.getColumnIndex("star"));
            ImgModel imgModel = new ImgModel();
            imgModel.setUrl(url);
            imgModel.setId(id);
            imgModel.setStar(star);
            imgModel.setImgGroup(imgGroup);
            imgModels.add(imgModel);
        }
        cursor.close();
        return imgModels;
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