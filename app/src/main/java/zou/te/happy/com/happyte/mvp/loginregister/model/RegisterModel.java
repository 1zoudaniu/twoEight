package zou.te.happy.com.happyte.mvp.loginregister.model;

import java.util.Map;

import rx.Observer;
import zou.te.happy.com.happyte.beanLists.BaseBean;
import zou.te.happy.com.happyte.beanLists.LoginBean;
import zou.te.happy.com.happyte.beanLists.YzmBean;
import zou.te.happy.com.happyte.netdata.httpdata.HttpData;
import zou.te.happy.com.happyte.utils.MyLog;

/**
 * Created by Administrator on 2018/5/16.
 */

public class RegisterModel {
    /**
     * 注册的验证码
     *
     * @param testMap
     * @param listener
     */
    public void getRegisterYzm(Map<String, Object> testMap, final LoginLoadDataListener listener) {
        HttpData.getInstance().getRegisterYzm(testMap, new Observer<YzmBean>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                MyLog.d("忘记密码验证码-" + e.toString());
                listener.onFailure(3, e);
            }

            @Override
            public void onNext(YzmBean testBean) {
                MyLog.d("忘记密码验证码" + testBean.toString());
                listener.onSuccess(3, testBean);
            }
        });
    }

    /**
     * 注册
     *
     * @param testMap
     * @param listener
     */
    public void loadRegisterData(Map<String, Object> testMap, final LoginLoadDataListener listener) {
        HttpData.getInstance().getRegister(testMap, new Observer<BaseBean>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                MyLog.d("更新密码-" + e.toString());
                listener.onFailure(4, e);
            }

            @Override
            public void onNext(BaseBean testBean) {
                MyLog.d("更新密码" + testBean.toString());
                listener.onSuccess(4, testBean);
            }
        });
    }

    /**
     * 登陆
     *
     * @param testMap
     * @param listener
     */
    public void loadLoginData(Map<String, Object> testMap, final LoginLoadDataListener listener) {
        HttpData.getInstance().getLogin(testMap, new Observer<LoginBean>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                MyLog.d("登陆失败-" + e.toString());
                listener.onFailure(5, e);
            }

            @Override
            public void onNext(LoginBean testBean) {
                MyLog.d("登陆成功" + testBean.toString());
                listener.onSuccess(5, testBean);
            }
        });
    }

}
