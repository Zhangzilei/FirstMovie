package com.bw.movie.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.constraint.Constraint;
import com.bw.movie.model.bean.MovieXqBean;
import com.bw.movie.presenter.MovieXQPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MovieDetailsActivity extends BaseActivity<MovieXQPresenter> implements Constraint.IMovieXqView {

    @BindView(R.id.movie_details_img)
    ImageView movieDetailsImg;
    @BindView(R.id.movie_details_back)
    ImageView movieDetailsBack;
    @BindView(R.id.movie_details_pf)
    TextView movieDetailsPf;
    @BindView(R.id.movie_details_pl)
    TextView movieDetailsPl;
    @BindView(R.id.movie_details_name)
    TextView movieDetailsName;
    @BindView(R.id.movie_details_type)
    TextView movieDetailsType;
    @BindView(R.id.movie_details_time)
    TextView movieDetailsTime;
    @BindView(R.id.lin)
    LinearLayout lin;
    @BindView(R.id.movie_details_address)
    TextView movieDetailsAddress;
    @BindView(R.id.movie_details_check_guanzhu)
    CheckBox movieDetailsCheckGuanzhu;
    @BindView(R.id.movie_details_text_guanzhu)
    TextView movieDetailsTextGuanzhu;
    @BindView(R.id.movie_details_yin)
    TextView movieDetailsYin;
    @BindView(R.id.movie_details_lin_shang)
    LinearLayout movieDetailsLinShang;
    @BindView(R.id.movie_details_tab)
    TabLayout movieDetailsTab;
    @BindView(R.id.movie_details_view_pager)
    ViewPager movieDetailsViewPager;
    @BindView(R.id.movie_details_lin_xia)
    LinearLayout movieDetailsLinXia;
    @BindView(R.id.movie_details_xpl)
    Button movieDetailsXpl;
    @BindView(R.id.movie_details_xzgp)
    Button movieDetailsXzgp;
    private int mMovieId;

    @Override
    void initData() {
        presenter.movieXQ(mMovieId);
    }

    @Override
    MovieXQPresenter getPresenter() {
        return new MovieXQPresenter();
    }

    @Override
    void initListener() {
        Intent intent = getIntent();
        mMovieId = intent.getIntExtra("movieId", 0);
    }

    @Override
    int initLayout() {
        return R.layout.activity_movie_details;
    }

    @OnClick({R.id.movie_details_back, R.id.lin, R.id.movie_details_xpl, R.id.movie_details_xzgp})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.movie_details_back:
                finish();
                break;
            case R.id.movie_details_yin:
                break;
            case R.id.movie_details_xpl:
                break;
            case R.id.movie_details_xzgp:
                break;
        }
    }

    @Override
    public void movieXQSuccess(MovieXqBean movieXqBean) {
        if (movieXqBean.status.equals("0000")) {

            MovieXqBean.ResultBean result = movieXqBean.result;

            Glide.with(this).load(result.imageUrl).into(movieDetailsImg);

            movieDetailsPf.setText(String.valueOf(result.score+"分"));
            movieDetailsPl.setText(String.valueOf(result.commentNum+"条"));
            movieDetailsName.setText(result.name);
            movieDetailsType.setText(result.movieType);
            movieDetailsTime.setText(result.duration);
            movieDetailsAddress.setText(result.placeOrigin);


        }
    }

    @Override
    public void movieXQError(String s) {

    }
}
