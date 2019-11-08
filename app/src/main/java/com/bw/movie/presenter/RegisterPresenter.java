package com.bw.movie.presenter;

import com.bw.movie.constraint.Constraint;
import com.bw.movie.model.bean.EmailBean;
import com.bw.movie.model.bean.RegisterBean;
import com.bw.movie.model.http.HttpUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * date:19/11/6
 * author:张自磊(lenovo)
 * function:
 */
public class RegisterPresenter extends BasePresenter<Constraint.RegisterView> {
    public void Register(String nikeName,String pwd,String email,String code){
        HttpUtils.getInstance().getConstant().REGISTER(nikeName,pwd,email,code)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<RegisterBean>() {
                    @Override
                    public void accept(RegisterBean registerBean) throws Exception {
                        getView().registerSuccess(registerBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                        getView().registerError(throwable.getMessage());
                    }
                });
    }

    public void Email(final String email){
        HttpUtils.getInstance().getConstant().SENDOUTEMAILCODE(email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<EmailBean>() {
                    @Override
                    public void accept(EmailBean emailBean) throws Exception {
                        getView().emailSuccess(emailBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                        getView().emailError(throwable.getMessage());
                    }
                });
    }

}
