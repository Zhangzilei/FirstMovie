package com.bw.movie.model.bean;

import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：吴新仲<p>
 * <p>创建时间：2019/11/7/007<p>
 * <p>更改时间：2019/11/7/007<p>
 */
public class HomeBanner {

    public String message;
    public String status;
    public List<ResultBean> result;

    public static class ResultBean {

        public String imageUrl;
        public String jumpUrl;
        public int rank;
    }
}
