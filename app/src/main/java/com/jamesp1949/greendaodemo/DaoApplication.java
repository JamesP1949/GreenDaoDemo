package com.jamesp1949.greendaodemo;

import android.app.Application;

import com.jamesp1949.greendaodemo.bean.DaoManager;
import com.socks.library.KLog;

/**
 * Created by JamesP949 on 2017/3/17.
 * Function:
 */

public class DaoApplication extends Application {
    public static DaoApplication mInstance = null;
    private DaoManager mDaoManager;
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        mDaoManager = DaoManager.getInstance(this);
        KLog.init(BuildConfig.LOG_DEBUG, "JamesP1949");
    }

    public DaoManager getDaoManager() {
        return mDaoManager;
    }
}
