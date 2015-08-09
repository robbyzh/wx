package com.telchina.wx.news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.telchina.wx.R;

import java.util.List;

/**
 * Created by zg on 2015/8/9.
 */
public class NewsAdapter extends ArrayAdapter<News> {

    private int resourceId;
    private TextView newsTitle;

    public NewsAdapter(Context context, int textViewResourceId, List<News> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        News news = getItem(position);
        View view;

        if (null == convertView) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
        } else {
            view = convertView;
        }
        newsTitle = (TextView) view.findViewById(R.id.news_title);
        newsTitle.setText(news.getTitle());
        return view;
    }


}
