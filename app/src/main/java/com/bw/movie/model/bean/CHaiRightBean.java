package com.bw.movie.model.bean;

import java.util.List;

/**
 * date:19/11/8
 * author:张自磊(lenovo)
 * function:
 */
public class CHaiRightBean {

    public String message;
    public String status;
    public List<ResultBean> result;

    public static class ResultBean {
        /**
         * id : 1
         * name : 青春光线电影院
         */

        public int id;
        public String name;
    }
}
