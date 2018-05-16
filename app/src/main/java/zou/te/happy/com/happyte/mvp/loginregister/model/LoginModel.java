package zou.te.happy.com.happyte.mvp.loginregister.model;

import java.util.Map;

import rx.Observer;
import zou.te.happy.com.happyte.beanLists.BaseBean;
import zou.te.happy.com.happyte.beanLists.LoginBean;
import zou.te.happy.com.happyte.netdata.httpdata.HttpData;
import zou.te.happy.com.happyte.utils.MyLog;

/**
 * Created by Administrator on 2018/5/15.
 *
 */

public class LoginModel {

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
                listener.onFailure(1, e);
            }

            @Override
            public void onNext(LoginBean testBean) {
                MyLog.d("登陆成功" + testBean.toString());
                listener.onSuccess(1, testBean);
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
                MyLog.d("注册失败-" + e.toString());
                listener.onFailure(0, e);
            }

            @Override
            public void onNext(BaseBean testBean) {
                MyLog.d("注册成功" + testBean.toString());
                listener.onSuccess(0, testBean);
            }
        });
    }


}
