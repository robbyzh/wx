package com.telchina.wx;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * tab页显示的内容
 */
public class TabFragment extends Fragment {

    private String mTitle = "Default";

    public static final String TITLE = "title";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //获取参数
        if (getArguments() != null) {
            mTitle = getArguments().getString(TITLE);
        }

        //创建一个TextView
        TextView tv = new TextView(getActivity());
        tv.setTextSize(30);
        tv.setBackgroundColor(Color.parseColor("#ffffffff"));
        tv.setText(mTitle);
        tv.setGravity(Gravity.CENTER);

        return tv;
    }

}
