package com.bw.movie.presenter;

import com.bw.movie.constraint.Constraint;
import com.bw.movie.model.bean.MovieXqYingpingBean;
import com.bw.movie.model.http.HttpUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * <p>文件描述：<p>
 * <p>作者：吴新仲<p>
 * <p>创建时间：2019/11/15/015<p>
 * <p>更改时间：2019/11/15/015<p>
 */
public class MovieXqYpPresenter extends BasePresenter<Constraint.IMovieXqYpView> {

    private int page;

    public void yp(int movieId,boolean isRefresh) {

        if (isRefresh)
            page=1;
        else
            page++;

        HttpUtils.getInstance().getConstant().MOVIE_XQ_YINGPING_BEAN(movieId,page,7)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MovieXqYingpingBean>() {
                    @Override
                    public void accept(MovieXqYingpingBean movieXqYingpingBean) throws Exception {
                        getView().movieXqYpSuccess(movieXqYingpingBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getView().movieXqYpError(throwable.getMessage());
                    }
                });
    }

    public int getPage() {
        return page;
    }
}
