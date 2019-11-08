package com.bw.movie.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bw.movie.R;
import com.bw.movie.view.fragment.CinemaFragment;
import com.bw.movie.view.fragment.MovieFragment;
import com.bw.movie.view.fragment.MyFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
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
    private Unbinder mBind;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        mBind = ButterKnife.bind(this);

        MovieFragment movieFragment = new MovieFragment();
        CinemaFragment cinemaFragment = new CinemaFragment();
        MyFragment myFragment = new MyFragment();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.home_view_pager,movieFragment)
                .add(R.id.home_view_pager,cinemaFragment)
                .add(R.id.home_view_pager,myFragment)
                .show(movieFragment)
                .hide(cinemaFragment)
                .hide(myFragment)
                .commit();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb1:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .show(movieFragment)
                                .hide(cinemaFragment)
                                .hide(myFragment)
                                .commit();
                        break;
                    case R.id.rb2:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .show(cinemaFragment)
                                .hide(movieFragment)
                                .hide(myFragment)
                                .commit();
                        break;
                    case R.id.rb3:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .show(myFragment)
                                .hide(cinemaFragment)
                                .hide(movieFragment)
                                .commit();
                        break;
                }
            }
        });

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBind.unbind();
    }
}
