package com.bw.movie.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.R;
import com.bw.movie.constraint.Constraint;
import com.bw.movie.model.bean.MovieXqBean;
import com.bw.movie.presenter.MovieXQPresenter;
import com.bw.movie.view.adapter.MovieXqJuzhaoAdapter;

import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：吴新仲<p>
 * <p>创建时间：2019/11/13/013<p>
 * <p>更改时间：2019/11/13/013<p>
 */
public class JuzhaoFragment extends BaseFragment<MovieXQPresenter> implements Constraint.IMovieXqView {

    private RecyclerView movie_xq_juzhao;
    private int mMovieId;
    private MovieXqJuzhaoAdapter mMovieXqJuzhaoAdapter;

    @Override
    void initData() {

        Intent intent = getActivity().getIntent();
        mMovieId = intent.getIntExtra("movieId", 0);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        movie_xq_juzhao.setLayoutManager(gridLayoutManager);

        mMovieXqJuzhaoAdapter = new MovieXqJuzhaoAdapter();
        movie_xq_juzhao.setAdapter(mMovieXqJuzhaoAdapter);

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
        return R.layout.movie_xq_juzhao_fragment_layout;
    }

    void initView(View view) {
        movie_xq_juzhao = (RecyclerView) view.findViewById(R.id.movie_xq_juzhao);
    }

    @Override
    public void movieXQSuccess(MovieXqBean movieXqBean) {
        if (movieXqBean.status.equals("0000")) {

            List<String> posterList = movieXqBean.result.posterList;

            mMovieXqJuzhaoAdapter.addAll1(posterList);
            mMovieXqJuzhaoAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void movieXQError(String s) {

    }
}
