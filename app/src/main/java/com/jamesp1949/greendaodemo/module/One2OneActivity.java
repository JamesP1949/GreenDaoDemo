package com.jamesp1949.greendaodemo.module;

import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jamesp1949.greendaodemo.DaoApplication;
import com.jamesp1949.greendaodemo.R;
import com.jamesp1949.greendaodemo.adapter.One2OneAdapter;
import com.jamesp1949.greendaodemo.base.BaseToolBarActivity;
import com.jamesp1949.greendaodemo.bean.DaoManager;
import com.jamesp1949.greendaodemo.bean.GirlFriend;
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
 * Function: 1对1关联  老师---女友
 */

public class One2OneActivity extends BaseToolBarActivity {

    @Bind(R.id.et_name)
    EditText mEtName;
    @Bind(R.id.tv_add)
    TextView mTvAdd;
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
        return R.layout.activity_1to1;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        setToolbarTitle("1对1表关联");
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
                    if (teacher.getMGirlFriend() != null) {
                        Toast.makeText(One2OneActivity.this, "一个老师只能有一个女朋友,不能贪心呦！", Toast
                                .LENGTH_SHORT).show();
                    } else {
                        addGF(teacher);
                    }
                }
            }
        });

    }

    // 添加女友
    private void addGF(Teacher teacher) {
        // 制造女友
        GirlFriend girlFriend = DataUtil.getGirlFriend();
        // 插入数据库
        mDaoManager.insertGirlFriend(girlFriend);
        // 查询女友 获得主键id
        GirlFriend girlFriend1 = mDaoManager.queryGirlFriend(girlFriend.getName());
        // 更新获得女友的Teacher
        mDaoManager.updateTeacher(teacher, girlFriend1.getId());
        List<Teacher> list = mDaoManager.queryTeachers(mKey);
        // 刷新页面
        refreshView(list);
    }

    @OnClick({R.id.tv_add, R.id.tv_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_add:
                String name = mEtName.getEditableText().toString().trim();
                if (!TextUtils.isEmpty(name)) {
                    Teacher teacher = new Teacher();
                    teacher.setName(name);
                    /**
                     * 必须抓取异常 因为Teacher的name属性必须唯一(unique)
                     */
                    try {
                        mDaoManager.insertTeacher(teacher);
                        Toast.makeText(this, "添加成功！", Toast.LENGTH_SHORT).show();
                    } catch (SQLiteException e) {
                        Toast.makeText(this, "姓名重复", Toast.LENGTH_SHORT).show();
                    }
                } else
                    Toast.makeText(this, "姓名不能为空", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_search:
                mKey = mEtCondition.getEditableText().toString().trim();
                if (!TextUtils.isEmpty(mKey)) {
                    List<Teacher> list = mDaoManager.queryTeachers(mKey);
                    refreshView(list);
                } else
                    Toast.makeText(this, "查询条件不能为空", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    // 刷新界面
    private void refreshView(List<Teacher> list) {
        if (list.isEmpty()) {
            mAdapter.clear();
            mRecyclerview.showError();
        } else {
            mDatas.clear();
            for (Teacher t : list) {
                mDatas.add(t);
                if (t.getMGirlFriend() != null) {
                    mDatas.add(t.getMGirlFriend());
                }
            }
            mAdapter.clear();
            mAdapter.addAll(mDatas);
        }
    }
}
