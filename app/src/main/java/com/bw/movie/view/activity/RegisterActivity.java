package com.bw.movie.view.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.constraint.Constraint;
import com.bw.movie.jiami.EncryptUtil;
import com.bw.movie.model.bean.EmailBean;
import com.bw.movie.model.bean.RegisterBean;
import com.bw.movie.presenter.RegisterPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity<RegisterPresenter> implements Constraint.RegisterView {

    @BindView(R.id.edit_nicheng)
    EditText editNicheng;
    @BindView(R.id.edit_email)
    EditText editEmail;
    @BindView(R.id.edit_pwd)
    EditText editPwd;
    @BindView(R.id.img_shan)
    CheckBox imgShan;
    @BindView(R.id.img_yan)
    CheckBox imgYan;
    @BindView(R.id.edit_yanzheng)
    EditText editYanzheng;
    @BindView(R.id.btn_yan)
    Button btnYan;
    @BindView(R.id.text_login)
    TextView textLogin;
    @BindView(R.id.btn_register)
    Button btnRegister;
    private String encrypt;
    private String nicheng;
    private String mail;
    private String pwd;
    private String yanzheng;

    @Override
    void initData() {

    }

    //邮箱验证
    public static boolean isEmail(String strEmail) {
        String strPattern = "^[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$";
        if (TextUtils.isEmpty(strPattern)) {
            return false;
        } else {
            return strEmail.matches(strPattern);
        }
    }

    @Override
    RegisterPresenter getPresenter() {
        return new RegisterPresenter();
    }

    @Override
    void initListener() {

    }

    @Override
    int initLayout() {
        return R.layout.activity_register;
    }

    @Override
    public void registerSuccess(RegisterBean registerBean) {
        Toast.makeText(this, registerBean.message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void registerError(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void emailSuccess(EmailBean emailBean) {
        Toast.makeText(this, emailBean.message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void emailError(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @OnClick({R.id.img_shan, R.id.img_yan, R.id.btn_yan, R.id.text_login, R.id.btn_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_shan:
                editPwd.setText("");
                break;
            case R.id.img_yan:
                if (editPwd.getInputType() == 128) {
                    editPwd.setInputType(129);
                } else {
                    editPwd.setInputType(128);
                }
                break;
            case R.id.btn_yan:
                mail = editEmail.getText().toString();
                if(!TextUtils.isEmpty(mail)){
                    presenter.Email(mail);
                    btnYan.setText("重新获取");
                }else {
                    Toast.makeText(this, "邮箱不能为空", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.text_login:
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
                break;
            case R.id.btn_register:

                nicheng = editNicheng.getText().toString();
                pwd = editPwd.getText().toString();
                yanzheng = editYanzheng.getText().toString();

                //加密
                encrypt = EncryptUtil.encrypt(pwd);

                if (TextUtils.isEmpty(nicheng) && TextUtils.isEmpty(mail) && TextUtils.isEmpty(pwd) && TextUtils.isEmpty(yanzheng)) {
                    Toast.makeText(this, "不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                //邮箱验证
                isEmail(mail);

                presenter.Register(nicheng, encrypt, mail, yanzheng);

                break;
        }
    }
}
