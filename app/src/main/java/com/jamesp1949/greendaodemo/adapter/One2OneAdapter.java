package com.jamesp1949.greendaodemo.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.jamesp1949.greendaodemo.bean.GirlFriend;
import com.jamesp1949.greendaodemo.bean.Student;
import com.jamesp1949.greendaodemo.bean.Teacher;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.security.InvalidParameterException;

/**
 * Created by JamesP949 on 2017/3/17.
 * Function:
 */

public class One2OneAdapter extends RecyclerArrayAdapter<Object> {
    public static final int TYPE_INVALID = 0;
    public static final int TYPE_TEACHER = 1;  // 老师
    public static final int TYPE_GIRLFRIEND = 2; // 女友
    public static final int TYPE_STUDENT = 3; //学生

    public One2OneAdapter(Context context) {
        super(context);
    }

    @Override
    public int getViewType(int position) {
        if (getItem(position) instanceof Teacher)
            return TYPE_TEACHER;
        else if (getItem(position) instanceof GirlFriend)
            return TYPE_GIRLFRIEND;
        else if (getItem(position) instanceof Student) {
            return TYPE_STUDENT;
        } else
            return TYPE_INVALID;

    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_TEACHER:
                return new TeacherViewHolder(parent);
            case TYPE_GIRLFRIEND:
                return new GFViewHolder(parent);
            case TYPE_STUDENT:
                return new StudentViewHolder(parent);
            default:
                throw new InvalidParameterException();
        }
    }
}
