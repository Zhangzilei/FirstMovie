package com.bw.movie.model.bean;

import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：吴新仲<p>
 * <p>创建时间：2019/11/13/013<p>
 * <p>更改时间：2019/11/13/013<p>
 */
public class MovieXqBean {

    public ResultBean result;
    public String message;
    public String status;

    public static class ResultBean {
        /**
         * commentNum : 23
         * duration : 102分钟
         * imageUrl : http://172.17.8.100/images/movie/stills/zdn/zdn1.jpg
         * movieActor : [{"name":"姚晨","photo":"http://172.17.8.100/images/movie/actor/zdn/yaochen.jpg","role":"李捷"},{"name":"马伊琍","photo":"http://172.17.8.100/images/movie/actor/zdn/mayili.jpg","role":"孙芳"},{"name":"\r\n袁文康","photo":"http://172.17.8.100/images/movie/actor/zdn/yuanwenkang.jpg","role":"田宁"},{"name":"\r\n吴昊宸","photo":"http://172.17.8.100/images/movie/actor/zdn/wuhaochen.jpg","role":"张博"}]
         * movieDirector : [{"name":"吕乐","photo":"http://172.17.8.100/images/movie/director/zdn/1.jpg"}]
         * movieId : 21
         * movieType : 剧情
         * name : 找到你
         * placeOrigin : 中国大陆
         * posterList : ["http://172.17.8.100/images/movie/stills/zdn/zdn1.jpg","http://172.17.8.100/images/movie/stills/zdn/zdn2.jpg","http://172.17.8.100/images/movie/stills/zdn/zdn3.jpg","http://172.17.8.100/images/movie/stills/zdn/zdn4.jpg","http://172.17.8.100/images/movie/stills/zdn/zdn5.jpg","http://172.17.8.100/images/movie/stills/zdn/zdn6.jpg"]
         * releaseTime : 1537545600000
         * score : 8.5
         * shortFilmList : [{"imageUrl":"http://172.17.8.100/images/movie/stills/zdn/zdn3.jpg","videoUrl":"http://172.17.8.100/video/movie/zdn/zdn1.mp4"},{"imageUrl":"http://172.17.8.100/images/movie/stills/zdn/zdn2.jpg","videoUrl":"http://172.17.8.100/video/movie/zdn/zdn2.mp4"},{"imageUrl":"http://172.17.8.100/images/movie/stills/zdn/zdn4.jpg","videoUrl":"http://172.17.8.100/video/movie/zdn/zdn3.mp4"}]
         * summary : 律师李捷（姚晨 饰）正在离婚进行时，与前夫争夺女儿抚养权，拼命工作为给孩子最好的生活，幸有保姆孙芳（马伊琍 饰）帮忙照顾孩子视如己出。一日下班，李捷发现保姆孙芳和女儿毫无预兆地消失了，她内心最大的恐惧变成了现实。在追寻孙芳和女儿的下落时，她收到来自家人的谴责声讨，甚至遭到警方的怀疑。几乎崩溃的李捷，靠着惊人的勇气，踏上独自寻访的旅程。在追踪过程中，李捷逐渐接近了另一个女人——保姆孙芳的人生故事，她的身份原先都是谎言，而真相也将浮出水面……
         * whetherFollow : 2
         */

        public int commentNum;
        public String duration;
        public String imageUrl;
        public int movieId;
        public String movieType;
        public String name;
        public String placeOrigin;
        public long releaseTime;
        public double score;
        public String summary;
        public int whetherFollow;
        public List<MovieActorBean> movieActor;
        public List<MovieDirectorBean> movieDirector;
        public List<String> posterList;
        public List<ShortFilmListBean> shortFilmList;

        public static class MovieActorBean {
            /**
             * name : 姚晨
             * photo : http://172.17.8.100/images/movie/actor/zdn/yaochen.jpg
             * role : 李捷
             */

            public String name;
            public String photo;
            public String role;
        }

        public static class MovieDirectorBean {
            /**
             * name : 吕乐
             * photo : http://172.17.8.100/images/movie/director/zdn/1.jpg
             */

            public String name;
            public String photo;
        }

        public static class ShortFilmListBean {
            /**
             * imageUrl : http://172.17.8.100/images/movie/stills/zdn/zdn3.jpg
             * videoUrl : http://172.17.8.100/video/movie/zdn/zdn1.mp4
             */

            public String imageUrl;
            public String videoUrl;
        }
    }
}
