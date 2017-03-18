package com.jamesp1949.greendaodemo.adapter;

import android.view.ViewGroup;
import android.widget.TextView;

import com.jamesp1949.greendaodemo.R;
import com.jamesp1949.greendaodemo.bean.Student;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by JamesP949 on 2017/3/17.
 * Function:
 */

public class StudentViewHolder extends BaseViewHolder<Student> {
    TextView s_name, s_score;
    public StudentViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_student_view);
        s_name = $(R.id.s_name);
        s_score = $(R.id.s_score);
    }

    @Override
    public void setData(Student data) {
        super.setData(data);
        s_name.setText(data.getName());
        s_score.setText(String.valueOf(data.getScore()));
    }
}
