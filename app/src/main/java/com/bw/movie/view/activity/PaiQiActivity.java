package com.bw.movie.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.constraint.Constraint;
import com.bw.movie.model.bean.PaiQiTimeBean;
import com.bw.movie.presenter.PaiQiTimePresenter;
import com.bw.movie.view.fragment.PaiQiFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PaiQiActivity extends BaseActivity<PaiQiTimePresenter> implements Constraint.PaiQiTimeView {
    @BindView(R.id.pqimg)
    ImageView pqimg;
    @BindView(R.id.paiqitab)
    TabLayout paiqitab;
    @BindView(R.id.pqviewpager)
    ViewPager pqviewpager;
    List<Fragment> list = new ArrayList<>();

    @Override
    void initData() {

        Intent intent = getIntent();
        int yyid = intent.getIntExtra("yyid", 0);

        presenter.PaiQiTime();

        paiqitab.addTab(paiqitab.newTab());
        paiqitab.addTab(paiqitab.newTab());
        paiqitab.addTab(paiqitab.newTab());
        paiqitab.addTab(paiqitab.newTab());
        paiqitab.addTab(paiqitab.newTab());
        paiqitab.addTab(paiqitab.newTab());
        paiqitab.addTab(paiqitab.newTab());

        list.add(new PaiQiFragment(yyid));
        list.add(new PaiQiFragment(yyid));
        list.add(new PaiQiFragment(yyid));
        list.add(new PaiQiFragment(yyid));
        list.add(new PaiQiFragment(yyid));
        list.add(new PaiQiFragment(yyid));
        list.add(new PaiQiFragment(yyid));

        pqviewpager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return list.get(i);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });


        paiqitab.setupWithViewPager(pqviewpager);

    }

    @Override
    PaiQiTimePresenter getPresenter() {
        return new PaiQiTimePresenter();
    }

    @Override
    void initListener() {

    }

    @Override
    int initLayout() {
        return R.layout.activity_pai_qi;
    }

    @Override
    public void paiqitimeSuccess(PaiQiTimeBean paiQiTimeBean) {
        List<String> result = paiQiTimeBean.result;
        String s = result.toString();
        String[] split = s.split(",");
        paiqitab.getTabAt(0).setText(split[0]);
        paiqitab.getTabAt(1).setText(split[1]);
        paiqitab.getTabAt(2).setText(split[2]);
        paiqitab.getTabAt(3).setText(split[3]);
        paiqitab.getTabAt(4).setText(split[4]);
        paiqitab.getTabAt(5).setText(split[5]);
        paiqitab.getTabAt(6).setText(split[6]);
    }

    @Override
    public void paiqitimeError(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}
