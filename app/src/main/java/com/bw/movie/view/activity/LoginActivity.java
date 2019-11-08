package com.bw.movie.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.constraint.Constraint;
import com.bw.movie.jiami.EncryptUtil;
import com.bw.movie.model.bean.XLLoginBean;
import com.bw.movie.presenter.LoginPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<LoginPresenter> implements Constraint.ILoginView {

    @BindView(R.id.login_img_back)
    ImageView loginImgBack;
    @BindView(R.id.login_edit_user)
    EditText loginEditUser;
    @BindView(R.id.login_edit_pwd)
    EditText loginEditPwd;
    @BindView(R.id.login_btn_wang)
    Button loginBtnWang;
    @BindView(R.id.login_zhu)
    TextView loginZhu;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_wei)
    ImageView btnWei;
    private String mEncrypt;
    private String mEmail;


    //邮箱验证
    public static boolean isEmail(String strEmail) {
        String strPattern = "^[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$";
        if (TextUtils.isEmpty(strPattern)) {
            return false;
        } else {
            return strEmail.matches(strPattern);
        }
    }


    @OnClick({R.id.login_img_back, R.id.login_btn_wang, R.id.login_zhu, R.id.btn_login, R.id.btn_wei})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_img_back:
                System.exit(0);
                break;
            case R.id.login_btn_wang:
                break;
            case R.id.login_zhu:
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                break;
            case R.id.btn_login:
                if (submit()) {
                    isEmail(mEmail);
                    presenter.login(mEmail, mEncrypt);
                }
                break;
            case R.id.btn_wei:
                break;
        }
    }

    @Override
    public void loginSuccess(XLLoginBean xlLoginBean) {
        if (xlLoginBean.status.equals("0000")) {
//            Toast.makeText(this, xlLoginBean.message, Toast.LENGTH_SHORT).show();
//            startActivity(new Intent(this,MainActivity.class));

        } else {
            Toast.makeText(this, xlLoginBean.message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void loginError(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    void initData() {


    }

    @Override
    LoginPresenter getPresenter() {
        return new LoginPresenter();
    }

    @Override
    void initListener() {

    }

    @Override
    int initLayout() {
        return R.layout.activity_login;
    }

    private boolean submit() {
        // validate
        mEmail = loginEditUser.getText().toString().trim();
        if (TextUtils.isEmpty(mEmail)) {
            Toast.makeText(this, "请输入邮箱", Toast.LENGTH_SHORT).show();
            return false;
        }

        String pwd = loginEditPwd.getText().toString().trim();
        mEncrypt = EncryptUtil.encrypt(pwd);
        if (TextUtils.isEmpty(mEncrypt)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
