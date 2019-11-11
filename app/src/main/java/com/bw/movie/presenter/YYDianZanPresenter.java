package com.bw.movie.presenter;

import com.bw.movie.constraint.Constraint;
import com.bw.movie.model.bean.YYGuanZhuBean;
import com.bw.movie.model.http.HttpUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * date:19/11/9
 * author:张自磊(lenovo)
 * function:
 */
public class YYDianZanPresenter extends BasePresenter<Constraint.YYDianZanView> {
    public void YYdianzan(int userId,String sessionId,int commentId){
        HttpUtils.getInstance().getConstant().CINEMACOMMENTGREAT(userId,sessionId,commentId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<YYGuanZhuBean>() {
                    @Override
                    public void accept(YYGuanZhuBean guanZhuBean) throws Exception {
                        getView().dianzanSuccess(guanZhuBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                        getView().dianzanError(throwable.getMessage());
                    }
                });
    }
}
