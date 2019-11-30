package com.bw.movie.view.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bw.movie.R;
import com.bw.movie.view.fragment.MyplypFragment;
import com.bw.movie.view.fragment.MyplyyFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PingLunActivity extends AppCompatActivity {

    @BindView(R.id.xximg)
    ImageView xximg;
    @BindView(R.id.mypltab)
    TabLayout mypltab;
    @BindView(R.id.myplview)
    ViewPager myplview;
    List<Fragment> list=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ping_lun);
        ButterKnife.bind(this);

        mypltab.addTab(mypltab.newTab());
        mypltab.addTab(mypltab.newTab());

        list.add(new MyplypFragment());
        list.add(new MyplyyFragment());

        myplview.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return list.get(i);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });

        mypltab.setupWithViewPager(myplview);

        mypltab.getTabAt(0).setText("电影");
        mypltab.getTabAt(1).setText("影院");

    }

    @OnClick(R.id.xximg)
    public void onClick() {
        finish();
    }
}
