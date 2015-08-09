package com.telchina.wx;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.Window;

import com.telchina.wx.R;
import com.telchina.wx.base.ChangeColorIconWithText;
import com.telchina.wx.base.TabFragment;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends FragmentActivity implements
        View.OnClickListener,
        ViewPager.OnPageChangeListener {

    private ViewPager mViewPager;
    private List<Fragment> mTabs = new ArrayList<>();

    private String[] mTitles = new String[]{
            "第1个Fragment",
            "第2个Fragment",
            "第3个Fragment",
            "第4个Fragment"
    };

    private static final int FIRST_VIEW = 0;
    private static final int SECOND_VIEW = 1;
    private static final int THIRD_VIEW = 2;
    private static final int FORTH_VIEW = 3;

    public static final String NEWS = "com.telchina.wx.NEWS";

    private FragmentPagerAdapter mAdapter;

    private List<ChangeColorIconWithText> mTabIndicators = new ArrayList<ChangeColorIconWithText>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setOverFlowButtonAlaways();
        getActionBar().setDisplayShowHomeEnabled(false);

        initView();
        initDatas();
        mViewPager.setAdapter(mAdapter);

        intEvent();
    }

    private void intEvent() {
        mViewPager.setOnPageChangeListener(this);
    }

    /**
     * 初始化四个自定义view
     */
    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);

        ChangeColorIconWithText one = (ChangeColorIconWithText) findViewById(R.id.id_indicator_one);
        mTabIndicators.add(one);
        ChangeColorIconWithText two = (ChangeColorIconWithText) findViewById(R.id.id_indicator_two);
        mTabIndicators.add(two);
        ChangeColorIconWithText three = (ChangeColorIconWithText) findViewById(R.id.id_indicator_three);
        mTabIndicators.add(three);
        ChangeColorIconWithText four = (ChangeColorIconWithText) findViewById(R.id.id_indicator_four);
        mTabIndicators.add(four);

        //view的onclick事件
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);

        one.setIconAlpha(1.0f); //第一个view的透明度  不透明
    }

    private void initDatas() {
        int i = 0;
        for (String title : mTitles) {

            TabFragment tabFragment = new TabFragment();

            Bundle bundle = new Bundle();
            bundle.putString(TabFragment.TITLE, title);
            bundle.putInt(TabFragment.INDEX, i++);

            tabFragment.setArguments(bundle);
            mTabs.add(tabFragment);
        }

        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return mTabs.get(i);
            }

            @Override
            public int getCount() {
                return mTabs.size();
            }
        };
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_group_news) {

            Intent intent = new Intent(NEWS);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    private void setOverFlowButtonAlaways() {

        ViewConfiguration config = ViewConfiguration.get(this);
        try {
            Field menuKey = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
            menuKey.setAccessible(true);
            menuKey.setBoolean(config, false);

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {

        if (featureId == Window.FEATURE_ACTION_BAR && menu != null) {

            if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
                try {
                    Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible",
                            Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
        return super.onMenuOpened(featureId, menu);
    }

    @Override
    public void onClick(View v) {

        tabClick(v);
    }

    private void tabClick(View v) {
        resetOtherTabs();

        switch (v.getId()) {

            case R.id.id_indicator_one:
                mTabIndicators.get(FIRST_VIEW).setIconAlpha(1.0f);
                mViewPager.setCurrentItem(FIRST_VIEW, true);
                break;

            case R.id.id_indicator_two:
                mTabIndicators.get(SECOND_VIEW).setIconAlpha(1.0f);
                mViewPager.setCurrentItem(SECOND_VIEW, true);
                break;

            case R.id.id_indicator_three:
                mTabIndicators.get(THIRD_VIEW).setIconAlpha(1.0f);
                mViewPager.setCurrentItem(THIRD_VIEW, true);
                break;

            case R.id.id_indicator_four:
                mTabIndicators.get(FORTH_VIEW).setIconAlpha(1.0f);
                mViewPager.setCurrentItem(FORTH_VIEW, true);
                break;
        }
    }


    private void resetOtherTabs() {

        for (int i = 0; i < mTabIndicators.size(); i++) {
            mTabIndicators.get(i).setIconAlpha(0);
        }

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        if (positionOffset > 0) {
            ChangeColorIconWithText left = mTabIndicators.get(position);
            ChangeColorIconWithText right = mTabIndicators.get(position + 1);

            left.setIconAlpha(1 - positionOffset);
            right.setIconAlpha(positionOffset);
        }
    }

    @Override
    public void onPageSelected(int position) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {

        }

        if (newConfig.keyboardHidden == Configuration.KEYBOARDHIDDEN_NO) {

        }
    }

}
