package com.telchina.wx.news;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.Window;

import com.telchina.wx.R;
import com.telchina.wx.base.BaseActivity;

public class NewsContentActivity extends BaseActivity {

    public static void actionStart(Context context, String newsTitle, String newsContent) {
        Intent intent = new Intent(context, NewsContentActivity.class);
        intent.putExtra("news_list", newsTitle);
        intent.putExtra("news_content", newsContent);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.news_content);

        String newsTitle = getIntent().getStringExtra("news_list");
        String newsContent = getIntent().getStringExtra("news_content");

        FragmentManager fm = getSupportFragmentManager();
        NewsContentFragment fragment = (NewsContentFragment) fm.findFragmentById(R.id.news_content_fragment);
        fragment.refresh(newsTitle, newsContent);
    }
}
