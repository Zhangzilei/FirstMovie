package com.bw.movie.presenter;

import com.bw.movie.constraint.Constraint;
import com.bw.movie.model.bean.MovieXqBean;
import com.bw.movie.model.bean.PaiQiTimeBean;
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
public class PaiQiTimePresenter extends BasePresenter<Constraint.PaiQiTimeView> {
    public void PaiQiTime() {
        HttpUtils.getInstance().getConstant().FINDDATELIST()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<PaiQiTimeBean>() {
                    @Override
                    public void accept(PaiQiTimeBean paiQiTimeBean) throws Exception {
                        getView().paiqitimeSuccess(paiQiTimeBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getView().paiqitimeError(throwable.getMessage());
                    }
                });
    }
}
