package com.bw.movie.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.constraint.Constraint;
import com.bw.movie.dao.DaoMaster;
import com.bw.movie.dao.DaoSession;
import com.bw.movie.dao.UserDao;
import com.bw.movie.model.bean.User;
import com.bw.movie.model.bean.YuYueBean;
import com.bw.movie.presenter.YuYuePresenter;
import com.bw.movie.view.adapter.YuYueAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class YuYueActivity extends BaseActivity<YuYuePresenter> implements Constraint.YuYueView {

    @BindView(R.id.xximg)
    ImageView xximg;
    @BindView(R.id.yuyuerecycler)
    RecyclerView yuyuerecycler;
    private UserDao mUserDao;
    private String sessionId;
    private int userId;
    private YuYueAdapter yuYueAdapter;

    @Override
    void initData() {

        DaoSession daoSession = DaoMaster.newDevSession(this, UserDao.TABLENAME);
        mUserDao = daoSession.getUserDao();

        List<User> users = mUserDao.loadAll();
        for (int i = 0; i < users.size(); i++) {
            sessionId = users.get(i).getSessionId();
            userId = users.get(i).getUserId();
        }

        presenter.YuYueP(userId, sessionId);

        yuYueAdapter = new YuYueAdapter();
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        yuyuerecycler.setLayoutManager(linearLayoutManager);
        yuyuerecycler.setAdapter(yuYueAdapter);
    }

    @Override
    YuYuePresenter getPresenter() {
        return new YuYuePresenter();
    }

    @Override
    void initListener() {

    }

    @Override
    int initLayout() {
        return R.layout.activity_yu_yue;
    }

    @Override
    public void yuyueSuccess(YuYueBean yuYueBean) {
        if (yuYueBean.status.equals("9999")) {
            Toast.makeText(this, yuYueBean.message, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        } else {
            List<YuYueBean.ResultBean> result = yuYueBean.result;
            yuYueAdapter.onAddAll(result);
            yuYueAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void yuyueError(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.xximg)
    public void onClick() {
        finish();
    }
}
