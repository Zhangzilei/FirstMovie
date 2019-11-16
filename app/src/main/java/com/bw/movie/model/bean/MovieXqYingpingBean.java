package com.bw.movie.model.bean;

import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：吴新仲<p>
 * <p>创建时间：2019/11/15/015<p>
 * <p>更改时间：2019/11/15/015<p>
 */
public class MovieXqYingpingBean {

    public String message;
    public String status;
    public List<ResultBean> result;

    public static class ResultBean {
        /**
         * commentContent : 唉唉唉
         * commentHeadPic : http://172.17.8.100/images/movie/head_pic/2019-11-13/20191113094254.jpg
         * commentId : 2024
         * commentTime : 1572868390000
         * commentUserId : 13714
         * commentUserName : run
         * greatNum : 0
         * isGreat : 0
         * replyHeadPic : []
         * replyNum : 0
         * score : 5.1
         */

        public String commentContent;
        public String commentHeadPic;
        public int commentId;
        public long commentTime;
        public int commentUserId;
        public String commentUserName;
        public int greatNum;
        public int isGreat;
        public int replyNum;
        public double score;
        public List<?> replyHeadPic;
    }
}
