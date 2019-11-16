package com.bw.movie.presenter;

import com.bw.movie.constraint.Constraint;
import com.bw.movie.model.bean.GuanZhuYingYuanBean;
import com.bw.movie.model.bean.ZiLiaoBean;
import com.bw.movie.model.http.HttpUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * <p>文件描述：<p>
 * <p>作者：张自磊<p>
 * <p>创建时间：2019/11/15<p>
 * <p>更改时间：2019/11/15<p>
 */
public class GuanZhuYingYuanPresenter extends BasePresenter<Constraint.GuanZhuYingYuanView> {
    public void GuanZhuYingYuan(int userId,String sessionId,int page,int count){

        HttpUtils.getInstance().getConstant()
                .GUANZHUYINGYUAN(userId,sessionId,page,count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<GuanZhuYingYuanBean>() {
                    @Override
                    public void accept(GuanZhuYingYuanBean tuiJianBean) throws Exception {
                        getView().gzyySuccess(tuiJianBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                        getView().gzyyError(throwable.getMessage());
                    }
                });

    }
}
