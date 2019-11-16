package com.bw.movie.presenter;

import com.bw.movie.constraint.Constraint;
import com.bw.movie.model.bean.GuanZhuDianYingBean;
import com.bw.movie.model.bean.GuanZhuYingYuanBean;
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
public class GuanZhuDianYingPresenter extends BasePresenter<Constraint.GuanZhuDianYingView> {
    public void GuanZhuDianYingn(int userId,String sessionId,int page,int count){

        HttpUtils.getInstance().getConstant()
                .GUANZHUDIANYING(userId,sessionId,page,count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<GuanZhuDianYingBean>() {
                    @Override
                    public void accept(GuanZhuDianYingBean tuiJianBean) throws Exception {
                        getView().gzdySuccess(tuiJianBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                        getView().gzdyError(throwable.getMessage());
                    }
                });

    }
}
