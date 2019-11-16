package com.bw.movie.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.constraint.Constraint;
import com.bw.movie.model.bean.MovieXqBean;
import com.bw.movie.presenter.MovieXQPresenter;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * <p>文件描述：<p>
 * <p>作者：吴新仲<p>
 * <p>创建时间：2019/11/13/013<p>
 * <p>更改时间：2019/11/13/013<p>
 */
public class YugaoFragment extends BaseFragment<MovieXQPresenter> implements Constraint.IMovieXqView {

    private JCVideoPlayerStandard videoplayer;
    private int mMovieId;

    @Override
    void initData() {

        Intent intent = getActivity().getIntent();
        mMovieId = intent.getIntExtra("movieId", 0);

        presenter.movieXQ(mMovieId);
    }


    @Override
    public void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }


    @Override
    MovieXQPresenter getPresenter() {
        return new MovieXQPresenter();
    }

    @Override
    void initListener() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            videoplayer.releaseAllVideos();
        } catch (Exception e) {
        }
    }

    @Override
    int initLayout() {
        return R.layout.movie_xq_yugao_fragment_layout;
    }

    void initView(View view) {
        videoplayer = (JCVideoPlayerStandard) view.findViewById(R.id.videoplayer);
    }

    @Override
    public void movieXQSuccess(MovieXqBean movieXqBean) {

        videoplayer.TOOL_BAR_EXIST = false;

        for (int i = 0; i < movieXqBean.result.shortFilmList.size(); i++) {
            videoplayer.setUp(movieXqBean.result.shortFilmList.get(i).videoUrl, JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, movieXqBean.result.name);

            Glide.with(getContext()).load(movieXqBean.result.shortFilmList.get(i).imageUrl).into(videoplayer.thumbImageView);
        }

        videoplayer.widthRatio = 4;//播放比例
        videoplayer.heightRatio = 3;


    }


    @Override
    public void movieXQError(String s) {

    }
}
