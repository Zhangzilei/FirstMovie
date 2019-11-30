package com.bw.movie.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.constraint.Constraint;
import com.bw.movie.dao.DaoMaster;
import com.bw.movie.dao.DaoSession;
import com.bw.movie.dao.UserDao;
import com.bw.movie.model.bean.User;
import com.bw.movie.model.bean.YYGuanZhuBean;
import com.bw.movie.presenter.FanKuiPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FanKuiActivity extends BaseActivity<FanKuiPresenter> implements Constraint.FanKuiView {

    @BindView(R.id.xximg)
    ImageView xximg;
    @BindView(R.id.editfankui)
    EditText editfankui;
    @BindView(R.id.texttijiao)
    TextView texttijiao;
    private UserDao mUserDao;
    private String sessionId;
    private int userId;

    @Override
    void initData() {
        DaoSession daoSession = DaoMaster.newDevSession(this, UserDao.TABLENAME);
        mUserDao = daoSession.getUserDao();

        List<User> users = mUserDao.loadAll();
        for (int i = 0; i < users.size(); i++) {
            sessionId = users.get(i).getSessionId();
            userId = users.get(i).getUserId();
        }

    }

    @Override
    FanKuiPresenter getPresenter() {
        return new FanKuiPresenter();
    }

    @Override
    void initListener() {

    }

    @Override
    int initLayout() {
        return R.layout.activity_fan_kui;
    }

    @Override
    public void fankuiSuccess(YYGuanZhuBean fankui) {
        if (fankui.status.equals("0000")) {
            finish();
            Intent intent = new Intent(this, TiJiaoChengGong.class);
            startActivity(intent);
        } else if (fankui.status.equals("9999")) {
            Toast.makeText(this, fankui.message, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void fankuiError(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @OnClick({R.id.xximg, R.id.texttijiao})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.xximg:
                finish();
                break;
            case R.id.texttijiao:
                String s = editfankui.getText().toString();
                if(TextUtils.isEmpty(s)){
                    return;
                }else {
                    presenter.FanKuiP(userId, sessionId, s);
                }
                break;
        }
    }
}
