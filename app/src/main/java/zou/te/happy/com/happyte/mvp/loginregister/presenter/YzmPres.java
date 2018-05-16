package zou.te.happy.com.happyte.mvp.loginregister.presenter;

import android.content.Context;

import java.util.Map;

import zou.te.happy.com.happyte.beanLists.LoginBean;
import zou.te.happy.com.happyte.mvp.loginregister.model.LoginLoadDataListener;
import zou.te.happy.com.happyte.mvp.loginregister.model.YzmModle;
import zou.te.happy.com.happyte.mvp.loginregister.view.LoginView;

/**
 * Created by Administrator on 2018/5/15.
 * 登陆  忘记密码
 */

public class YzmPres implements LoginLoadDataListener<Object> {

    private LoginView mView;
    private YzmModle mModel;
    private Context mContext;

    public YzmPres(LoginView mView, Context context) {
        this.mView = mView;
        this.mContext = context;
        this.mModel = new YzmModle();
    }

    /**
     * @param loOrReg 3:验证码   4：更新密码   5：登陆
     * @param testMap
     */
    public void sendForgetYzm(int loOrReg, Map<String, Object> testMap) {
        mView.showProgress();
        if (loOrReg == 3) {//验证码
            mModel.loadYzmData(testMap, this);
        } else if (loOrReg == 4) {//更新密码
            mModel.loadUpdatePassData(testMap, this);
        } else if (loOrReg == 5) {//登陆
            mModel.loadLoginData(testMap, this);
        }
    }

    @Override
    public void onSuccess(int loOrReg, Object data) {
        mView.newDatas(loOrReg, data);
        mView.hideProgress();
    }

    @Override
    public void onFailure(int loOrReg, Throwable e) {
        mView.showLoadFailMsg(loOrReg, e.getMessage());
        mView.hideProgress();
    }
}
