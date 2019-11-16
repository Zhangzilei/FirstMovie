package com.bw.movie.model.bean;

/**
 * <p>文件描述：<p>
 * <p>作者：张自磊<p>
 * <p>创建时间：2019/11/15<p>
 * <p>更改时间：2019/11/15<p>
 */
public class ZiLiaoBean {

    public ResultBean result;
    public String message;
    public String status;

    public static class ResultBean {
        /**
         * email : 1946298407@qq.com
         * headPic : http://172.17.8.100/images/movie/head_pic/bwjy.jpg
         * id : 13766
         * lastLoginTime : 1573788894000
         * nickName : nideyifa
         * sex : 1
         */

        public String email;
        public String headPic;
        public int id;
        public long lastLoginTime;
        public String nickName;
        public int sex;
    }
}
