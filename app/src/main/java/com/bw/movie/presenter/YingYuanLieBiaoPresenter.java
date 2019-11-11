package com.bw.movie.presenter;

import com.bw.movie.constraint.Constraint;
import com.bw.movie.model.bean.RegisterBean;
import com.bw.movie.model.bean.YingYuanLieBiaoBean;
import com.bw.movie.model.http.HttpUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * date:19/11/8
 * author:张自磊(lenovo)
 * function:
 */
public class YingYuanLieBiaoPresenter extends BasePresenter<Constraint.YingYuanLieBiao> {
    public void YingYuanLie(int cinemaId,int page,int count){
        HttpUtils.getInstance().getConstant().FINDCINEMASCHEDULELIST(cinemaId,page,count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<YingYuanLieBiaoBean>() {
                    @Override
                    public void accept(YingYuanLieBiaoBean registerBean) throws Exception {
                        getView().liebiaoSuccess(registerBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                        getView().liebiaoError(throwable.getMessage());
                    }
                });
    }
}
