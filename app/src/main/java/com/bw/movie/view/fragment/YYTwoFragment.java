package com.bw.movie.view.fragment;


import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.constraint.Constraint;
import com.bw.movie.dao.DaoMaster;
import com.bw.movie.dao.DaoSession;
import com.bw.movie.dao.UserDao;
import com.bw.movie.model.bean.User;
import com.bw.movie.model.bean.YYGuanZhuBean;
import com.bw.movie.model.bean.YYPingLunBean;
import com.bw.movie.presenter.YYPingLunPresenter;
import com.bw.movie.view.activity.YingYuanXiangQing;
import com.bw.movie.view.adapter.YYTwoFragmentAdapter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

/**
 * date:19/11/9
 * author:张自磊(lenovo)
 * function:
 */
public class YYTwoFragment extends BaseFragment<YYPingLunPresenter> implements Constraint.YYPingJiaView {
    private XRecyclerView twoxrecycler;
    private YYTwoFragmentAdapter twoFragmentAdapter;
    private UserDao mUserDao;
    private String sessionId;
    private int userId;

    @Override
    void initData() {

        DaoSession daoSession = DaoMaster.newDevSession(getActivity(), UserDao.TABLENAME);
        mUserDao = daoSession.getUserDao();

        List<User> users = mUserDao.loadAll();
        for (int i = 0; i < users.size(); i++) {
            sessionId = users.get(i).getSessionId();
            userId = users.get(i).getUserId();
        }

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        twoxrecycler.setLayoutManager(linearLayoutManager);
        twoFragmentAdapter = new YYTwoFragmentAdapter();
        twoxrecycler.setAdapter(twoFragmentAdapter);
        twoFragmentAdapter.notifyDataSetChanged();

        twoFragmentAdapter.setDianZanBack(new YYTwoFragmentAdapter.DianZanBack() {
            @Override
            public void onZanBack(int zanId) {
                if (userId!=0){
                    presenter.YYdianzan(userId,sessionId,zanId);
                }else {
                    Toast.makeText(getContext(), "请先登录哇~", Toast.LENGTH_SHORT).show();
                }

            }
        });

        if(userId!=0){
            presenter.PingJia(userId,sessionId,1,1,5);
        }else {
            presenter.PingJia(0,null,1,1,5);
        }

    }

    @Override
    YYPingLunPresenter getPresenter() {
        return new YYPingLunPresenter();
    }

    @Override
    void initListener() {

    }

    @Override
    int initLayout() {
        return R.layout.yy_two_fragment;
    }

    protected void initView(View inflate) {
        twoxrecycler = (XRecyclerView) inflate.findViewById(R.id.twoxrecycler);
    }

    @Override
    public void pinglunSuccess(YYPingLunBean yyPingLunBean) {
        List<YYPingLunBean.ResultBean> result = yyPingLunBean.result;

        twoFragmentAdapter.onAddAll(result);

        twoFragmentAdapter.notifyDataSetChanged();
    }

    @Override
    public void pinglunError(String s) {
        Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void dianzanSuccess(YYGuanZhuBean guanZhuBean) {
        Toast.makeText(getContext(), guanZhuBean.message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void dianzanError(String s) {
        Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
    }
}
