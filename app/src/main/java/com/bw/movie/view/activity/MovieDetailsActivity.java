package com.bw.movie.view.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.constraint.Constraint;
import com.bw.movie.model.bean.MovieXqBean;
import com.bw.movie.presenter.MovieXQPresenter;
import com.bw.movie.view.adapter.MovieXqTabAdapter;
import com.bw.movie.view.fragment.JieshaoFragment;
import com.bw.movie.view.fragment.JuzhaoFragment;
import com.bw.movie.view.fragment.YingpingFragment;
import com.bw.movie.view.fragment.YugaoFragment;

import java.util.ArrayList;

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
    @BindView(R.id.movie_details_xpl)
    Button movieDetailsXpl;
    @BindView(R.id.movie_details_xzgp)
    Button movieDetailsXzgp;
    private int mMovieId;

    private ArrayList<String> mTitle = new ArrayList<>();
    private ArrayList<Fragment> mList = new ArrayList<>();
    private JieshaoFragment mJieshaoFragment;
    private YugaoFragment mYugaoFragment;
    private JuzhaoFragment mJuzhaoFragment;
    private YingpingFragment mYingpingFragment;

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

        mTitle.add("介绍");
        mTitle.add("预告");
        mTitle.add("剧照");
        mTitle.add("影评");

        mJieshaoFragment = new JieshaoFragment();
        mYugaoFragment = new YugaoFragment();
        mJuzhaoFragment = new JuzhaoFragment();
        mYingpingFragment = new YingpingFragment();

        mList.add(mJieshaoFragment);
        mList.add(mYugaoFragment);
        mList.add(mJuzhaoFragment);
        mList.add(mYingpingFragment);

        movieDetailsTab.setupWithViewPager(movieDetailsViewPager);

        MovieXqTabAdapter movieXqTabAdapter = new MovieXqTabAdapter(getSupportFragmentManager(), mTitle, mList);
        movieDetailsViewPager.setAdapter(movieXqTabAdapter);

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
                movieDetailsLinShang.setVisibility(View.GONE);
                break;
            case R.id.movie_details_xpl:
                Toast.makeText(this, "写评论", Toast.LENGTH_SHORT).show();
                break;
            case R.id.movie_details_xzgp:
                startActivity(new Intent(this,AllGouPiaoActivity.class));
                break;
        }
    }

    @Override
    public void movieXQSuccess(MovieXqBean movieXqBean) {
        if (movieXqBean.status.equals("0000")) {

            MovieXqBean.ResultBean result = movieXqBean.result;

            Glide.with(this).load(result.imageUrl).into(movieDetailsImg);

            movieDetailsPf.setText(String.valueOf(result.score + "分"));
            movieDetailsPl.setText(String.valueOf(result.commentNum + "条"));
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
