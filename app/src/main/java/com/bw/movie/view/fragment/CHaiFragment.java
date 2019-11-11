package com.bw.movie.view.fragment;

import android.content.Intent;
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
import com.bw.movie.model.bean.CHaiLeftBean;
import com.bw.movie.model.bean.CHaiRightBean;
import com.bw.movie.presenter.BasePresenter;
import com.bw.movie.presenter.CHaiPresenter;
import com.bw.movie.view.activity.YingYuanXiangQing;
import com.bw.movie.view.adapter.CTuiJianAdapter;
import com.bw.movie.view.adapter.LeftAdapter;
import com.bw.movie.view.adapter.RightAdapter;

import java.util.List;

/**
 * date:19/11/7
 * author:张自磊(lenovo)
 * function:
 */
public class CHaiFragment extends BaseFragment<CHaiPresenter> implements Constraint.CHaiView {

    private RecyclerView leftre,rightre;
    private LeftAdapter leftAdapter;
    private RightAdapter rightAdapter;

    @Override
    public void leftSuccess(CHaiLeftBean leftBean) {
        List<CHaiLeftBean.ResultBean> result = leftBean.result;

        leftAdapter.onAddAll(result);

        leftAdapter.notifyDataSetChanged();
    }

    @Override
    public void leftError(String s) {
        Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void rightSuccess(CHaiRightBean rightBean) {
        List<CHaiRightBean.ResultBean> result = rightBean.result;
        rightAdapter.onAddAll(result);

        rightAdapter.notifyDataSetChanged();
    }

    @Override
    public void rightError(String s) {
        Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
    }

    @Override
    void initData() {
        presenter.Left();

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        leftre.setLayoutManager(linearLayoutManager);
        leftAdapter = new LeftAdapter();
        leftre.setAdapter(leftAdapter);

        leftAdapter.setLeftBack(new LeftAdapter.LeftBack() {

            @Override
            public void onLeft(int isId) {
                Toast.makeText(getContext(), ""+isId, Toast.LENGTH_SHORT).show();

                presenter.Right(isId);
            }
        });

        LinearLayoutManager linearLayoutManager1=new LinearLayoutManager(getContext());
        linearLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
        rightre.setLayoutManager(linearLayoutManager1);
        rightAdapter = new RightAdapter();
        rightre.setAdapter(rightAdapter);

        rightAdapter.setRightBack(new RightAdapter.RightBack() {
            @Override
            public void onRightId(int rightid) {
                Intent intent=new Intent(getActivity(), YingYuanXiangQing.class);
                intent.putExtra("id",rightid+"");
                getActivity().startActivity(intent);
            }
        });
    }

    @Override
    CHaiPresenter getPresenter() {
        return new CHaiPresenter();
    }

    @Override
    void initListener() {

    }

    @Override
    int initLayout() {
        return R.layout.chai_fragment;
    }

    @Override
    void initView(View view) {
        leftre = view.findViewById(R.id.leftre);
        rightre = view.findViewById(R.id.rightre);
    }
}
