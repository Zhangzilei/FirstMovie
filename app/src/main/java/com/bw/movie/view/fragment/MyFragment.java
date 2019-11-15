package com.bw.movie.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.dao.DaoMaster;
import com.bw.movie.dao.DaoSession;
import com.bw.movie.dao.UserDao;
import com.bw.movie.model.bean.User;
import com.bw.movie.view.activity.DianYingPiaoActivity;
import com.bw.movie.view.activity.FanKuiActivity;
import com.bw.movie.view.activity.GouPiaoActivity;
import com.bw.movie.view.activity.GuanZhuActivity;
import com.bw.movie.view.activity.MovieActivity;
import com.bw.movie.view.activity.MyXinxiActivity;
import com.bw.movie.view.activity.PingLunActivity;
import com.bw.movie.view.activity.SheZhiActivity;
import com.bw.movie.view.activity.YuYueActivity;
import com.bw.movie.view.activity.ZiliaoActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * <p>文件描述：<p>
 * <p>作者：吴新仲<p>
 * <p>创建时间：2019/11/7/007<p>
 * <p>更改时间：2019/11/7/007<p>
 */
public class MyFragment extends Fragment {
    @BindView(R.id.my_xinxi)
    ImageView myXinxi;
    @BindView(R.id.my_text_name)
    TextView mytextname;
    @BindView(R.id.my_head_ic)
    ImageView myHeadIc;
    @BindView(R.id.my_ziliao)
    ImageView myZiliao;
    @BindView(R.id.my_dyp)
    ImageView myDyp;
    @BindView(R.id.my_dianyingpiao)
    ImageView myDianyingpiao;
    @BindView(R.id.my_guanzhu)
    LinearLayout myGuanzhu;
    @BindView(R.id.my_movie)
    LinearLayout myMovie;
    @BindView(R.id.my_shezhi)
    LinearLayout myShezhi;
    @BindView(R.id.my_yuyue)
    LinearLayout myYuyue;
    @BindView(R.id.my_pinglun)
    LinearLayout myPinglun;
    @BindView(R.id.my_goupiao)
    LinearLayout myGoupiao;
    @BindView(R.id.my_fankui)
    LinearLayout myFankui;
    Unbinder unbinder;
    private UserDao mUserDao;
    private String headPic;
    private String nickName;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.my_fragment_layout, container, false);
        unbinder = ButterKnife.bind(this, view);
        DaoSession daoSession = DaoMaster.newDevSession(getContext(), UserDao.TABLENAME);
        mUserDao = daoSession.getUserDao();

        List<User> users = mUserDao.loadAll();
        for (int i = 0; i < users.size(); i++) {
            headPic = users.get(i).getHeadPic();
            nickName = users.get(i).getNickName();
        }

        Glide.with(getContext()).load(headPic).into(myHeadIc);
        mytextname.setText(nickName);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.my_xinxi, R.id.my_ziliao, R.id.my_dianyingpiao, R.id.my_guanzhu, R.id.my_movie, R.id.my_shezhi, R.id.my_yuyue, R.id.my_pinglun, R.id.my_goupiao, R.id.my_fankui})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //消息
            case R.id.my_xinxi:
                startActivity(new Intent(getContext(), MyXinxiActivity.class));
                break;
            case R.id.my_ziliao:
                startActivity(new Intent(getContext(), ZiliaoActivity.class));
                break;
            case R.id.my_dianyingpiao:
                startActivity(new Intent(getContext(), DianYingPiaoActivity.class));
                break;
            case R.id.my_guanzhu:
                startActivity(new Intent(getContext(), GuanZhuActivity.class));
                break;
            case R.id.my_movie:
                startActivity(new Intent(getContext(), MovieActivity.class));
                break;
            case R.id.my_shezhi:
                startActivity(new Intent(getContext(), SheZhiActivity.class));
                break;
            case R.id.my_yuyue:
                startActivity(new Intent(getContext(), YuYueActivity.class));
                break;
            case R.id.my_pinglun:
                startActivity(new Intent(getContext(), PingLunActivity.class));
                break;
            case R.id.my_goupiao:
                startActivity(new Intent(getContext(), GouPiaoActivity.class));
                break;
            case R.id.my_fankui:
                startActivity(new Intent(getContext(), FanKuiActivity.class));
                break;
        }
    }
}
