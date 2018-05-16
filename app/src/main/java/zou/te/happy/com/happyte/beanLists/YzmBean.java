package zou.te.happy.com.happyte.beanLists;

/**
 * Created by Administrator on 2018/5/15.
 * 验证码
 */

public class YzmBean {

    /**
     * smsCode : 7846
     */

    private String smsCode;

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    @Override
    public String toString() {
        return "{" +
                "smsCode:" + smsCode +
                '}';
    }
}
