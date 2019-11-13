package com.bw.movie.view.activity;

import android.content.Intent;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
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
    private List<Fragment> list = new ArrayList<>();
    private String id;
    private boolean YYImgCheck;
    private UserDao mUserDao;
    private String sessionId;
    private int userId;

    @Override
    void initData() {

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

        yyimg.setChecked(YYImgCheck);

        yyimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(yyimg.isChecked()){
                    YYImgCheck=true;
                    if (userId!=0){
                        presenter.YYguanzhu(userId, sessionId, Integer.valueOf(id));
                    }else {
                        Toast.makeText(YingYuanXiangQing.this, "请先登录哇~", Toast.LENGTH_SHORT).show();
                    }
                    yyimg.setChecked(YYImgCheck);
                }else {
                    YYImgCheck=false;
                    presenter.YYquguan(userId, sessionId, Integer.valueOf(id));
                    yyimg.setChecked(YYImgCheck);
                }
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
        guanZhuBean.yyimgcheck=true;
        Toast.makeText(this, guanZhuBean.message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void guanzhuError(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void quguanSuccess(YYGuanZhuBean guanZhuBean) {
        guanZhuBean.yyimgcheck=false;
        Toast.makeText(this, guanZhuBean.message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void quguanError(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

}
