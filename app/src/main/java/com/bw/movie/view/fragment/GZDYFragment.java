package com.bw.movie.view.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.constraint.Constraint;
import com.bw.movie.dao.DaoMaster;
import com.bw.movie.dao.DaoSession;
import com.bw.movie.dao.UserDao;
import com.bw.movie.model.bean.GuanZhuDianYingBean;
import com.bw.movie.model.bean.GuanZhuYingYuanBean;
import com.bw.movie.model.bean.User;
import com.bw.movie.presenter.GuanZhuDianYingPresenter;
import com.bw.movie.presenter.GuanZhuYingYuanPresenter;
import com.bw.movie.view.activity.LoginActivity;
import com.bw.movie.view.adapter.GZDYAdapter;

import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：张自磊<p>
 * <p>创建时间：2019/11/15<p>
 * <p>更改时间：2019/11/15<p>
 */
public class GZDYFragment extends BaseFragment<GuanZhuDianYingPresenter> implements Constraint.GuanZhuDianYingView {

    private RecyclerView gzdyre;
    private GZDYAdapter gzdyAdapter;
    private UserDao mUserDao;
    private String sessionId;
    private int userId;

    @Override
    void initData() {
        DaoSession daoSession = DaoMaster.newDevSession(getContext(), UserDao.TABLENAME);
        mUserDao = daoSession.getUserDao();

        List<User> users = mUserDao.loadAll();
        for (int i = 0; i < users.size(); i++) {
            sessionId = users.get(i).getSessionId();
            userId = users.get(i).getUserId();
        }

        presenter.GuanZhuDianYingn(userId,sessionId,1,10);

        gzdyAdapter = new GZDYAdapter();
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        gzdyre.setLayoutManager(linearLayoutManager);
        gzdyre.setAdapter(gzdyAdapter);
        gzdyAdapter.notifyDataSetChanged();
    }

    @Override
    GuanZhuDianYingPresenter getPresenter() {
        return new GuanZhuDianYingPresenter();
    }

    @Override
    void initListener() {

    }

    @Override
    int initLayout() {
        return R.layout.gzdy_fragment;
    }

    @Override
    void initView(View view) {
        gzdyre = view.findViewById(R.id.gzdyrecycler);
    }

    @Override
    public void gzdySuccess(GuanZhuDianYingBean gzdyBean) {
        if(gzdyBean.status.equals("9999")){
            Toast.makeText(getContext(), "请先登录", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
        }else {
            gzdyAdapter.GXYYaddAll(gzdyBean.result);

            gzdyAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void gzdyError(String s) {
        Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
    }
}
