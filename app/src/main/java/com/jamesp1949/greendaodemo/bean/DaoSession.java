package com.jamesp1949.greendaodemo.bean;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.jamesp1949.greendaodemo.bean.GirlFriend;
import com.jamesp1949.greendaodemo.bean.Student;
import com.jamesp1949.greendaodemo.bean.Teacher;

import com.jamesp1949.greendaodemo.bean.GirlFriendDao;
import com.jamesp1949.greendaodemo.bean.StudentDao;
import com.jamesp1949.greendaodemo.bean.TeacherDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig girlFriendDaoConfig;
    private final DaoConfig studentDaoConfig;
    private final DaoConfig teacherDaoConfig;

    private final GirlFriendDao girlFriendDao;
    private final StudentDao studentDao;
    private final TeacherDao teacherDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        girlFriendDaoConfig = daoConfigMap.get(GirlFriendDao.class).clone();
        girlFriendDaoConfig.initIdentityScope(type);

        studentDaoConfig = daoConfigMap.get(StudentDao.class).clone();
        studentDaoConfig.initIdentityScope(type);

        teacherDaoConfig = daoConfigMap.get(TeacherDao.class).clone();
        teacherDaoConfig.initIdentityScope(type);

        girlFriendDao = new GirlFriendDao(girlFriendDaoConfig, this);
        studentDao = new StudentDao(studentDaoConfig, this);
        teacherDao = new TeacherDao(teacherDaoConfig, this);

        registerDao(GirlFriend.class, girlFriendDao);
        registerDao(Student.class, studentDao);
        registerDao(Teacher.class, teacherDao);
    }
    
    public void clear() {
        girlFriendDaoConfig.clearIdentityScope();
        studentDaoConfig.clearIdentityScope();
        teacherDaoConfig.clearIdentityScope();
    }

    public GirlFriendDao getGirlFriendDao() {
        return girlFriendDao;
    }

    public StudentDao getStudentDao() {
        return studentDao;
    }

    public TeacherDao getTeacherDao() {
        return teacherDao;
    }

}
