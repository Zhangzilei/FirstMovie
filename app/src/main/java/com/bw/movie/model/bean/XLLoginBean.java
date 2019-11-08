package com.bw.movie.model.bean;

/**
 * <p>文件描述：登录<p>
 * <p>作者：吴新仲<p>
 * <p>创建时间：2019/11/6/006<p>
 * <p>更改时间：2019/11/6/006<p>
 */
public class XLLoginBean {

    public ResultBean result;
    public String message;
    public String status;

    public static class ResultBean {

        public String sessionId;
        public int userId;
        public UserInfoBean userInfo;

        public static class UserInfoBean {

            public String email;
            public String headPic;
            public int id;
            public long lastLoginTime;
            public String nickName;
            public int sex;
        }
    }
}
