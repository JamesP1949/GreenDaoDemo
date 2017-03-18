package com.jamesp1949.greendaodemo.bean;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;

/**
 * Created by JamesP949 on 2017/3/17.
 * Function:
 */
@Entity
public class Student {
    @Id(autoincrement = true)
    private Long id;
    private String name;
    private float score;

    private Long t_id;
    @ToOne(joinProperty = "t_id")
    private Teacher mTeacher;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 1943931642)
    private transient StudentDao myDao;
    @Generated(hash = 1323478661)
    private transient Long mTeacher__resolvedKey;
    @Generated(hash = 27082703)
    public Student(Long id, String name, float score, Long t_id) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.t_id = t_id;
    }
    @Generated(hash = 1556870573)
    public Student() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public float getScore() {
        return this.score;
    }
    public void setScore(float score) {
        this.score = score;
    }
    public Long getT_id() {
        return this.t_id;
    }
    public void setT_id(Long t_id) {
        this.t_id = t_id;
    }
    /** To-one relationship, resolved on first access. */
    @Generated(hash = 928604501)
    public Teacher getMTeacher() {
        Long __key = this.t_id;
        if (mTeacher__resolvedKey == null || !mTeacher__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            TeacherDao targetDao = daoSession.getTeacherDao();
            Teacher mTeacherNew = targetDao.load(__key);
            synchronized (this) {
                mTeacher = mTeacherNew;
                mTeacher__resolvedKey = __key;
            }
        }
        return mTeacher;
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1330230626)
    public void setMTeacher(Teacher mTeacher) {
        synchronized (this) {
            this.mTeacher = mTeacher;
            t_id = mTeacher == null ? null : mTeacher.getId();
            mTeacher__resolvedKey = t_id;
        }
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1701634981)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getStudentDao() : null;
    }
}
