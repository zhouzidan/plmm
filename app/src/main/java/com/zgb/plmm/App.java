package com.zgb.plmm;

import android.app.Application;
import android.content.Context;

import com.crashlytics.android.Crashlytics;
import com.zgb.plmm.db.DBManager;

import io.fabric.sdk.android.Fabric;

/**
 * Created by zhou on 2017/5/12.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        this.mContext = getApplicationContext();
        Fabric.with(this, new Crashlytics());
        DBManager.get();
    }

    private static Context mContext ;
    public static Context getAppContext(){
        return mContext;
    }




}
