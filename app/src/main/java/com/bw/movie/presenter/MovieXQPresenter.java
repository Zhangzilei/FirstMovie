package com.bw.movie.presenter;

import com.bw.movie.constraint.Constraint;
import com.bw.movie.model.bean.MovieXqBean;
import com.bw.movie.model.http.HttpUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * <p>文件描述：<p>
 * <p>作者：吴新仲<p>
 * <p>创建时间：2019/11/13/013<p>
 * <p>更改时间：2019/11/13/013<p>
 */
public class MovieXQPresenter extends BasePresenter<Constraint.IMovieXqView> {

    public void movieXQ(int movieId) {
        HttpUtils.getInstance().getConstant().MOVIE_XQ_BEAN(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MovieXqBean>() {
                    @Override
                    public void accept(MovieXqBean movieXqBean) throws Exception {
                        getView().movieXQSuccess(movieXqBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getView().movieXQError(throwable.getMessage());
                    }
                });
    }

}
