package com.jamesp1949.greendaodemo.adapter;

import android.view.ViewGroup;
import android.widget.TextView;

import com.jamesp1949.greendaodemo.R;
import com.jamesp1949.greendaodemo.bean.Teacher;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by JamesP949 on 2017/3/17.
 * Function:
 */

public class TeacherViewHolder extends BaseViewHolder<Teacher> {
    TextView t_name;

    public TeacherViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_teacher_view);
        t_name = $(R.id.t_name);
    }

    @Override
    public void setData(Teacher data) {
        super.setData(data);
        t_name.setText(data.getName());
    }
}
