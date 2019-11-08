package com.bw.movie.constant;


import com.bw.movie.model.bean.EmailBean;
import com.bw.movie.model.bean.HomeBanner;
import com.bw.movie.model.bean.PopularMovieBean;
import com.bw.movie.model.bean.ReYingBean;
import com.bw.movie.model.bean.RegisterBean;

import com.bw.movie.model.bean.ShangYingBean;
import com.bw.movie.model.bean.XLLoginBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * <p>文件描述：接口<p>
 * <p>作者：吴新仲<p>
 * <p>创建时间：2019/11/5/005<p>
 * <p>更改时间：2019/11/5/005<p>
 */
public interface Constant {
    //注册
    @POST("movieApi/user/v2/register")
    @FormUrlEncoded
    Observable<RegisterBean> REGISTER(@Field("nickName") String nickName,
                                      @Field("pwd") String pwd,
                                      @Field("email") String email,
                                      @Field("code") String code);

    //获取邮箱验证码
    @POST("movieApi/user/v2/sendOutEmailCode")
    @FormUrlEncoded
    Observable<EmailBean> SENDOUTEMAILCODE(@Field("email") String email);

    //登录接口
    @FormUrlEncoded
    @POST("movieApi/user/v2/login")
    Observable<XLLoginBean> XL_LOGIN_BEAN(@Field("email") String email,
                                          @Field("pwd") String pwd);

    //banner
    @GET("movieApi/tool/v2/banner")
    Observable<HomeBanner> HOME_BANNER();

    //热门电影
    @GET("movieApi/movie/v2/findHotMovieList")
    Observable<PopularMovieBean> POPULAR_MOVIE_BEAN(@Query("page") int page,
                                                    @Query("count") int count);

    //正在热映
    @GET("movieApi/movie/v2/findReleaseMovieList")
    Observable<ReYingBean> RE_YING_BEAN(@Query("page") int page,
                                        @Query("count") int count);


    //即将上映
    @GET("movieApi/movie/v2/findComingSoonMovieList")
    Observable<ShangYingBean> SHANG_YING_BEAN(@Query("page") int page,
                                              @Query("count") int count);
}
