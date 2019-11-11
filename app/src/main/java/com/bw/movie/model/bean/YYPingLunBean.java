package com.bw.movie.model.bean;

import java.util.List;

/**
 * date:19/11/9
 * author:张自磊(lenovo)
 * function:
 */
public class YYPingLunBean {

    public String message;
    public String status;
    public List<ResultBean> result;

    public static class ResultBean {
        /**
         * commentContent : 咋的了
         * commentHeadPic : http://172.17.8.100/images/movie/head_pic/2019-10-31/20191031165328.png
         * commentId : 93
         * commentTime : 1572512635000
         * commentUserId : 13678
         * commentUserName : abnetming
         * greatHeadPic : []
         * greatNum : 0
         * hotComment : 0
         * isGreat : 0
         */

        public String commentContent;
        public String commentHeadPic;
        public int commentId;
        public long commentTime;
        public int commentUserId;
        public String commentUserName;
        public int greatNum;
        public int hotComment;
        public int isGreat;
        public List<?> greatHeadPic;
    }
}
