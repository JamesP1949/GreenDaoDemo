package com.jamesp1949.greendaodemo.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.OrderBy;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.DaoException;

import java.util.List;

/**
 * Created by JamesP949 on 2017/3/17.
 * Function:对象实体--老师
 */
@Entity
public class Teacher {
    @Id(autoincrement = true)
    private Long id;

    @NotNull
    @Unique
    private String name;

    private Long g_id;  // 关联两个表的外键
    @ToOne(joinProperty = "g_id")
    private GirlFriend mGirlFriend;  // 源实体

    @ToMany(referencedJoinProperty = "t_id")
    @OrderBy(value = "score DESC")
    private List<Student> mStudents;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 648119699)
    private transient TeacherDao myDao;

    @Generated(hash = 447752328)
    private transient Long mGirlFriend__resolvedKey;

    @Generated(hash = 250738344)
    public Teacher(Long id, @NotNull String name, Long g_id) {
        this.id = id;
        this.name = name;
        this.g_id = g_id;
    }

    @Generated(hash = 1630413260)
    public Teacher() {
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

    public Long getG_id() {
        return this.g_id;
    }

    public void setG_id(Long g_id) {
        this.g_id = g_id;
    }

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 400379903)
    public GirlFriend getMGirlFriend() {
        Long __key = this.g_id;
        if (mGirlFriend__resolvedKey == null
                || !mGirlFriend__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            GirlFriendDao targetDao = daoSession.getGirlFriendDao();
            GirlFriend mGirlFriendNew = targetDao.load(__key);
            synchronized (this) {
                mGirlFriend = mGirlFriendNew;
                mGirlFriend__resolvedKey = __key;
            }
        }
        return mGirlFriend;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 311396782)
    public void setMGirlFriend(GirlFriend mGirlFriend) {
        synchronized (this) {
            this.mGirlFriend = mGirlFriend;
            g_id = mGirlFriend == null ? null : mGirlFriend.getId();
            mGirlFriend__resolvedKey = g_id;
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
    @Generated(hash = 1349174479)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getTeacherDao() : null;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1758362269)
    public List<Student> getMStudents() {
        if (mStudents == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            StudentDao targetDao = daoSession.getStudentDao();
            List<Student> mStudentsNew = targetDao._queryTeacher_MStudents(id);
            synchronized (this) {
                if (mStudents == null) {
                    mStudents = mStudentsNew;
                }
            }
        }
        return mStudents;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1799575573)
    public synchronized void resetMStudents() {
        mStudents = null;
    }
}
