package com.bw.movie.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.R;
import com.bw.movie.constraint.Constraint;
import com.bw.movie.model.bean.MovieXqYingpingBean;
import com.bw.movie.presenter.MovieXqYpPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

/**
 * <p>文件描述：<p>
 * <p>作者：吴新仲<p>
 * <p>创建时间：2019/11/13/013<p>
 * <p>更改时间：2019/11/13/013<p>
 */
public class YingpingFragment extends BaseFragment<MovieXqYpPresenter> implements Constraint.IMovieXqYpView {

    private int mMovieId;
    private XRecyclerView movie_xq_yingping_recycle;

    @Override
    void initData() {

        Intent intent = getActivity().getIntent();
        mMovieId = intent.getIntExtra("movieId", 0);




        presenter.yp(mMovieId, true);
    }

    @Override
    MovieXqYpPresenter getPresenter() {
        return new MovieXqYpPresenter();
    }

    @Override
    void initListener() {

    }

    @Override
    int initLayout() {
        return R.layout.movie_xq_yingping_fragment_layout;
    }

    void initView(View view) {
        movie_xq_yingping_recycle = view.findViewById(R.id.movie_xq_yingping_recycle);
    }

    @Override
    public void movieXqYpSuccess(MovieXqYingpingBean movieXqYingpingBean) {

    }

    @Override
    public void movieXqYpError(String s) {

    }
}
