package com.bw.movie.presenter;

import com.bw.movie.constraint.Constraint;
import com.bw.movie.model.bean.MovieXqBean;
import com.bw.movie.model.bean.PaiQiBean;
import com.bw.movie.model.http.HttpUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * <p>文件描述：<p>
 * <p>作者：张自磊<p>
 * <p>创建时间：2019/11/14<p>
 * <p>更改时间：2019/11/14<p>
 */
public class PaiQiPresenter extends BasePresenter<Constraint.PaiQiView> {
    public void PaiQi(int movieId,int page,int count) {
        HttpUtils.getInstance().getConstant().PAIQI(movieId,page,count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<PaiQiBean>() {
                    @Override
                    public void accept(PaiQiBean paiQiBean) throws Exception {
                        getView().paiqiSuccess(paiQiBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getView().paiqiError(throwable.getMessage());
                    }
                });
    }
}
