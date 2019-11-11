package com.bw.movie.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bw.movie.R;
import com.bw.movie.constraint.Constraint;
import com.bw.movie.model.bean.YingYuanLieBiaoBean;
import com.bw.movie.presenter.YingYuanLieBiaoPresenter;

public class LieBiaoActivity extends BaseActivity<YingYuanLieBiaoPresenter> implements Constraint.YingYuanLieBiao {

    @Override
    void initData() {
        
    }

    @Override
    YingYuanLieBiaoPresenter getPresenter() {
        return new YingYuanLieBiaoPresenter();
    }

    @Override
    void initListener() {

    }

    @Override
    int initLayout() {
        return R.layout.activity_lie_biao;
    }

    @Override
    public void liebiaoSuccess(YingYuanLieBiaoBean lieBiao) {

    }

    @Override
    public void liebiaoError(String s) {

    }
}
