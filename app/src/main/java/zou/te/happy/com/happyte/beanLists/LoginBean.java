package zou.te.happy.com.happyte.beanLists;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/5/15.
 * 登陆接口
 */

public class LoginBean implements Serializable{

    /**
     * user : {"userId":524}
     * token : ddb35fab-9d38-4986-9f1f-f93ba47ff273
     */

    private UserBean user;
    private String token;

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static class UserBean {
        /**
         * userId : 524
         */

        private int userId;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        @Override
        public String toString() {
            return "{" +
                    "userId:" + userId +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "{" +
                "user:" + user +
                ", token:" + token  +
                '}';
    }
}
