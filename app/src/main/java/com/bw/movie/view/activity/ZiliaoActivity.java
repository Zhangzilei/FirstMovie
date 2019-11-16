package com.bw.movie.view.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.TimeUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.bw.movie.R;
import com.bw.movie.constraint.Constraint;
import com.bw.movie.dao.DaoMaster;
import com.bw.movie.dao.DaoSession;
import com.bw.movie.dao.UserDao;
import com.bw.movie.model.bean.User;
import com.bw.movie.model.bean.YYGuanZhuBean;
import com.bw.movie.model.bean.ZiLiaoBean;
import com.bw.movie.presenter.ZiLiaoPresenter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ZiliaoActivity extends BaseActivity<ZiLiaoPresenter> implements Constraint.ZiLiaoView {

    @BindView(R.id.xximg)
    ImageView xximg;
    @BindView(R.id.zlsimple)
    ImageView zlimg;
    @BindView(R.id.zlname)
    TextView zlname;
    @BindView(R.id.zlsex)
    TextView zlsex;
    @BindView(R.id.zltime)
    TextView zltime;
    @BindView(R.id.zlyouxiang)
    TextView zlyouxiang;
    private String sessionId;
    private int userId;
    private UserDao mUserDao;
    private TimePickerView pvTime;
    private String birthDay;

    @Override
    void initData() {
        DaoSession daoSession = DaoMaster.newDevSession(this, UserDao.TABLENAME);
        mUserDao = daoSession.getUserDao();

        List<User> users = mUserDao.loadAll();
        for (int i = 0; i < users.size(); i++) {
            sessionId = users.get(i).getSessionId();
            userId = users.get(i).getUserId();
            birthDay = users.get(i).getBirthDay();
        }

        presenter.ZiLiao(userId, sessionId);

        zltime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //时间选择器
                pvTime = new TimePickerBuilder(ZiliaoActivity.this, new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
                        zltime.setText(simpleDateFormat.format(date));

                        User user=new User();
                        user.setBirthDay(simpleDateFormat.format(date));
                        mUserDao.update(user);

                        presenter.ShengRi(userId,sessionId,simpleDateFormat.format(date));

                        zltime.setText(simpleDateFormat.format(date));
                    }
                }).build();

                pvTime.show();
            }
        });
    }

    @Override
    ZiLiaoPresenter getPresenter() {
        return new ZiLiaoPresenter();
    }

    @Override
    void initListener() {

    }

    @Override
    int initLayout() {
        return R.layout.activity_ziliao;
    }

    @Override
    public void ziliaoSuccess(ZiLiaoBean ziLiaoBean) {

        if (ziLiaoBean.status.equals("9999")) {
            Toast.makeText(this, "请先登录", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(ZiliaoActivity.this,LoginActivity.class);
            startActivity(intent);
        }else {
            ZiLiaoBean.ResultBean result = ziLiaoBean.result;
            Uri uri=Uri.parse(result.headPic);
            zlimg.setImageURI(uri);

            zlname.setText(result.nickName);
            if(result.sex==1){
                zlsex.setText("男");
            }else {
                zlsex.setText("女");
            }
            zltime.setText(birthDay);
            zlyouxiang.setText(result.email);
        }
    }

    @Override
    public void ziliaoError(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void shengqiSuccess(YYGuanZhuBean guanZhuBean) {
        Toast.makeText(this, guanZhuBean.message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void shengqiError(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}
