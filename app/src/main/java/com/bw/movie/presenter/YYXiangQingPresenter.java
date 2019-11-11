package com.bw.movie.presenter;

import com.bw.movie.constraint.Constraint;
import com.bw.movie.model.bean.YYGuanZhuBean;
import com.bw.movie.model.bean.YYXiangQingBean;
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
public class YYXiangQingPresenter extends BasePresenter<Constraint.YYXiangQingView> {
    //影院详情
    public void YYXiangQinga(int cinemaId){
        HttpUtils.getInstance().getConstant().FINDCINEMAINFO(cinemaId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<YYXiangQingBean>() {
                    @Override
                    public void accept(YYXiangQingBean xiangQingBean) throws Exception {
                        getView().xiangqingSuccess(xiangQingBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                        getView().xiangqingError(throwable.getMessage());
                    }
                });
    }

    //影院关注
    public void YYguanzhu(int userId,String sessionId,int cinemaId){
        HttpUtils.getInstance().getConstant().FOLLOWCINEMA(userId,sessionId,cinemaId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<YYGuanZhuBean>() {
                    @Override
                    public void accept(YYGuanZhuBean guanZhuBean) throws Exception {
                        getView().guanzhuSuccess(guanZhuBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                        getView().guanzhuError(throwable.getMessage());
                    }
                });
    }

    //影院取关
    public void YYquguan(int userId,String sessionId,int cinemaId){
        HttpUtils.getInstance().getConstant().CANCELFOLLOWCINEMA(userId,sessionId,cinemaId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<YYGuanZhuBean>() {
                    @Override
                    public void accept(YYGuanZhuBean guanZhuBean) throws Exception {
                        getView().quguanSuccess(guanZhuBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                        getView().quguanError(throwable.getMessage());
                    }
                });
    }
}
