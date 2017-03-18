package com.jamesp1949.greendaodemo.module;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jamesp1949.greendaodemo.DaoApplication;
import com.jamesp1949.greendaodemo.R;
import com.jamesp1949.greendaodemo.adapter.One2OneAdapter;
import com.jamesp1949.greendaodemo.base.BaseToolBarActivity;
import com.jamesp1949.greendaodemo.bean.DaoManager;
import com.jamesp1949.greendaodemo.bean.Student;
import com.jamesp1949.greendaodemo.bean.Teacher;
import com.jamesp1949.greendaodemo.util.DataUtil;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by JamesP949 on 2017/3/17.
 * Function:一对多关联 老师---学生
 */

public class One2NActivity extends BaseToolBarActivity {
    @Bind(R.id.et_condition)
    EditText mEtCondition;
    @Bind(R.id.tv_search)
    TextView mTvSearch;
    @Bind(R.id.recyclerview)
    EasyRecyclerView mRecyclerview;
    private One2OneAdapter mAdapter;
    private List<Object> mDatas;
    private DaoManager mDaoManager;
    private String mKey;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_1ton;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        setToolbarTitle("1对N表关联");
        setToolbarIndicator(true);
        mDatas = new ArrayList<>();
        mDaoManager = DaoApplication.mInstance.getDaoManager();
        mAdapter = new One2OneAdapter(this);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerview.setAdapter(mAdapter);
        mRecyclerview.setErrorView(R.layout.x_common_query_error_view);
        mAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (mDatas.get(position) instanceof Teacher) {
                    Teacher teacher = (Teacher) mDatas.get(position);
                    if (!teacher.getMStudents().isEmpty()) {
                        Toast.makeText(One2NActivity.this, "您已经有不少学生了,给别人点活路吧！", Toast
                                .LENGTH_SHORT).show();
                    } else {
                        addStudents(teacher);
                    }
                }
            }
        });

    }  // 添加学生
    private void addStudents(Teacher teacher) {
        // 获取数据
        List<Student> studentList = DataUtil.getStudents();
        // 将老师的id设置进去
        for (Student student: studentList) {
            student.setT_id(teacher.getId());
        }
        // 插入数据库
        mDaoManager.insertStudents(studentList);
        mDaoManager.updateTeacher(teacher);
        List<Teacher> list = mDaoManager.queryTeachers(mKey);
        // 刷新页面
        refreshView(list);
    }

    @OnClick(R.id.tv_search)
    public void onClick() {
        mKey = mEtCondition.getEditableText().toString().trim();
        if (!TextUtils.isEmpty(mKey)) {
            List<Teacher> list = mDaoManager.queryTeachers(mKey);
            refreshView(list);
        } else
            Toast.makeText(this, "查询条件不能为空", Toast.LENGTH_SHORT).show();
    }

    /**
     * 刷新界面 选择将Teacher和Student的信息展示出来
     */
    private void refreshView(List<Teacher> list) {
        if (list.isEmpty()) {
            mAdapter.clear();
            mRecyclerview.showError();
        } else {
            mDatas.clear();
            for (Teacher t : list) {
                mDatas.add(t);
                if (!t.getMStudents().isEmpty()) {
                    for (Student s : t.getMStudents()) {
                        mDatas.add(s);
                    }
                }
            }
            mAdapter.clear();
            mAdapter.addAll(mDatas);
        }
    }
}
