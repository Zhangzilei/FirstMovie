package com.bw.movie.presenter;

import com.bw.movie.constraint.Constraint;
import com.bw.movie.model.bean.HomeBanner;
import com.bw.movie.model.bean.MovieYuYueBean;
import com.bw.movie.model.bean.PopularMovieBean;
import com.bw.movie.model.bean.ReYingBean;
import com.bw.movie.model.bean.SearchBean;
import com.bw.movie.model.bean.ShangYingBean;
import com.bw.movie.model.http.HttpUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.http.Field;
import retrofit2.http.Header;

/**
 * <p>文件描述：<p>
 * <p>作者：吴新仲<p>
 * <p>创建时间：2019/11/7/007<p>
 * <p>更改时间：2019/11/7/007<p>
 */
public class HomePageViewPresenter extends BasePresenter<Constraint.IHomeMovie> {

    //banner
    public void homeBanner() {
        HttpUtils.getInstance().getConstant().HOME_BANNER()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<HomeBanner>() {
                    @Override
                    public void accept(HomeBanner homeBanner) throws Exception {
                        getView().bannerSuccess(homeBanner);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getView().bannerError(throwable.getMessage());
                    }
                });
    }

    private int page;

    //正在热映
    public void reYing(boolean isRefresh) {
        if (isRefresh)
            page = 1;
        else
            page++;

        HttpUtils.getInstance().getConstant().RE_YING_BEAN(page, 7)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ReYingBean>() {
                    @Override
                    public void accept(ReYingBean reYingBean) throws Exception {
                        getView().reyingSuccess(reYingBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getView().reyingError(throwable.getMessage());
                    }
                });
    }

    //即将上映
    public void shangYing(boolean isRefresh,String sessionId, int userId) {
        if (isRefresh)
            page = 1;
        else
            page++;

        HttpUtils.getInstance().getConstant().SHANG_YING_BEAN(sessionId,userId,page, 7)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ShangYingBean>() {
                    @Override
                    public void accept(ShangYingBean shangYingBean) throws Exception {
                        getView().shangyingSuccess(shangYingBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getView().shangyingError(throwable.getMessage());
                    }
                });
    }

    //预约
    public void yuYue(String sessionId, int userId, int movieId){
        HttpUtils.getInstance().getConstant().MOVIE_YU_YUE_BEAN(sessionId, userId, movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MovieYuYueBean>() {
                    @Override
                    public void accept(MovieYuYueBean movieYuYueBean) throws Exception {
                        getView().yuyueSuccess(movieYuYueBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getView().yuyueError(throwable.getMessage());
                    }
                });
    }


    //热门电影
    public void popular(boolean isRefresh) {
        if (isRefresh)
            page = 1;
        else
            page++;

        HttpUtils.getInstance().getConstant().POPULAR_MOVIE_BEAN(page, 7)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<PopularMovieBean>() {
                    @Override
                    public void accept(PopularMovieBean popularMovieBean) throws Exception {
                        getView().popularSuccess(popularMovieBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getView().popularError(throwable.getMessage());
                    }
                });
    }



}
