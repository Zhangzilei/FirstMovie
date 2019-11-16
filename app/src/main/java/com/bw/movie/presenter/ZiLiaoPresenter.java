package com.bw.movie.presenter;

import com.bw.movie.constraint.Constraint;
import com.bw.movie.model.bean.CTuiJianBean;
import com.bw.movie.model.bean.YYGuanZhuBean;
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
public class ZiLiaoPresenter extends BasePresenter<Constraint.ZiLiaoView> {
    public void ZiLiao(int userId,String sessionId){

        HttpUtils.getInstance().getConstant()
                .ZILIAOOBSERVALE(userId,sessionId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ZiLiaoBean>() {
                    @Override
                    public void accept(ZiLiaoBean tuiJianBean) throws Exception {
                        getView().ziliaoSuccess(tuiJianBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                        getView().ziliaoError(throwable.getMessage());
                    }
                });

    }


    public void ShengRi(int userId,String sessionId,String shengri){

        HttpUtils.getInstance().getConstant()
                .XIUGAISHENGRI(userId,sessionId,shengri)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<YYGuanZhuBean>() {
                    @Override
                    public void accept(YYGuanZhuBean tuiJianBean) throws Exception {
                        getView().shengqiSuccess(tuiJianBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                        getView().shengqiError(throwable.getMessage());
                    }
                });

    }
}
