package com.bw.movie.model.bean;

import java.util.List;

/**
 * date:19/11/8
 * author:张自磊(lenovo)
 * function:
 */
public class CHaiLeftBean {

    public String message;
    public String status;
    public List<ResultBean> result;

    public static class ResultBean {

        public int regionId;
        public String regionName;
    }
}
