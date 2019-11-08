package com.bw.movie.presenter;

import com.bw.movie.constraint.Constraint;
import com.bw.movie.model.bean.XLLoginBean;
import com.bw.movie.model.http.HttpUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * <p>文件描述：登录<p>
 * <p>作者：吴新仲<p>
 * <p>创建时间：2019/11/6/006<p>
 * <p>更改时间：2019/11/6/006<p>
 */
public class LoginPresenter extends BasePresenter<Constraint.ILoginView> {

    public void login(String email, String pwd) {
        HttpUtils.getInstance().getConstant().XL_LOGIN_BEAN(email, pwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<XLLoginBean>() {
                    @Override
                    public void accept(XLLoginBean xlLoginBean) throws Exception {
                        getView().loginSuccess(xlLoginBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getView().loginError(throwable.getMessage());
                    }
                });
    }
}
