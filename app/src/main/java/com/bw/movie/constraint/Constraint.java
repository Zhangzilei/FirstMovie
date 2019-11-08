package com.bw.movie.constraint;


import com.bw.movie.model.bean.CHaiLeftBean;
import com.bw.movie.model.bean.CHaiRightBean;
import com.bw.movie.model.bean.CTuiJianBean;
import com.bw.movie.model.bean.EmailBean;
import com.bw.movie.model.bean.HomeBanner;
import com.bw.movie.model.bean.PopularMovieBean;
import com.bw.movie.model.bean.ReYingBean;
import com.bw.movie.model.bean.RegisterBean;

import com.bw.movie.model.bean.ShangYingBean;
import com.bw.movie.model.bean.XLLoginBean;

import com.bw.movie.view.interfaces.IBaseView;

/**
 * <p>文件描述：契约类<p>
 * <p>作者：吴新仲<p>
 * <p>创建时间：2019/11/5/005<p>
 * <p>更改时间：2019/11/5/005<p>
 */
public interface Constraint {

    //注册
    interface RegisterView extends IBaseView {
        void registerSuccess(RegisterBean registerBean);

        void registerError(String s);

        void emailSuccess(EmailBean emailBean);

        void emailError(String s);
    }

    //登录
    interface ILoginView extends IBaseView {
        void loginSuccess(XLLoginBean xlLoginBean);

        void loginError(String s);
    }

    //首页电影展示
    interface IHomeMovie extends IBaseView {
        //banner
        void bannerSuccess(HomeBanner homeBanner);

        void bannerError(String s);

        //热映
        void reyingSuccess(ReYingBean reYingBean);

        void reyingError(String s);

        //上映
        void shangyingSuccess(ShangYingBean shangYingBean);

        void shangyingError(String s);

        //热门
        void popularSuccess(PopularMovieBean popularMovieBean);

        void popularError(String s);
    }

    //附近和推荐
    interface TuiJianView extends IBaseView{
        void tuijianSuccess(CTuiJianBean cTuiJianBean);
        void tuijianError(String s);
    }
    //chaileft
    interface CHaiView extends IBaseView{
        void leftSuccess(CHaiLeftBean leftBean);
        void leftError(String s);

        void rightSuccess(CHaiRightBean rightBean);
        void rightError(String s);
    }
}
