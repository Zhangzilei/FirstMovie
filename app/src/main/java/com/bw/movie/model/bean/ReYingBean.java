package com.bw.movie.model.bean;

import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：吴新仲<p>
 * <p>创建时间：2019/11/7/007<p>
 * <p>更改时间：2019/11/7/007<p>
 */
public class ReYingBean {

    public String message;
    public String status;
    public List<ResultBean> result;

    public static class ResultBean {

        public String director;
        public String imageUrl;
        public int movieId;
        public String name;
        public double score;
        public String starring;
    }
}
