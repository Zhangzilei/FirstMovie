package com.bw.movie.view.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bw.movie.R;
import com.bw.movie.view.fragment.GZDYFragment;
import com.bw.movie.view.fragment.GZYYFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GuanZhuActivity extends AppCompatActivity {

    @BindView(R.id.xximg)
    ImageView xximg;
    @BindView(R.id.gztab)
    TabLayout gztab;
    @BindView(R.id.gzview)
    ViewPager gzview;
    List<Fragment> list=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guan_zhu);
        ButterKnife.bind(this);

        gztab.addTab(gztab.newTab());
        gztab.addTab(gztab.newTab());

        list.add(new GZDYFragment());
        list.add(new GZYYFragment());

        gzview.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return list.get(i);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });

        gztab.setupWithViewPager(gzview);

        gztab.getTabAt(0).setText("电影");
        gztab.getTabAt(1).setText("影院");
    }
}
