package com.bw.movie.presenter;

import com.bw.movie.constraint.Constraint;
import com.bw.movie.model.bean.RegisterBean;
import com.bw.movie.model.bean.XiaoXiBean;
import com.bw.movie.model.http.HttpUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * <p>文件描述：<p>
 * <p>作者：张自磊<p>
 * <p>创建时间：2019/11/13<p>
 * <p>更改时间：2019/11/13<p>
 */
public class XiaoXiPresenter extends BasePresenter<Constraint.XiaoXiView> {
    public void XiaoXi (int userId,String sessionId,int page,int count){
        HttpUtils.getInstance().getConstant().FINDALLSYSMSGLIST(userId,sessionId,page,count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<XiaoXiBean>() {
                    @Override
                    public void accept(XiaoXiBean xiaoXiBean) throws Exception {
                        getView().xiaoxiSuccess(xiaoXiBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                        getView().xiaoxiError(throwable.getMessage());
                    }
                });
    }
}
