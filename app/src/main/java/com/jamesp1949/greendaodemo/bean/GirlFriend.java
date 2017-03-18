package com.jamesp1949.greendaodemo.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by JamesP949 on 2017/3/17.
 * Function:
 */

@Entity
public class GirlFriend {
    @Id(autoincrement = true)
    private Long id;

    private String name;

    @Generated(hash = 315062142)
    public GirlFriend(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Generated(hash = 736151275)
    public GirlFriend() {
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

}
