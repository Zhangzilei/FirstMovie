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
