package zou.te.happy.com.happyte.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import zou.te.happy.com.happyte.R;
import zou.te.happy.com.happyte.app.AppManager;
import zou.te.happy.com.happyte.base.BaseActivity;
import zou.te.happy.com.happyte.beanLists.LoginBean;
import zou.te.happy.com.happyte.beanLists.YzmBean;
import zou.te.happy.com.happyte.constants.Constant;
import zou.te.happy.com.happyte.constants.URLConstant;
import zou.te.happy.com.happyte.dialogs.LoadingDialogUtil;
import zou.te.happy.com.happyte.mvp.loginregister.presenter.RegisterPres;
import zou.te.happy.com.happyte.mvp.loginregister.presenter.YzmPres;
import zou.te.happy.com.happyte.mvp.loginregister.view.RegisterView;
import zou.te.happy.com.happyte.utils.MyLog;
import zou.te.happy.com.happyte.utils.SPUtil;
import zou.te.happy.com.happyte.utils.StatusBarUtil;
import zou.te.happy.com.happyte.utils.ToastUtil;

/**
 * 注册
 */
public class LoginRegisterActivity extends BaseActivity implements RegisterView {

    private EditText registerEditTel;
    private EditText registerEditYzm;
    private TextView registerTextGetYzm;
    private EditText registerEditPassword;
    private Button registerBtnLogin;
    private RelativeLayout registerRlBack;
    private TextView registerTitle;

    private RegisterPres registerPres;
    private LoadingDialogUtil mLoadingDialog;

    private MyCountDownTimer countDownTimer;
    private String yzmString;//验证码

    @Override
    public void initData(@Nullable Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        //隐藏标题栏
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        StatusBarUtil.transparencyBar(this);
        return R.layout.activity_login_register;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        registerEditTel = (EditText) findViewById(R.id.register_edit_tel);
        registerEditYzm = (EditText) findViewById(R.id.register_edit_yzm);
        registerTextGetYzm = (TextView) findViewById(R.id.register_text_getYzm);
        registerEditPassword = (EditText) findViewById(R.id.register_edit_password);
        registerBtnLogin = (Button) findViewById(R.id.register_btn_login);
        registerRlBack = (RelativeLayout) findViewById(R.id.layout_head_title_rl_back);
        registerTitle = (TextView) findViewById(R.id.layout_head_title_tv_title);

        registerTitle.setText("新用户注册");
        registerBtnLogin.setOnClickListener(this);
        registerTextGetYzm.setOnClickListener(this);
        registerRlBack.setOnClickListener(this);

        registerPres = new RegisterPres(this, this);
    }

    @Override
    public void doBusiness() {

    }

    @Override
    public void onWidgetClick(View view) {
        if (view == registerBtnLogin) {//登陆
            if (TextUtils.isEmpty(registerEditTel.getText().toString().trim())) {
                ToastUtil.showShortToast(this, "电话号码不能为空");
                return;
            }
            if (!URLConstant.isPhoneNumber(registerEditTel.getText().toString().trim())) {
                ToastUtil.showShortToast(this, "电话号码格式错误");
                return;
            }
            if (TextUtils.isEmpty(registerEditYzm.getText().toString().trim())) {
                ToastUtil.showShortToast(this, "验证码不能为空");
                return;
            }
            if (registerEditYzm.getText().toString().trim().length() != 4) {
                ToastUtil.showShortToast(this, "验证码错误");
                return;
            }
            if (!registerEditYzm.getText().toString().trim().equals(yzmString)) {
                ToastUtil.showShortToast(this, "验证码错误");
                return;
            }
            if (TextUtils.isEmpty(registerEditPassword.getText().toString().trim())) {
                ToastUtil.showShortToast(this, "密码不能为空");
                return;
            }
            if (registerEditPassword.getText().toString().trim().length() < 6 || registerEditPassword.getText().toString().trim().length() > 18) {
                ToastUtil.showShortToast(this, "用户名或者密码错误");
                return;
            }
            Map<String, Object> registerMp = new HashMap<>();
            registerMp.put("version", Constant.P_VERSION);
            registerMp.put("deviceId", "121");
            registerMp.put("mobile", registerEditTel.getText().toString());
            registerMp.put("password", registerEditPassword.getText().toString().trim());
            registerMp.put("confirmPassword", registerEditPassword.getText().toString().trim());
            registerMp.put("verifyCode", yzmString);
            registerMp.put("sourceType", "0");
            registerMp.put("sign", URLConstant.md5(URLConstant.sign(registerMp)));
            MyLog.d("注册参数" + registerMp.toString());
            registerPres.sendregisterYzm(4, registerMp);
        } else if (view == registerTextGetYzm) {//获取验证码
            if (isFastClick()) {
                if (TextUtils.isEmpty(registerEditTel.getText().toString().trim())) {
                    ToastUtil.showShortToast(this, "电话号码不能为空");
                    return;
                }
                if (!URLConstant.isPhoneNumber(registerEditTel.getText().toString().trim())) {
                    ToastUtil.showShortToast(this, "电话号码格式错误");
                    return;
                }
                countDownTimer = new MyCountDownTimer(60000, 1000);
                Map<String, Object> map = new HashMap<>();
                map.put("version", Constant.P_VERSION);
                map.put("deviceId", "121");
                map.put("mobile", registerEditTel.getText());
                map.put("sign", URLConstant.md5(URLConstant.sign(map)));
                registerPres.sendregisterYzm(3, map);
            }
        } else if (view == registerRlBack) {
            if (null != countDownTimer) {
                countDownTimer.cancel();
                countDownTimer.onFinish();
                countDownTimer = null;
            }
            finish();
        }
    }

