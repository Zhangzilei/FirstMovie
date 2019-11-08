package com.bw.movie.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.constraint.Constraint;
import com.bw.movie.model.bean.CTuiJianBean;
import com.bw.movie.presenter.BasePresenter;
import com.bw.movie.presenter.CTuiJianPresenter;
import com.bw.movie.view.adapter.CTuiJianAdapter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

/**
 * date:19/11/7
 * author:张自磊(lenovo)
 * function:
 */
public class CTuiJianFragment extends BaseFragment<CTuiJianPresenter> implements Constraint.TuiJianView {

    private XRecyclerView crecycler;
    private CTuiJianAdapter tuiJianAdapter;
    int page=1;
    int userId=0;
    String sessionId=null;

    @Override
    void initData() {

        presenter.TuiJian(userId,sessionId,page,10);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        crecycler.setLayoutManager(linearLayoutManager);
        tuiJianAdapter = new CTuiJianAdapter();
        crecycler.setAdapter(tuiJianAdapter);

        crecycler.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page=1;
                presenter.TuiJian(userId,sessionId,page,10);
                crecycler.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                page++;
                presenter.TuiJian(userId,sessionId,page,10);
                crecycler.loadMoreComplete();
            }
        });
    }

    @Override
    CTuiJianPresenter getPresenter() {
        return new CTuiJianPresenter();
    }

    @Override
    void initListener() {

    }

    @Override
    int initLayout() {
        return R.layout.ctuijian_fragment;
    }

    @Override
    void initView(View view) {
        crecycler = view.findViewById(R.id.crecycler);
    }

    @Override
    public void tuijianSuccess(CTuiJianBean cTuiJianBean) {
        List<CTuiJianBean.ResultBean> tuiJians = cTuiJianBean.result;
        tuiJianAdapter.onAddAll(tuiJians);

        tuiJianAdapter.notifyDataSetChanged();
    }

    @Override
    public void tuijianError(String s) {
        Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
    }
}
