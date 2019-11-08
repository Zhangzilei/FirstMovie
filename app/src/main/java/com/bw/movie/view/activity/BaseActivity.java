package com.bw.movie.view.activity;

import android.support.v7.app.AppCompatActivity;

import com.bw.movie.presenter.BasePresenter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bw.movie.R;
import com.bw.movie.presenter.BasePresenter;
import com.bw.movie.view.interfaces.IBaseView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * <p>文件描述：<p>
 * <p>作者：吴新仲<p>
 * <p>创建时间：2019/11/5/005<p>
 * <p>更改时间：2019/11/5/005<p>
 */

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements IBaseView {
    public P presenter;
    private Unbinder mBind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initLayout());
        mBind = ButterKnife.bind(this);
        initListener();
        presenter = getPresenter();
        presenter.attachView(this);
        initData();
    }

    abstract void initData();

    abstract P getPresenter();

    abstract void initListener();

    abstract int initLayout();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBind.unbind();
        presenter.detachView();
    }
}
