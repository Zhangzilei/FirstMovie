package com.bw.movie.view.activity;

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
import com.bw.movie.model.bean.XiaoXiBean;
import com.bw.movie.presenter.XiaoXiPresenter;
import com.bw.movie.view.adapter.XiaoXiAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyXinxiActivity extends BaseActivity<XiaoXiPresenter> implements Constraint.XiaoXiView {

    @BindView(R.id.xxrecycler)
    RecyclerView xxrecycler;
    @BindView(R.id.xximg)
    ImageView xximg;
    private UserDao mUserDao;
    private String sessionId;
    private int userId;
    private XiaoXiAdapter xiaoXiAdapter;

    @Override
    void initData() {
        DaoSession daoSession = DaoMaster.newDevSession(this, UserDao.TABLENAME);
        mUserDao = daoSession.getUserDao();

        List<User> users = mUserDao.loadAll();
        for (int i = 0; i < users.size(); i++) {
            sessionId = users.get(i).getSessionId();
            userId = users.get(i).getUserId();
        }

        presenter.XiaoXi(userId, sessionId, 1, 5);

        xiaoXiAdapter = new XiaoXiAdapter();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MyXinxiActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xxrecycler.setLayoutManager(linearLayoutManager);
        xxrecycler.setAdapter(xiaoXiAdapter);
        xiaoXiAdapter.notifyDataSetChanged();
    }

    @Override
    XiaoXiPresenter getPresenter() {
        return new XiaoXiPresenter();
    }

    @Override
    void initListener() {

    }

    @Override
    int initLayout() {
        return R.layout.activity_my_xinxi;
    }

    @Override
    public void xiaoxiSuccess(XiaoXiBean xiaoXiBean) {
        xiaoXiAdapter.XXAddAll(xiaoXiBean.result);

        xiaoXiAdapter.notifyDataSetChanged();
    }

    @Override
    public void xiaoxiError(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.xximg)
    public void onViewClicked() {
        finish();
    }
}
