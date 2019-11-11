package com.bw.movie.presenter;

import com.bw.movie.constraint.Constraint;
import com.bw.movie.model.bean.YYPingLunBean;
import com.bw.movie.model.bean.YingYuanLieBiaoBean;
import com.bw.movie.model.http.HttpUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * date:19/11/9
 * author:张自磊(lenovo)
 * function:
 */
public class YYPingLunPresenter extends BasePresenter<Constraint.YYPingJiaView> {
    public void PingJia(int userId,String sessionId,int cinemaId,int page,int count){
        HttpUtils.getInstance().getConstant().FINDALLCINEMACOMMENT(userId,sessionId,cinemaId,page,count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<YYPingLunBean>() {
                    @Override
                    public void accept(YYPingLunBean registerBean) throws Exception {
                        getView().pinglunSuccess(registerBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                        getView().pinglunError(throwable.getMessage());
                    }
                });
    }
}
