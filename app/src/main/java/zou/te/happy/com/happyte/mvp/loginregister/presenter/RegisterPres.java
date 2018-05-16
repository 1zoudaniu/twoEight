package zou.te.happy.com.happyte.mvp.loginregister.presenter;

import android.content.Context;

import java.util.Map;

import zou.te.happy.com.happyte.mvp.loginregister.model.LoginLoadDataListener;
import zou.te.happy.com.happyte.mvp.loginregister.model.RegisterModel;
import zou.te.happy.com.happyte.mvp.loginregister.view.RegisterView;

/**
 * Created by Administrator on 2018/5/16.
 * 注册
 */

public class RegisterPres implements LoginLoadDataListener<Object> {

    private RegisterView mView;
    private RegisterModel mModel;
    private Context mContext;

    public RegisterPres(RegisterView mView, Context context) {
        this.mView = mView;
        this.mContext = context;
        this.mModel = new RegisterModel();
    }

    /**
     * @param loOrReg 3:验证码   4：注册   5：登陆
     * @param testMap
     */
    public void sendregisterYzm(int loOrReg, Map<String, Object> testMap) {
        mView.showProgress();
        if (loOrReg == 3) {//验证码
            mModel.getRegisterYzm(testMap, this);
        } else if (loOrReg == 4) {//注册
            mModel.loadRegisterData(testMap, this);
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
