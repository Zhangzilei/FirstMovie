package com.bw.movie.model.bean;

import java.util.List;

/**
 * date:19/11/8
 * author:张自磊(lenovo)
 * function:
 */
public class CTuiJianBean {

    public String message;
    public String status;
    public List<ResultBean> result;

    public static class ResultBean {
        /**
         * address : 湖景东路11号新奥购物中心B1(东A口)
         * commentTotal : 0
         * distance : 0
         * followCinema : 2
         * id : 5
         * logo : http://mobile.bwstudent.com/images/movie/logo/CGVxx.jpg
         * name : CGV星星影城
         */

        public String address;
        public int commentTotal;
        public int distance;
        public int followCinema;
        public int id;
        public String logo;
        public String name;
    }
}
