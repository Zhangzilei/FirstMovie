package com.bw.movie.model.bean;

import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：张自磊<p>
 * <p>创建时间：2019/11/13<p>
 * <p>更改时间：2019/11/13<p>
 */
public class XiaoXiBean {

    /**
     * result : [{"content":"感谢您注册维度账号,小维希望您使用我们的产品能够获得快乐~","id":5135,"pushTime":1573040375000,"status":0,"title":"系统通知","userId":13766}]
     * message : 查询成功
     * status : 0000
     */

    public String message;
    public String status;
    public List<ResultBean> result;

    public static class ResultBean {
        /**
         * content : 感谢您注册维度账号,小维希望您使用我们的产品能够获得快乐~
         * id : 5135
         * pushTime : 1573040375000
         * status : 0
         * title : 系统通知
         * userId : 13766
         */

        public String content;
        public int id;
        public long pushTime;
        public int status;
        public String title;
        public int userId;
    }
}
