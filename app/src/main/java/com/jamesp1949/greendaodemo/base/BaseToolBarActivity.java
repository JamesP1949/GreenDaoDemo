package com.jamesp1949.greendaodemo.base;

import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jamesp1949.greendaodemo.R;
import com.socks.library.KLog;

import butterknife.ButterKnife;

/**
 * Created by JamesP949 on 2016/11/18.
 */

public abstract class BaseToolBarActivity extends AppCompatActivity {
    public Toolbar mToolbar;
    /**
     * Toolbar左侧按钮的样式
     */
    public int mToolbarIndicator;
    private ActionBar mActionBar;
    protected int menuId = -1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        ButterKnife.bind(this);
        initToolbar();
    }

    // 获取布局layoutId
    public abstract int getLayoutResId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (menuId != -1)
            getMenuInflater().inflate(menuId, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                finishAfterTransition();
            } else {
                finish();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initToolbar() {
        try {
            mToolbar = (Toolbar) findViewById(R.id.toolbar);
            if (mToolbar != null) {
                // 24.0.0版本后导航图标会有默认的与标题的距离，这里设置去掉
                mToolbar.setContentInsetStartWithNavigation(0);
                setSupportActionBar(mToolbar);

                mActionBar = getSupportActionBar();
                mActionBar.setElevation(10); // 设置阴影高版本才起作用
            }
        } catch (Exception e) {
//            throw new Exception("Do this activity has a toolbar layout?");
            e.printStackTrace();
            KLog.e("Do this activity has a toolbar layout?");
        }


    }

    protected void setToolbarBackGround(@NonNull int colorRes) {
        mToolbar.setBackgroundColor(ContextCompat.getColor(this, colorRes));
    }
    protected void setToolbarTitle(@NonNull String title) {
        if (mActionBar != null) {
            mActionBar.setDisplayShowTitleEnabled(false);
            TextView textView = getTextView(title);
            Toolbar.LayoutParams params = new Toolbar.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.CENTER);
            mToolbar.addView(textView, params);
        }

    }

    protected void setToolbarTitle(@NonNull int resId) {
        if (mActionBar != null) {
            mActionBar.setDisplayShowTitleEnabled(false);
            TextView textView = getTextView(resId);
            Toolbar.LayoutParams params = new Toolbar.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.CENTER);
            mToolbar.addView(textView, params);
        }

    }
    /**
     * 设置toolbar左边的文字指示器 并定义回调事件
     *
     * @param text
     * @param clickListener
     */
    protected void setToolbarLeftTextBtn(@NonNull String text, View.OnClickListener clickListener) {
        if (mActionBar != null)
            //是否显示toolbar左上角的指示器。false 不显示
            mActionBar.setDisplayHomeAsUpEnabled(false);
        TextView textView = getTextView(text);
        textView.setOnClickListener(clickListener);
        Toolbar.LayoutParams params = new Toolbar.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                Gravity.LEFT);
        mToolbar.addView(textView, params);

    }

    /**
     * 设置toolbar左上角的指示器
     *
     * @param hasToolbarIndicator
     */
    protected void setToolbarIndicator(boolean hasToolbarIndicator) {
        if (hasToolbarIndicator) {
            if (mActionBar != null) {
                mActionBar.setDisplayHomeAsUpEnabled(true);
                mActionBar.setHomeAsUpIndicator(R.drawable.icon_back);
            } else throw new IllegalStateException("Do this activity has a SupportActionBar ?");
        } else mActionBar.setDisplayHomeAsUpEnabled(false);
    }

    /**
     * ToolBar文字
     *
     * @param text        文字
     * @param textsize    文字大小
     * @param textColorId 文字颜色
     * @return
     */
    private TextView getTextView(CharSequence text, int textsize, int textColorId) {
        TextView textView = new TextView(this);
        textView.setText(text);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, textsize);
        textView.setGravity(Gravity.CENTER);
        textView.setTypeface(Typeface.DEFAULT_BOLD);
        textView.setTextColor(ContextCompat.getColor(this, textColorId));
        return textView;
    }

    private TextView getTextView(int resId, int textsize, int textColorId) {
        TextView textView = new TextView(this);
        textView.setText(resId);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,textsize);
        textView.setGravity(Gravity.CENTER);
        textView.setTypeface(Typeface.DEFAULT_BOLD);
        textView.setTextColor(ContextCompat.getColor(this, textColorId));
        return textView;
    }
    /**
     * 默认文字大小18sp,颜色白色
     *
     * @param text
     * @return
     */
    private TextView getTextView(CharSequence text) {
        return getTextView(text, 18, R.color.white);
    }

    private TextView getTextView(@NonNull int resId) {
        return getTextView(resId, 18, R.color.white);
    }
    @Override
    public void onBackPressed() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            super.onBackPressed();
        } else {
            finish();
            System.exit(0);
        }
    }
}
