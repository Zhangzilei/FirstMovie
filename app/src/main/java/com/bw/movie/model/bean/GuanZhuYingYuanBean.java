package com.bw.movie.model.bean;

import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：张自磊<p>
 * <p>创建时间：2019/11/15<p>
 * <p>更改时间：2019/11/15<p>
 */
public class GuanZhuYingYuanBean {

    public String message;
    public String status;
    public List<ResultBean> result;

    public static class ResultBean {
        /**
         * address : 湖景东路11号新奥购物中心B1(东A口)
         * cinemaId : 5
         * logo : http://172.17.8.100/images/movie/logo/CGVxx.jpg
         * name : CGV星星影城
         */

        public String address;
        public int cinemaId;
        public String logo;
        public String name;
    }
}
