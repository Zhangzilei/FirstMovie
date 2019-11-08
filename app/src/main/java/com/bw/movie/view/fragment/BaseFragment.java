package com.bw.movie.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.presenter.BasePresenter;
import com.bw.movie.view.interfaces.IBaseView;

/**
 * <p>文件描述：<p>
 * <p>作者：吴新仲<p>
 * <p>创建时间：2019/11/6/006<p>
 * <p>更改时间：2019/11/6/006<p>
 */
public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements IBaseView {
    public P presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(container.getContext()).inflate(initLayout(), container, false);
        initView(view);
        initListener();
        presenter = getPresenter();
        presenter.attachView(this);
        initData();
        return view;
    }

    abstract void initData();

    abstract P getPresenter();

    abstract void initListener();

    abstract int initLayout();

    abstract void initView(View view);

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}