    @Override
    public void showProgress() {
        LoadingDialogUtil.cancelLoadingDialog();
        LoadingDialogUtil.showLoadingDialog(this, 1);
    }

    @Override
    public void hideProgress() {
        LoadingDialogUtil.cancelLoadingDialog();
    }

    @Override
    public void newDatas(int loOrReg, Object data) {
        if (loOrReg == 3) {//验证码
            ToastUtil.showShortToast(this, "验证码已发送");
            countDownTimer.start();
            yzmString = ((YzmBean) data).getSmsCode();
        } else if (loOrReg == 4) {//注册
            ToastUtil.showShortToast(this, "密码已重置");
            Map<String, Object> loginMap = new HashMap<>();
            loginMap.put("version", Constant.P_VERSION);
            loginMap.put("userName", registerEditTel.getText().toString().trim());
            loginMap.put("password", registerEditPassword.getText().toString().trim());
            loginMap.put("deviceId", "121");
            loginMap.put("sign", URLConstant.md5(URLConstant.sign(loginMap)));
            MyLog.d("登陆参数" + loginMap.toString());
            registerPres.sendregisterYzm(5, loginMap);
        } else if (loOrReg == 5) {//登陆
            ToastUtil.showShortToast(this, "登陆成功");
            if (null != countDownTimer) {
                countDownTimer.cancel();
                countDownTimer.onFinish();
                countDownTimer = null;
            }
            SPUtil.getInstance().putString(Constant.U_USERINFO, ((LoginBean) data).toString());
            Intent intent = new Intent(LoginRegisterActivity.this, MainActivity.class);
            startActivity(intent);
            AppManager.finishAllActivity();
        }
    }

    @Override
    public void showLoadFailMsg(int loOrReg, String tTostring) {
        if (loOrReg == 3) {//验证码
            ToastUtil.showShortToast(this, tTostring);
        } else if (loOrReg == 4) {//注册
            ToastUtil.showShortToast(this, tTostring);
        } else if (loOrReg == 5) {//登陆
            ToastUtil.showShortToast(this, tTostring);
        }
    }

    class MyCountDownTimer extends CountDownTimer {

        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        //计时过程
        @Override
        public void onTick(long l) {
            //防止计时过程中重复点击
            registerTextGetYzm.setClickable(false);
            registerTextGetYzm.setText(l / 1000 + "s");
        }

        //计时完毕的方法
        @Override
        public void onFinish() {
            //重新给Button设置文字
            registerTextGetYzm.setText("获取验证码");
            //设置可点击
            registerTextGetYzm.setClickable(true);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != countDownTimer) {
            countDownTimer.cancel();
            countDownTimer.onFinish();
            countDownTimer = null;
        }

    }
}
