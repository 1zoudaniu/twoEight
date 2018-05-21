package zou.te.happy.com.happyte.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

import zou.te.happy.com.happyte.R;
import zou.te.happy.com.happyte.app.AppManager;
import zou.te.happy.com.happyte.base.BaseActivity;
import zou.te.happy.com.happyte.beanLists.LoginBean;
import zou.te.happy.com.happyte.constants.Constant;
import zou.te.happy.com.happyte.constants.URLConstant;
import zou.te.happy.com.happyte.dialogs.LoadingDialogUtil;
import zou.te.happy.com.happyte.mvp.loginregister.presenter.LoginPres;
import zou.te.happy.com.happyte.mvp.loginregister.view.LoginView;
import zou.te.happy.com.happyte.utils.MyLog;
import zou.te.happy.com.happyte.utils.SPUtil;
import zou.te.happy.com.happyte.utils.StatusBarUtil;
import zou.te.happy.com.happyte.utils.ToastUtil;

/**
 * 登陆
 */
public class LoginAllActivity extends BaseActivity implements LoginView {

    private EditText loginEditTel;
    private EditText loginEditPassword;
    private TextView loginTextForget;
    private Button loginBtnLogin;

    private LoginPres loginPres;
    private LoadingDialogUtil mLoadingDialog;
    private RelativeLayout btnLoginRlBack;

    @Override
    public void initData(@Nullable Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        //隐藏标题栏
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        StatusBarUtil.transparencyBar(this);
        return R.layout.activity_login_all;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        loginEditTel = (EditText) findViewById(R.id.login_edit_tel);
        loginEditPassword = (EditText) findViewById(R.id.login_edit_password);
        loginTextForget = (TextView) findViewById(R.id.login_text_forget);
        loginBtnLogin = (Button) findViewById(R.id.login_btn_login);
        btnLoginRlBack = (RelativeLayout) findViewById(R.id.login_rl_back);

        loginBtnLogin.setOnClickListener(this);
        loginTextForget.setOnClickListener(this);
        btnLoginRlBack.setOnClickListener(this);

        loginPres = new LoginPres(this, this);
    }

    @Override
    public void doBusiness() {

    }

    @Override
    public void onWidgetClick(View view) {
        if (view == loginBtnLogin) {//登陆
            if (TextUtils.isEmpty(loginEditTel.getText().toString().trim())) {
                ToastUtil.showShortToast(this, "电话号码不能为空");
                return;
            }
            if (!URLConstant.isPhoneNumber(loginEditTel.getText().toString().trim())) {
                ToastUtil.showShortToast(this, "电话号码格式错误");
                return;
            }
            if (TextUtils.isEmpty(loginEditPassword.getText().toString().trim())) {
                ToastUtil.showShortToast(this, "密码不能为空");
                return;
            }
            if (loginEditPassword.getText().toString().trim().length() < 6 || loginEditPassword.getText().toString().trim().length() > 18) {
                ToastUtil.showShortToast(this, "用户名或者密码错误");
                return;
            }
            Map<String, Object> loginMap = new HashMap<>();
            loginMap.put("version", Constant.P_VERSION);
            loginMap.put("userName", loginEditTel.getText().toString().trim());
            loginMap.put("password", loginEditPassword.getText().toString().trim());
            loginMap.put("deviceId", "121");
            loginMap.put("sign", URLConstant.md5(URLConstant.sign(loginMap)));
            MyLog.d("登陆参数" + loginMap.toString());
            loginPres.loadData(1, loginMap);
        } else if (view == loginTextForget) {//忘记密码
            Intent intentForget = new Intent(LoginAllActivity.this, LoginForgetActivity.class);
            startActivity(intentForget);
        } else if (view == btnLoginRlBack) {//返回
            finish();
        }
    }


    @Override
    public void showProgress() {
        LoadingDialogUtil.cancelLoadingDialog();
        LoadingDialogUtil.showLoadingDialog(this, 1, "正在登陆中-----");
    }

    @Override
    public void hideProgress() {
        LoadingDialogUtil.cancelLoadingDialog();
    }

    @Override
    public void newDatas(int loOrReg, Object data) {
        if (loOrReg == 1) {
            MyLog.d("用户信息" + data.toString());
            SPUtil.getInstance().putString(Constant.U_USERINFO, ((LoginBean) data).toString());
            Intent intent = new Intent(LoginAllActivity.this, MainActivity.class);
            startActivity(intent);
            AppManager.finishAllActivity();
        }
    }

    @Override
    public void showLoadFailMsg(int loOrReg, String tTostring) {
        if (loOrReg == 1) {
            ToastUtil.showShortToast(this, tTostring);
        }
    }
}
