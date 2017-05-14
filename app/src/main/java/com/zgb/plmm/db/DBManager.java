package com.zgb.plmm.db;

import android.database.sqlite.SQLiteDatabase;

import com.zgb.plmm.App;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class DBManager {
    private String DB_NAME = "plmm.db";
    private SQLiteDatabase database;


    private DBManager() {
        if (database == null)
            database = getDatabase();
    }

    private static DBManager instance;

    public static DBManager get() {
        if (instance == null)
            instance = new DBManager();
        return instance;
    }

    //把assets目录下的db文件复制到dbpath下
    public SQLiteDatabase getDatabase() {
        if (database != null)
            return database;
        copyDBFile();
        return SQLiteDatabase.openOrCreateDatabase(getDBFilePath(), null);
    }

    public void closeDatabase() {
        if (database != null && database.isOpen())
            database.close();
        if (database != null)
            database = null;
    }

    private void copyDBFile() {
        String dbPath = getDBFilePath();
        File dbFile = new File(dbPath);
        dbFile.getParentFile().mkdirs();
        if (!dbFile.exists()) {
            try {
                FileOutputStream out = new FileOutputStream(dbPath);
                InputStream in = App.getAppContext().getAssets().open(DB_NAME);
                byte[] buffer = new byte[1024];
                int readBytes = 0;
                while ((readBytes = in.read(buffer)) != -1)
                    out.write(buffer, 0, readBytes);
                in.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String getDBFilePath() {
        return "/data/data/" + App.getAppContext().getPackageName()
                + "/databases/" + DB_NAME;
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