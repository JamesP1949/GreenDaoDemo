package com.jamesp1949.greendaodemo.module;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jamesp1949.greendaodemo.DaoApplication;
import com.jamesp1949.greendaodemo.R;
import com.jamesp1949.greendaodemo.base.BaseToolBarActivity;
import com.jamesp1949.greendaodemo.bean.DaoManager;
import com.jamesp1949.greendaodemo.bean.Teacher;
import com.jamesp1949.greendaodemo.util.DataUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class MainActivity extends BaseToolBarActivity {

    @Bind(R.id.tv_1to1)
    TextView mTv1to1;
    @Bind(R.id.tv_1toN)
    TextView mTv1toN;
    private DaoManager mDaoManager;
    private List<Teacher> mTeacherList;
    @Override
    public int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolbarTitle("GreenDao");
        init();
    }

    private void init() {
        mDaoManager = DaoApplication.mInstance.getDaoManager();
        mTeacherList = new ArrayList<>();
        mTeacherList.addAll(mDaoManager.queryAllTeachers());
        if (mTeacherList.isEmpty()) {
            mDaoManager.insertTeachers(DataUtil.getSimple());
        }
    }

    @OnClick({R.id.tv_1to1, R.id.tv_1toN})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_1to1:
                Intent intent = new Intent(this, One2OneActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_1toN:
                Intent intent1 = new Intent(this, One2NActivity.class);
                startActivity(intent1);
                break;
        }
    }
}
