package com.jamesp1949.greendaodemo.adapter;

import android.view.ViewGroup;
import android.widget.TextView;

import com.jamesp1949.greendaodemo.R;
import com.jamesp1949.greendaodemo.bean.GirlFriend;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by JamesP949 on 2017/3/17.
 * Function:
 */

public class GFViewHolder extends BaseViewHolder<GirlFriend> {
    TextView g_name;

    public GFViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_girlfriend_view);
        g_name = $(R.id.girlfriend);
    }

    @Override
    public void setData(GirlFriend data) {
        super.setData(data);
        g_name.setText(data.getName());
    }
}
