package com.huang.greendaodemo.base;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.huang.greendaodemo.gen.DaoMaster;
import com.huang.greendaodemo.gen.DaoSession;

/**
 * Created by huang-pc on 2016/12/31.
 */

public class BaseApplication extends Application{

    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    public static BaseApplication instances;


    @Override
    public void onCreate() {
        super.onCreate();
        instances = this;
        init();

    }

    public static BaseApplication getInstances() {
        return instances;
    }

    public void init() {
        //数据库的配置
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(instances, "text-db", null);
        db = devOpenHelper.getWritableDatabase();
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    public SQLiteDatabase getDb() {
        return db;
    }

}