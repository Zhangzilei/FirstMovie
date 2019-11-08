package com.bw.movie.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import com.bw.movie.R;
import com.bw.movie.view.adapter.HomePageAdapter;
import com.bw.movie.view.fragment.CinemaFragment;
import com.bw.movie.view.fragment.MovieFragment;
import com.bw.movie.view.fragment.MyFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HomePageActivity extends AppCompatActivity {

    @BindView(R.id.home_view_pager)
    ViewPager homeViewPager;
    @BindView(R.id.radio_group)
    RadioGroup radioGroup;
    private Unbinder mBind;
    private List<Fragment> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        mBind = ButterKnife.bind(this);

        mList.add(new MovieFragment());
        mList.add(new CinemaFragment());
        mList.add(new MyFragment());

        //创建适配器
        HomePageAdapter homePageAdapter = new HomePageAdapter(getSupportFragmentManager(), mList);
        homeViewPager.setAdapter(homePageAdapter);
        homeViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                radioGroup.check(radioGroup.getChildAt(i).getId());
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb1:
                        homeViewPager.setCurrentItem(0);
                        break;
                    case R.id.rb2:
                        homeViewPager.setCurrentItem(1);
                        break;
                    case R.id.rb3:
                        homeViewPager.setCurrentItem(2);
                        break;
                }
            }
        });

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBind.unbind();
    }
}
