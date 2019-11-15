package com.bw.movie.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.constraint.Constraint;
import com.bw.movie.dao.DaoMaster;
import com.bw.movie.dao.DaoSession;
import com.bw.movie.dao.UserDao;
import com.bw.movie.model.bean.User;
import com.bw.movie.model.bean.YYGuanZhuBean;
import com.bw.movie.model.bean.YYXiangQingBean;
import com.bw.movie.presenter.YYXiangQingPresenter;
import com.bw.movie.view.fragment.YYOneFragment;
import com.bw.movie.view.fragment.YYTwoFragment;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class YingYuanXiangQing extends BaseActivity<YYXiangQingPresenter> implements Constraint.YYXiangQingView {

    @BindView(R.id.yyname)
    TextView yyname;
    @BindView(R.id.yyimg)
    CheckBox yyimg;
    @BindView(R.id.yyxq_back)
    ImageView yyxq_back;
    @BindView(R.id.bq1)
    TextView bq1;
    //    @BindView(R.id.bq2)
//    TextView bq2;
//    @BindView(R.id.bq3)
//    TextView bq3;
    @BindView(R.id.yytab)
    TabLayout yytab;
    @BindView(R.id.yypage)
    ViewPager yypage;
    @BindView(R.id.dao)
    TextView dao;
    @BindView(R.id.line1)
    RelativeLayout line1;
    @BindView(R.id.yybtnpaiqi)
    Button yybtnpaiqi;
    private List<Fragment> list = new ArrayList<>();
    private String id;
    private UserDao mUserDao;
    private String sessionId;
    private int userId;
    private SharedPreferences sp;
    private SharedPreferences.Editor edit;
    private int id1;

    @Override
    void initData() {

        sp = getSharedPreferences("guanzhua", MODE_PRIVATE);
        edit = sp.edit();

        DaoSession daoSession = DaoMaster.newDevSession(this, UserDao.TABLENAME);
        mUserDao = daoSession.getUserDao();

        List<User> users = mUserDao.loadAll();
        for (int i = 0; i < users.size(); i++) {
            sessionId = users.get(i).getSessionId();
            userId = users.get(i).getUserId();
        }

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        presenter.YYXiangQinga(Integer.valueOf(id));

        yytab.addTab(yytab.newTab());
        yytab.addTab(yytab.newTab());

        list.add(new YYOneFragment());
        list.add(new YYTwoFragment());

        yypage.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return list.get(i);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });

        yytab.setupWithViewPager(yypage);

        yytab.getTabAt(0).setText("影院详情");
        yytab.getTabAt(1).setText("影院评价");

        yyxq_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        boolean yyimgcheck = sp.getBoolean("yyimgcheck", false);
        if (yyimgcheck) {
            yyimg.setChecked(yyimgcheck);
        } else {
            yyimg.setChecked(yyimgcheck);
        }

        yyimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (yyimgcheck != true) {
                    presenter.YYguanzhu(userId, sessionId, Integer.valueOf(id));
                } else {
                    presenter.YYquguan(userId, sessionId, Integer.valueOf(id));
                }
            }
        });

        yybtnpaiqi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(YingYuanXiangQing.this,PaiQiActivity.class);
                intent1.putExtra("yyid",id1);
                startActivity(intent1);
            }
        });

    }


    @Override
    YYXiangQingPresenter getPresenter() {
        return new YYXiangQingPresenter();
    }

    @Override
    void initListener() {

    }

    @Override
    int initLayout() {
        return R.layout.activity_ying_yuan_xiang_qing;
    }

    @Override
    public void xiangqingSuccess(YYXiangQingBean xiangQingBean) {
        Toast.makeText(this, xiangQingBean.result.name, Toast.LENGTH_SHORT).show();
        yyname.setText(xiangQingBean.result.name);
        bq1.setText(xiangQingBean.result.label);
        id1 = xiangQingBean.result.id;

        String address = xiangQingBean.result.address;
        String vehicleRoute = xiangQingBean.result.vehicleRoute;
        String phone = xiangQingBean.result.phone;

        Map<String, String> map = new HashMap<>();
        map.put("address", address);
        map.put("vehic", vehicleRoute);
        map.put("phone", phone);
        EventBus.getDefault().postSticky(map);

    }

    @Override
    public void xiangqingError(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void guanzhuSuccess(YYGuanZhuBean guanZhuBean) {

        if(guanZhuBean.status.equals("9999")){
            Toast.makeText(YingYuanXiangQing.this, "请先登录哇~", Toast.LENGTH_SHORT).show();
            Intent intent1 = new Intent(YingYuanXiangQing.this, LoginActivity.class);
            startActivity(intent1);
        }

        edit.clear();
        edit.commit();
        if (guanZhuBean.status.equals("0000") || guanZhuBean.status.equals("1001")) {
            yyimg.setChecked(true);
            edit.putBoolean("yyimgcheck", true);
            edit.commit();
            Toast.makeText(this, guanZhuBean.message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void guanzhuError(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void quguanSuccess(YYGuanZhuBean guanZhuBean) {

        edit.clear();
        edit.commit();
        if (guanZhuBean.status.equals("0000")) {
            yyimg.setChecked(false);
            edit.putBoolean("yyimgcheck", false);
            edit.commit();
            Toast.makeText(this, guanZhuBean.message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void quguanError(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}

