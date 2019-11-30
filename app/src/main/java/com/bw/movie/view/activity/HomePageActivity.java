package com.bw.movie.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.view.fragment.CinemaFragment;
import com.bw.movie.view.fragment.MovieFragment;
import com.bw.movie.view.fragment.MyFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class HomePageActivity extends AppCompatActivity {

    @BindView(R.id.home_view_pager)
    FrameLayout homeViewPager;
    @BindView(R.id.radio_group)
    RadioGroup radioGroup;
    @BindView(R.id.rb1)
    RadioButton rb1;
    @BindView(R.id.rb2)
    RadioButton rb2;
    @BindView(R.id.rb3)
    RadioButton rb3;
    @BindView(R.id.movie_lin)
    LinearLayout movieLin;
    @BindView(R.id.movie_img)
    ImageView movieImg;
    @BindView(R.id.movie_rel)
    RelativeLayout movieRel;
    @BindView(R.id.cinema_lin)
    LinearLayout cinemaLin;
    @BindView(R.id.cinema_img)
    ImageView cinemaImg;
    @BindView(R.id.cinema_rel)
    RelativeLayout cinemaRel;
    @BindView(R.id.my_lin)
    LinearLayout myLin;
    @BindView(R.id.my_img)
    ImageView myImg;
    @BindView(R.id.my_rel)
    RelativeLayout myRel;
    private Unbinder mBind;
    private MovieFragment mMovieFragment;
    private CinemaFragment mCinemaFragment;
    private MyFragment mMyFragment;
    //退出时的时间
    private long mExitTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        mBind = ButterKnife.bind(this);

        mMovieFragment = new MovieFragment();
        mCinemaFragment = new CinemaFragment();
        mMyFragment = new MyFragment();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.home_view_pager, mMovieFragment)
                .add(R.id.home_view_pager, mCinemaFragment)
                .add(R.id.home_view_pager, mMyFragment)
                .show(mMovieFragment)
                .hide(mCinemaFragment)
                .hide(mMyFragment)
                .commit();

    }

    @OnClick({R.id.movie_rel, R.id.cinema_rel, R.id.my_rel})
    public void onBtn(View view) {
        movieLin.setVisibility(View.GONE);
        cinemaLin.setVisibility(View.GONE);
        myLin.setVisibility(View.GONE);

        movieImg.setVisibility(View.VISIBLE);
        cinemaImg.setVisibility(View.VISIBLE);
        myImg.setVisibility(View.VISIBLE);

        if (view.getId() == R.id.movie_rel) {

            movieLin.setVisibility(View.VISIBLE);
            movieImg.setVisibility(View.GONE);

            getSupportFragmentManager()
                    .beginTransaction()
                    .show(mMovieFragment)
                    .hide(mCinemaFragment)
                    .hide(mMyFragment)
                    .commit();

        } else if (view.getId() == R.id.cinema_rel) {

            cinemaLin.setVisibility(View.VISIBLE);
            cinemaImg.setVisibility(View.GONE);

            getSupportFragmentManager()
                    .beginTransaction()
                    .show(mCinemaFragment)
                    .hide(mMovieFragment)
                    .hide(mMyFragment)
                    .commit();

        } else if (view.getId() == R.id.my_rel) {

            myLin.setVisibility(View.VISIBLE);
            myImg.setVisibility(View.GONE);

            getSupportFragmentManager()
                    .beginTransaction()
                    .show(mMyFragment)
                    .hide(mCinemaFragment)
                    .hide(mMovieFragment)
                    .commit();

        }
    }
    //对返回键进行监听
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {

            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exit() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBind.unbind();
    }
}
