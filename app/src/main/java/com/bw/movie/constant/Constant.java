package com.bw.movie.constant;


import com.bw.movie.model.bean.CHaiLeftBean;
import com.bw.movie.model.bean.CHaiRightBean;
import com.bw.movie.model.bean.CTuiJianBean;
import com.bw.movie.model.bean.EmailBean;
import com.bw.movie.model.bean.HomeBanner;
import com.bw.movie.model.bean.PopularMovieBean;
import com.bw.movie.model.bean.ReYingBean;
import com.bw.movie.model.bean.RegisterBean;

import com.bw.movie.model.bean.SearchBean;
import com.bw.movie.model.bean.ShangYingBean;
import com.bw.movie.model.bean.XLLoginBean;
import com.bw.movie.model.bean.YYGuanZhuBean;
import com.bw.movie.model.bean.YYPingLunBean;
import com.bw.movie.model.bean.YYXiangQingBean;
import com.bw.movie.model.bean.YingYuanLieBiaoBean;
import com.bw.movie.view.activity.YingYuanXiangQing;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
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

    //根据关键字查询电影
    @GET("movieApi/movie/v2/findMovieByKeyword")
    Observable<SearchBean> SEARCH_BEAN(@Query("keyword")String keyword,
                                       @Query("page")int page,
                                       @Query("count")int count);


    //即将上映
    @GET("movieApi/movie/v2/findComingSoonMovieList")
    Observable<ShangYingBean> SHANG_YING_BEAN(@Query("page") int page,
                                              @Query("count") int count);

    //影院推荐
    @GET("movieApi/cinema/v1/findRecommendCinemas")
    Observable<CTuiJianBean> FINDRECOMMENDCINEMAS(@Header("userId")int userId,
                                                  @Header("sessionId")String sessionId,
                                                  @Query("page")int page,
                                                  @Query("count")int count);

    //影院附近
    @GET("movieApi/cinema/v1/findNearbyCinemas")
    Observable<CTuiJianBean> FINDNEARBYCINEMAS(@Header("userId")int userId,
                                                  @Header("sessionId")String sessionId,
                                                  @Query("longitude")String longitude,
                                                  @Query("latitude")String latitude,
                                                  @Query("page")int page,
                                                  @Query("count")int count);

    //影院海淀
    @GET("movieApi/cinema/v2/findCinemaByRegion")
    Observable<CHaiRightBean> FINDCINEMABYREGION(@Query("regionId")int regionId);

    //影院附近
    @GET("movieApi/tool/v2/findRegionList")
    Observable<CHaiLeftBean> FINDREGIONLIST();

    //影院列表
    @GET("movieApi/cinema/v2/findCinemaScheduleList")
    Observable<YingYuanLieBiaoBean> FINDCINEMASCHEDULELIST(@Query("cinemaId")int cinemaId,
                                                           @Query("page")int page,
                                                           @Query("count")int count);
    //影院详情
    @GET("movieApi/cinema/v1/findCinemaInfo")
    Observable<YYXiangQingBean> FINDCINEMAINFO(@Query("cinemaId")int cinemaId);

    //关注影院
    @GET("movieApi/cinema/v1/verify/followCinema")
    Observable<YYGuanZhuBean> FOLLOWCINEMA(@Header("userId")int userId,
                                           @Header("sessionId")String sessionId,
                                           @Query("cinemaId")int cinemaId);

    //取关影院
    @GET("movieApi/cinema/v1/verify/cancelFollowCinema")
    Observable<YYGuanZhuBean> CANCELFOLLOWCINEMA(@Header("userId")int userId,
                                           @Header("sessionId")String sessionId,
                                           @Query("cinemaId")int cinemaId);

    //评论影院
    @POST("movieApi/cinema/v1/verify/cinemaComment")
    @FormUrlEncoded
    Observable<YYGuanZhuBean> CINEMACOMMENT(@Header("userId")int userId,
                                            @Header("sessionid")String sessionId,
                                            @Field("cinemaId") int cinemaId,
                                            @Field("commentContent") String commentContent);

    //影院评论点赞
    @POST("movieApi/cinema/v1/verify/cinemaCommentGreat")
    @FormUrlEncoded
    Observable<YYGuanZhuBean> CINEMACOMMENTGREAT(@Header("userId")int userId,
                                            @Header("sessionid")String sessionId,
                                            @Field("commentId") int commentId);

    //影院评论列表
    @GET("movieApi/cinema/v1/findAllCinemaComment")
    Observable<YYPingLunBean> FINDALLCINEMACOMMENT(@Header("userId")int userId,
                                                   @Header("sessionId")String sessionId,
                                                   @Query("page")int page,
                                                   @Query("count")int count,
                                                   @Query("cinemaId")int cinemaId);
}
