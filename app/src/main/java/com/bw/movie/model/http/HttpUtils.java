package com.bw.movie.model.http;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.bw.movie.app.XLApp;
import com.bw.movie.constant.Constant;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * <p>文件描述：<p>
 * <p>作者：吴新仲<p>
 * <p>创建时间：2019/11/5/005<p>
 * <p>更改时间：2019/11/5/005<p>
 */
public class HttpUtils {
    //单例模式
    private static final HttpUtils ourInstance = new HttpUtils();
    private Constant mConstant;

    public static HttpUtils getInstance() {
        return ourInstance;
    }

    private HttpUtils() {
    }

    //网络判断
    public boolean getCode() {
        ConnectivityManager c = (ConnectivityManager) XLApp.sContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = c.getActiveNetworkInfo();
        if (activeNetworkInfo != null) {
            return activeNetworkInfo.isAvailable();
        }
        return false;
    }


    //获取数据
    public void getData() {

        //网络判断
        boolean code = getCode();
        if (code) {
//            Toast.makeText(XLApp.sContext, "有网", Toast.LENGTH_SHORT).show();
            //须要网络判断就把下面的东西放到这里

        } else {
            Toast.makeText(XLApp.sContext, "没网", Toast.LENGTH_SHORT).show();
        }

        //拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        HttpLoggingInterceptor interceptor = loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient build = new OkHttpClient.Builder().addInterceptor(interceptor).build();


        Retrofit build1 = new Retrofit.Builder()
                .client(build)
                .baseUrl("http://172.17.8.100/")
                //.baseUrl("http://mobile.bwstudent.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        mConstant = build1.create(Constant.class);
    }

    public Constant getConstant() {
        getData();
        return mConstant;
    }

}
