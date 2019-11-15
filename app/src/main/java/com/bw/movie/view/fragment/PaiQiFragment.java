package com.bw.movie.view.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bw.movie.R;
import com.bw.movie.constraint.Constraint;
import com.bw.movie.model.bean.PaiQiBean;
import com.bw.movie.presenter.PaiQiPresenter;
import com.bw.movie.view.adapter.PaiQiAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * <p>文件描述：<p>
 * <p>作者：张自磊<p>
 * <p>创建时间：2019/11/14<p>
 * <p>更改时间：2019/11/14<p>
 */

@SuppressLint("ValidFragment")
public class PaiQiFragment extends BaseFragment<PaiQiPresenter> implements Constraint.PaiQiView {

    private RecyclerView pqrecycler;
    private int status;
    private PaiQiAdapter paiQiAdapter;

    @SuppressLint("ValidFragment")
    public PaiQiFragment(int i) {
        this.status = i;
    }

    @Override
    public void paiqiSuccess(PaiQiBean paiQiBean) {
        List<PaiQiBean.ResultBean> result = paiQiBean.result;
        paiQiAdapter.PaiQiAddAll(result);
        paiQiAdapter.notifyDataSetChanged();
    }

    @Override
    public void paiqiError(String s) {

    }

    @Override
    void initData() {

        presenter.PaiQi(status,1,10);

        paiQiAdapter = new PaiQiAdapter();
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        pqrecycler.setLayoutManager(linearLayoutManager);
        pqrecycler.setAdapter(paiQiAdapter);
        paiQiAdapter.notifyDataSetChanged();
    }

    @Override
    PaiQiPresenter getPresenter() {
        return new PaiQiPresenter();
    }

    @Override
    void initListener() {

    }

    @Override
    int initLayout() {
        return R.layout.paiqi_fragment;
    }

    @Override
    void initView(View view) {
        pqrecycler=view.findViewById(R.id.pqrecycler);
    }
}
