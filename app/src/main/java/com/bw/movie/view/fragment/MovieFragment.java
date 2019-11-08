package com.bw.movie.view.fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bw.movie.R;
import com.bw.movie.constraint.Constraint;
import com.bw.movie.model.bean.HomeBanner;
import com.bw.movie.model.bean.PopularMovieBean;
import com.bw.movie.model.bean.ReYingBean;
import com.bw.movie.model.bean.ShangYingBean;
import com.bw.movie.presenter.HomePageViewPresenter;
import com.bw.movie.view.activity.BannerXiangActivity;
import com.bw.movie.view.adapter.PopularRecycleAdapter;
import com.bw.movie.view.adapter.ReYingRecycleAdapter;
import com.bw.movie.view.adapter.ShangYingRecycleAdapter;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：吴新仲<p>
 * <p>创建时间：2019/11/7/007<p>
 * <p>更改时间：2019/11/7/007<p>
 */
public class MovieFragment extends BaseFragment<HomePageViewPresenter> implements Constraint.IHomeMovie,AMapLocationListener {
    private ImageView home_ding;
    private TextView home_ding_name;
    private ImageView home_sou;
    private Banner home_banner;
    private TextView home_re_duo;
    private RecyclerView home_ry_recycle;
    private TextView home_sy_duo;
    private RecyclerView home_sy_recycle;
    private TextView home_rm_duo;
    private RecyclerView home_rm_recycle;
    private ImageView pop_img;
    private TextView pop_name, pop_pf;
    private Button pop_btn_gp;
    public AMapLocationClientOption mLocationOption = null;
    private AMapLocationClient mlocationClient;
    private static final int MY_PERMISSIONS_REQUEST_CALL_LOCATION = 1;


    private static final String TAG = "MovieFragment";
    private ReYingRecycleAdapter mReYingRecycleAdapter;
    private ShangYingRecycleAdapter mShangYingRecycleAdapter;
    private PopularRecycleAdapter mPopularRecycleAdapter;


    private String district;

    @Override
    void initData() {

        //热映
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        home_ry_recycle.setLayoutManager(linearLayoutManager);

        mReYingRecycleAdapter = new ReYingRecycleAdapter();
        home_ry_recycle.setAdapter(mReYingRecycleAdapter);

        //即将上映
        LinearLayoutManager shangying = new LinearLayoutManager(getContext());
        shangying.setOrientation(LinearLayoutManager.VERTICAL);
        home_sy_recycle.setLayoutManager(shangying);

        mShangYingRecycleAdapter = new ShangYingRecycleAdapter();
        home_sy_recycle.setAdapter(mShangYingRecycleAdapter);

        //热门电影
        LinearLayoutManager pop = new LinearLayoutManager(getContext());
        pop.setOrientation(LinearLayoutManager.HORIZONTAL);
        home_rm_recycle.setLayoutManager(pop);

        mPopularRecycleAdapter = new PopularRecycleAdapter();
        home_rm_recycle.setAdapter(mPopularRecycleAdapter);

        presenter.homeBanner();
        presenter.reYing(true);
        presenter.shangYing(true);
        presenter.popular(true);


    }

    @Override
    HomePageViewPresenter getPresenter() {
        return new HomePageViewPresenter();
    }

    @Override
    void initListener() {

    }

    @Override
    int initLayout() {
        return R.layout.movie_fragment_layout;
    }

