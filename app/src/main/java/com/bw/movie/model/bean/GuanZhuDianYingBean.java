package com.bw.movie.model.bean;

import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：张自磊<p>
 * <p>创建时间：2019/11/15<p>
 * <p>更改时间：2019/11/15<p>
 */
public class GuanZhuDianYingBean {

    public String message;
    public String status;
    public List<ResultBean> result;

    public static class ResultBean {
        /**
         * director : 文牧野
         * imageUrl : http://172.17.8.100/images/movie/stills/wbsys/wbsys1.jpg
         * movieId : 1
         * name : 我不是药神
         * score : 8.9
         * starring : 徐峥,周一围,王传君,谭卓,章宇,杨新鸣,王砚辉
         */

        public String director;
        public String imageUrl;
        public int movieId;
        public String name;
        public double score;
        public String starring;
    }
}
