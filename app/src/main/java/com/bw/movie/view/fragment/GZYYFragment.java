package com.bw.movie.view.fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.constraint.Constraint;
import com.bw.movie.dao.DaoMaster;
import com.bw.movie.dao.DaoSession;
import com.bw.movie.dao.UserDao;
import com.bw.movie.model.bean.GuanZhuYingYuanBean;
import com.bw.movie.model.bean.User;
import com.bw.movie.presenter.GuanZhuYingYuanPresenter;
import com.bw.movie.view.activity.LoginActivity;
import com.bw.movie.view.adapter.GZDYAdapter;
import com.bw.movie.view.adapter.GZYYAdapter;

import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：张自磊<p>
 * <p>创建时间：2019/11/15<p>
 * <p>更改时间：2019/11/15<p>
 */
public class GZYYFragment extends BaseFragment<GuanZhuYingYuanPresenter> implements Constraint.GuanZhuYingYuanView {

    private RecyclerView gzyyre;
    private GZYYAdapter gzyyAdapter;
    private UserDao mUserDao;
    private String sessionId;
    private int userId;

    @Override
    public void gzyySuccess(GuanZhuYingYuanBean gzyybean) {
        if(gzyybean.status.equals("9999")){
            Toast.makeText(getContext(), "请先登录", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
        }else {
            gzyyAdapter.GXYYaddAll(gzyybean.result);
            gzyyAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void gzyyError(String s) {

    }

    @Override
    void initData() {
        DaoSession daoSession = DaoMaster.newDevSession(getContext(), UserDao.TABLENAME);
        mUserDao = daoSession.getUserDao();

        List<User> users = mUserDao.loadAll();
        for (int i = 0; i < users.size(); i++) {
            sessionId = users.get(i).getSessionId();
            userId = users.get(i).getUserId();
        }

        presenter.GuanZhuYingYuan(userId, sessionId,1,10);

        gzyyAdapter = new GZYYAdapter();
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        gzyyre.setLayoutManager(linearLayoutManager);
        gzyyre.setAdapter(gzyyAdapter);
        gzyyAdapter.notifyDataSetChanged();
    }

    @Override
    GuanZhuYingYuanPresenter getPresenter() {
        return new GuanZhuYingYuanPresenter();
    }

    @Override
    void initListener() {

    }

    @Override
    int initLayout() {
        return R.layout.gzyy_fragment;
    }

    @Override
    void initView(View view) {
        gzyyre = view.findViewById(R.id.gzyyrecycler);
    }
}
