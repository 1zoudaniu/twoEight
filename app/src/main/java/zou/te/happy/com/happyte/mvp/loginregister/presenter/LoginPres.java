package zou.te.happy.com.happyte.mvp.loginregister.presenter;

import android.content.Context;

import java.util.Map;

import zou.te.happy.com.happyte.mvp.loginregister.model.LoginLoadDataListener;
import zou.te.happy.com.happyte.beanLists.LoginBean;
import zou.te.happy.com.happyte.mvp.loginregister.model.LoginModel;
import zou.te.happy.com.happyte.mvp.loginregister.view.LoginView;

/**
 * Created by Administrator on 2018/5/15.
 */

public class LoginPres implements LoginLoadDataListener<LoginBean> {

    private LoginView mView;
    private LoginModel mModel;
    private Context mContext;

    public LoginPres(LoginView mView, Context context) {
        this.mView = mView;
        this.mContext = context;
        this.mModel = new LoginModel();
    }

    /**
     * @param loOrReg 0:注册   1：登陆
     * @param testMap
     */
    public void loadData(int loOrReg, Map<String, Object> testMap) {
        mView.showProgress();
        if (loOrReg == 0) {//注册
            mModel.loadRegisterData(testMap, this);
        } else {//登陆
            mModel.loadLoginData(testMap, this);
        }
    }

    @Override
    public void onSuccess(int loOrReg, LoginBean data) {
        mView.newDatas(loOrReg, data);
        mView.hideProgress();
    }

    @Override
    public void onFailure(int loOrReg, Throwable e) {
        mView.showLoadFailMsg(loOrReg, e.getMessage());
        mView.hideProgress();
    }
}
