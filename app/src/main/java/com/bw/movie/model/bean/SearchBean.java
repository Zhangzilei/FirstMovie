package com.bw.movie.model.bean;

import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：吴新仲<p>
 * <p>创建时间：2019/11/12/012<p>
 * <p>更改时间：2019/11/12/012<p>
 */
public class SearchBean {

    public String message;
    public String status;
    public List<ResultBean> result;

    public static class ResultBean {
        /**
         * director : 陈凯歌
         * imageUrl : http://172.17.8.100/images/movie/stills/whwdzg/whwdzg1.jpg
         * movieId : 23
         * name : 我和我的祖国
         * score : 9.7
         * starring : 黄渤,张译,杜江,葛优,刘昊然,吴京
         */

        public String director;
        public String imageUrl;
        public int movieId;
        public String name;
        public double score;
        public String starring;
    }
}
