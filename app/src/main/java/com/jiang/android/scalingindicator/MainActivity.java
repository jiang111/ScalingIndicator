package com.jiang.android.scalingindicator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.jiang.android.indicator.TabPageIndicator;
import com.jiang.android.indicator.adapter.ModelFragmentPagerAdapter;
import com.jiang.android.indicator.model.PageModel;
import com.jiang.android.scalingindicator.fragment.SimpleFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TabPageIndicator mIndicator;
    private ViewPager mViewPager;
    private List<Fragment> mFragmentLists = new ArrayList<>();
    private List<PageModel> mDatas = new ArrayList<>();
    private ModelFragmentPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initFragments();
        initAdapter();
        setUI();
    }

    private void setUI() {
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOffscreenPageLimit(mDatas.size());
        mIndicator.setVisibility(View.VISIBLE);
        mIndicator.setViewPager(mViewPager, 0);

    }

    private void initAdapter() {
        mAdapter = new ModelFragmentPagerAdapter(getSupportFragmentManager()) {


            @Override
            public CharSequence getPageTitle(int position) {
                return mDatas.get(position).getName();
            }

            @Override
            public PageModel getPageModel(int position) {
                return mDatas.get(position);
            }

            @Override
            public int getCount() {
                return mFragmentLists.size();
            }

            @Override
            public Fragment getItem(int position) {
                return mFragmentLists.get(position);
            }
        };
    }

    private void initView() {
        mIndicator = (TabPageIndicator) findViewById(R.id.indicator);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);

        mIndicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mDatas.get(position).setCount(0);
                if (mAdapter != null && mIndicator != null) {
                    mAdapter.notifyDataSetChanged();
                    mIndicator.notifyDataSetChanged();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void initFragments() {
        mFragmentLists.add(SimpleFragment.newInstance("工厂方法"));
        mFragmentLists.add(SimpleFragment.newInstance("抽象工厂"));
        mFragmentLists.add(SimpleFragment.newInstance("单例模式"));
        mFragmentLists.add(SimpleFragment.newInstance("桥接模式"));
        mFragmentLists.add(SimpleFragment.newInstance("组合模式"));
        mFragmentLists.add(SimpleFragment.newInstance("外观模式"));

        mDatas.add(new PageModel("工厂方法", 1));
        mDatas.add(new PageModel("抽象工厂", 1));
        mDatas.add(new PageModel("单例模式", 1));
        mDatas.add(new PageModel("桥接模式", 1));
        mDatas.add(new PageModel("组合模式", 1));
        mDatas.add(new PageModel("外观模式", 1));
    }
}
