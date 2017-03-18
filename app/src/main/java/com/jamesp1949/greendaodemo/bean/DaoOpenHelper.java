package com.jamesp1949.greendaodemo.bean;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by JamesP949 on 2017/2/20.
 * Function:
 */

public class DaoOpenHelper extends DaoMaster.OpenHelper {
    public DaoOpenHelper(Context context, String name) {
        super(context, name, null);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
        MigrationHelper.migrate(db, TeacherDao.class, GirlFriendDao.class, StudentDao.class);
    }
}
