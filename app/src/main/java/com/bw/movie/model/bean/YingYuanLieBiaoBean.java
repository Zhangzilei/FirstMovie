package com.bw.movie.model.bean;

import java.util.List;

/**
 * date:19/11/8
 * author:张自磊(lenovo)
 * function:
 */
public class YingYuanLieBiaoBean {

    public String message;
    public String status;
    public List<ResultBean> result;

    public static class ResultBean {
        /**
         * director :
         刘伟强
         * imageUrl : http://172.17.8.100/images/movie/stills/zgjz/zgjz1.jpg
         * movieId : 24
         * name : 中国机长
         * score : 9.4
         * starring : 张涵予,欧豪,袁泉,张天爱,李沁
         * trailerUrl : http://172.17.8.100/video/movie/zgjz/zgjz1.mp4
         */

        public String director;
        public String imageUrl;
        public int movieId;
        public String name;
        public double score;
        public String starring;
        public String trailerUrl;
    }
}