    void initView(View view) {
        home_ding = (ImageView) view.findViewById(R.id.home_ding);
        home_ding_name = (TextView) view.findViewById(R.id.home_ding_name);
        home_sou = (ImageView) view.findViewById(R.id.home_sou);
        home_banner = (Banner) view.findViewById(R.id.home_banner);
        home_re_duo = (TextView) view.findViewById(R.id.home_re_duo);
        home_ry_recycle = (RecyclerView) view.findViewById(R.id.home_ry_recycle);
        home_sy_duo = (TextView) view.findViewById(R.id.home_sy_duo);
        home_sy_recycle = (RecyclerView) view.findViewById(R.id.home_sy_recycle);
        home_rm_duo = (TextView) view.findViewById(R.id.home_rm_duo);
        home_rm_recycle = (RecyclerView) view.findViewById(R.id.home_rm_recycle);
        pop_img = (ImageView) view.findViewById(R.id.pop_img);
        pop_name = (TextView) view.findViewById(R.id.pop_name);
        pop_pf = (TextView) view.findViewById(R.id.pop_pf);
        pop_btn_gp = (Button) view.findViewById(R.id.pop_btn_gp);

        //检查版本是否大于M
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                        MY_PERMISSIONS_REQUEST_CALL_LOCATION);
            } else {
                //"权限已申请";
                showLocation();
            }
        }
        showLocation();
    }

    @Override
    public void bannerSuccess(HomeBanner homeBanner) {
        if (homeBanner.status.equals("0000")) {
//            Toast.makeText(getContext(), bannerBeans.message, Toast.LENGTH_SHORT).show();
            final List<HomeBanner.ResultBean> result = homeBanner.result;

            //设置banner样式
            home_banner.setBannerStyle(BannerConfig.NUM_INDICATOR);

            //设置图片加载器
            home_banner.setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    HomeBanner.ResultBean b = (HomeBanner.ResultBean) path;
                    Glide.with(context).load(b.imageUrl).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
                }
            });


            home_banner.setImages(result);
            //设置banner动画效果
            home_banner.setBannerAnimation(Transformer.Tablet);

            //设置自动轮播，默认为true
            home_banner.isAutoPlay(true);
            //设置轮播时间
            home_banner.setDelayTime(3000);
            //设置指示器位置（当banner模式中有指示器时）
            home_banner.setIndicatorGravity(BannerConfig.CENTER);

            //点击事件
            home_banner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {

                    String jumpUrl = result.get(position).jumpUrl;

                    Intent intent = null;

                    if (jumpUrl.startsWith("http")) {
                        intent = new Intent(getActivity(), BannerXiangActivity.class);
                        intent.putExtra("url", jumpUrl);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getContext(), "格式不正确", Toast.LENGTH_SHORT).show();
                        Uri uri = Uri.parse(jumpUrl);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    }
                }
            });

            //banner设置方法全部调用完毕时最后调用
            home_banner.start();
        } else {
            Toast.makeText(getContext(), homeBanner.message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void bannerError(String s) {
        Log.i(TAG, "bannerError: " + s);
    }

    @Override
    public void reyingSuccess(ReYingBean reYingBean) {
        if (reYingBean.status.equals("0000")) {
//            Toast.makeText(getContext(), reYingBean.message, Toast.LENGTH_SHORT).show();
            mReYingRecycleAdapter.addAdd(reYingBean.result);
            mReYingRecycleAdapter.notifyDataSetChanged();
        } else {
            Toast.makeText(getContext(), reYingBean.message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void reyingError(String s) {
        Log.i(TAG, "reyingError: " + s);
    }

    @Override
    public void shangyingSuccess(ShangYingBean shangYingBean) {
        if (shangYingBean.status.equals("0000")) {
//            Toast.makeText(getContext(), shangYingBean.message, Toast.LENGTH_SHORT).show();
            mShangYingRecycleAdapter.addAdd(shangYingBean.result);
            mShangYingRecycleAdapter.notifyDataSetChanged();

        } else {
            Toast.makeText(getContext(), shangYingBean.message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void shangyingError(String s) {
        Log.i(TAG, "shangyingError: " + s);
    }

    @Override
    public void popularSuccess(PopularMovieBean popularMovieBean) {
        if (popularMovieBean.status.equals("0000")) {

            pop_name.setText(popularMovieBean.result.get(0).name);
            pop_pf.setText(String.valueOf(popularMovieBean.result.get(0).score + "分"));
            Glide.with(getActivity()).load(popularMovieBean.result.get(0).imageUrl).into(pop_img);

//            Toast.makeText(getContext(), popularMovieBean.message, Toast.LENGTH_SHORT).show();
            mPopularRecycleAdapter.addAdd(popularMovieBean.result);
            mPopularRecycleAdapter.notifyDataSetChanged();

        } else {
            Toast.makeText(getContext(), popularMovieBean.message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void popularError(String s) {

    }


    private void showLocation() {
        try {
            mlocationClient = new AMapLocationClient(getActivity());
            mLocationOption = new AMapLocationClientOption();
            mLocationOption.setNeedAddress(true);
            mlocationClient.setLocationListener((AMapLocationListener) this);
            //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            mLocationOption.setInterval(5000);
            //设置定位参数
            mlocationClient.setLocationOption(mLocationOption);
            //启动定位
            mlocationClient.startLocation();

        } catch (Exception e) {

        }
    }

    public void onLocationChanged(AMapLocation amapLocation) {
        try {
            if (amapLocation != null) {
                if (amapLocation.getErrorCode() == 0) {
                    //定位成功回调信息，设置相关消息
                    //获取当前定位结果来源，如网络定位结果，详见定位类型表
                    Log.i("定位类型", amapLocation.getLocationType() + "");
                    Log.i("获取纬度", amapLocation.getLatitude() + "");
                    Log.i("获取经度", amapLocation.getLongitude() + "");
                    Log.i("获取精度信息", amapLocation.getAccuracy() + "");

                    //如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                    Log.i("地址", amapLocation.getAddress());
                    Log.i("国家信息", amapLocation.getCountry());
                    Log.i("省信息", amapLocation.getProvince());
                    Log.i("城市信息", amapLocation.getCity());
                    Log.i("城区信息", amapLocation.getDistrict());
                    Log.i("街道信息", amapLocation.getStreet());
                    Log.i("街道门牌号信息", amapLocation.getStreetNum());
                    Log.i("城市编码", amapLocation.getCityCode());
                    Log.i("地区编码", amapLocation.getAdCode());
                    Log.i("获取当前定位点的AOI信息", amapLocation.getAoiName());
                    Log.i("获取当前室内定位的建筑物Id", amapLocation.getBuildingId());
                    Log.i("获取当前室内定位的楼层", amapLocation.getFloor());
                    Log.i("获取GPS的当前状态", amapLocation.getGpsAccuracyStatus() + "");

//                    district = amapLocation.getDistrict();
                    home_ding_name.setText(amapLocation.getDistrict());

                    // 停止定位
                    mlocationClient.stopLocation();
                    mlocationClient.stopAssistantLocation();
                } else {
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                    Log.e("AmapError", "location Error, ErrCode:"
                            + amapLocation.getErrorCode() + ", errInfo:"
                            + amapLocation.getErrorInfo());
                }
            }
        } catch (Exception e) {

        }
    }
}
