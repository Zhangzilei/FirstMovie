package com.bw.movie.presenter;

import com.bw.movie.constraint.Constraint;
import com.bw.movie.model.bean.CHaiLeftBean;
import com.bw.movie.model.bean.CHaiRightBean;
import com.bw.movie.model.bean.CTuiJianBean;
import com.bw.movie.model.http.HttpUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * date:19/11/8
 * author:张自磊(lenovo)
 * function:
 */
public class CHaiPresenter extends BasePresenter<Constraint.CHaiView> {
    public void Right(int rigionid){

        HttpUtils.getInstance().getConstant()
                .FINDCINEMABYREGION(rigionid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CHaiRightBean>() {
                    @Override
                    public void accept(CHaiRightBean rightBean) throws Exception {
                        getView().rightSuccess(rightBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                        getView().rightError(throwable.getMessage());
                    }
                });

    }

    public void Left(){

        HttpUtils.getInstance().getConstant()
                .FINDREGIONLIST()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CHaiLeftBean>() {
                    @Override
                    public void accept(CHaiLeftBean leftBean) throws Exception {
                        getView().leftSuccess(leftBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                        getView().leftError(throwable.getMessage());
                    }
                });

    }

}
