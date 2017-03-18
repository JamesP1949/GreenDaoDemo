package com.jamesp1949.greendaodemo.bean;

import android.content.Context;

import com.socks.library.KLog;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by JamesP949 on 2017/2/20.
 * Function:数据库管理类
 */

public class DaoManager {
    public static final String DB_NAME = "JamesP1949.db";
    private static DaoManager mInstance;
    private Context mContext;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    public static DaoManager getInstance(Context context) {
        if (mInstance == null) {
            synchronized (DaoManager.class) {
                if (mInstance == null) {
                    mInstance = new DaoManager(context);
                }
            }
        }
        return mInstance;
    }

    private DaoManager(Context context) {
        mContext = context.getApplicationContext();
        Database database = new DaoOpenHelper(mContext, DB_NAME).getWritableDb();
        mDaoMaster = new DaoMaster(database);
        mDaoSession = mDaoMaster.newSession();
        QueryBuilder.LOG_SQL = true;
        QueryBuilder.LOG_VALUES = true;
    }

    public TeacherDao getTeacherDao() {
        // 去除数据库缓存
        mDaoSession.clear();
        return mDaoSession.getTeacherDao();
    }

    public GirlFriendDao getGirlFriendDao() {
//        mDaoSession.clear();
        return mDaoSession.getGirlFriendDao();
    }

    public StudentDao getStudentDao() {
//        mDaoSession.clear();
        return mDaoSession.getStudentDao();
    }

    /* *****************************Record--Start*********************************************/

    /**
     * 插入一条数据
     *
     * @param entity
     */
    public void insertTeacher(Teacher entity) throws DaoException {
        KLog.e("Person_id：" + entity.getId());
        if (entity == null) return;
        long insert = getTeacherDao().insert(entity);
        KLog.e("添加成功 rowId:" + insert);

    }

    /**
     * 插入多条数据
     *
     * @param entities
     */
    public void insertTeachers(List<Teacher> entities) {
        if (entities.isEmpty()) return;
        getTeacherDao().insertInTx(entities);

    }

    /**
     * @param key 主键
     * @return
     */
    public Teacher queryTeacher(Long key) {
        QueryBuilder<Teacher> builder = getTeacherDao().queryBuilder();
        builder.where(TeacherDao.Properties.Id.eq(key));
        return builder.uniqueOrThrow();
    }

    /**
     * 模糊查询
     *
     * @param key
     * @return
     */
    public List<Teacher> queryTeachers(String key) {
        QueryBuilder<Teacher> builder = getTeacherDao().queryBuilder();
        builder.where(TeacherDao.Properties.Name.like("%" + key + "%"));
        return builder.list();
    }

    /**
     * 查询所有
     *
     * @return
     */
    public List<Teacher> queryAllTeachers() {
        QueryBuilder<Teacher> builder = getTeacherDao().queryBuilder();
        return builder.list();
    }

    /**
     * 删除一条数据
     *
     * @param entity
     */

    public void deleteTeacher(Teacher entity) {
        if (entity == null) return;
        getTeacherDao().delete(entity);
    }

    /**
     * 更新老师信息
     *
     * @param teacher
     * @param g_key   女友的主键id
     */
    public void updateTeacher(Teacher teacher, long g_key) {
        teacher.setG_id(g_key);
        getTeacherDao().update(teacher);
    }

    public void updateTeacher(Teacher teacher) {
        getTeacherDao().update(teacher);
    }

    /* *****************************Teacher--End*********************************************/

    /* *****************************GirlFriend--Start*********************************************/
    public void insertGirlFriend(GirlFriend entity) {
        if (entity == null) return;
        long insert = getGirlFriendDao().insert(entity);
        KLog.e("新增一个女朋友：" + insert);
    }

    public GirlFriend queryGirlFriend(String name) {
        QueryBuilder<GirlFriend> builder = getGirlFriendDao().queryBuilder();
        builder.where(GirlFriendDao.Properties.Name.eq(name));
        return builder.uniqueOrThrow();
    }
    /* *****************************GirlFriend--End*********************************************/

    public void insertStudents(List<Student> entities) {
        if (entities.isEmpty()) return;
        getStudentDao().insertInTx(entities);
    }
}
