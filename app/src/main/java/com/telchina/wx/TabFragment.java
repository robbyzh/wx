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
    private int mIndex    = 0;

    public static final String TITLE = "title";
    public static final String INDEX = "index";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //获取参数
        if (getArguments() != null) {
            mTitle = getArguments().getString(TITLE);
            mIndex = getArguments().getInt(INDEX,0);
        }

        //第四个标签页显示MyView
        if(mIndex==0){
            MyView view=new MyView(getActivity());

            return view;
        }else{
            //创建一个TextView
            TextView tv = new TextView(getActivity());
            tv.setTextSize(30);
            tv.setBackgroundColor(Color.LTGRAY);
            tv.setText(mTitle + "  序号:" + mIndex);
            tv.setGravity(Gravity.CENTER);

            tv.setHeight(60);
            return tv;
        }
    }
}
