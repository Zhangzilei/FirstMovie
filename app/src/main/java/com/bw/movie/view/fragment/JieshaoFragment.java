package com.bw.movie.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.constraint.Constraint;
import com.bw.movie.model.bean.MovieXqBean;
import com.bw.movie.presenter.MovieXQPresenter;
import com.bw.movie.view.adapter.MovieXqJieshaoDyAapter;
import com.bw.movie.view.adapter.MovieXqJieshaoYyAapter;

/**
 * <p>文件描述：<p>
 * <p>作者：吴新仲<p>
 * <p>创建时间：2019/11/13/013<p>
 * <p>更改时间：2019/11/13/013<p>
 */
public class JieshaoFragment extends BaseFragment<MovieXQPresenter> implements Constraint.IMovieXqView {

    private TextView xq_jianjie;
    private RecyclerView sq_dy_recycle;
    private RecyclerView sq_yy_recycle;
    private int mMovieId;
    private MovieXqJieshaoDyAapter mMovieXqJieshaoDyAapter;
    private MovieXqJieshaoYyAapter mMovieXqJieshaoYyAapter;

    @Override
    void initData() {
        Intent intent = getActivity().getIntent();
        mMovieId = intent.getIntExtra("movieId", 0);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        sq_dy_recycle.setLayoutManager(layoutManager);

        mMovieXqJieshaoDyAapter = new MovieXqJieshaoDyAapter();
        sq_dy_recycle.setAdapter(mMovieXqJieshaoDyAapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 4);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        sq_yy_recycle.setLayoutManager(gridLayoutManager);

        mMovieXqJieshaoYyAapter = new MovieXqJieshaoYyAapter();
        sq_yy_recycle.setAdapter(mMovieXqJieshaoYyAapter);

        presenter.movieXQ(mMovieId);
    }

    @Override
    MovieXQPresenter getPresenter() {
        return new MovieXQPresenter();
    }

    @Override
    void initListener() {

    }

    @Override
    int initLayout() {
        return R.layout.movie_xq_jieshao_fragment_layout;
    }

    @Override
    void initView(View view) {
        xq_jianjie = (TextView) view.findViewById(R.id.xq_jianjie);
        sq_dy_recycle = (RecyclerView) view.findViewById(R.id.sq_dy_recycle);
        sq_yy_recycle = (RecyclerView) view.findViewById(R.id.sq_yy_recycle);
    }

    @Override
    public void movieXQSuccess(MovieXqBean movieXqBean) {
        if (movieXqBean.status.equals("0000")) {
            xq_jianjie.setText(movieXqBean.result.summary);

            mMovieXqJieshaoDyAapter.addAll(movieXqBean.result.movieDirector);
            mMovieXqJieshaoDyAapter.notifyDataSetChanged();

            mMovieXqJieshaoYyAapter.addAll(movieXqBean.result.movieActor);
            mMovieXqJieshaoYyAapter.notifyDataSetChanged();
        }
    }

    @Override
    public void movieXQError(String s) {

    }
}
