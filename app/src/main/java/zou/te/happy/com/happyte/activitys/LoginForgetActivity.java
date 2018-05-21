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
import zou.te.happy.com.happyte.base.BaseActivity;
import zou.te.happy.com.happyte.beanLists.LoginBean;
import zou.te.happy.com.happyte.beanLists.YzmBean;
import zou.te.happy.com.happyte.constants.Constant;
import zou.te.happy.com.happyte.constants.URLConstant;
import zou.te.happy.com.happyte.dialogs.LoadingDialogUtil;
import zou.te.happy.com.happyte.mvp.loginregister.presenter.YzmPres;
import zou.te.happy.com.happyte.mvp.loginregister.view.LoginView;
import zou.te.happy.com.happyte.utils.MyLog;
import zou.te.happy.com.happyte.utils.SPUtil;
import zou.te.happy.com.happyte.utils.StatusBarUtil;
import zou.te.happy.com.happyte.utils.ToastUtil;

/**
 * 忘记密码
 */
public class LoginForgetActivity extends BaseActivity implements LoginView {

    private EditText forgetEditTel;
    private EditText forgetEditYzm;
    private TextView forgetTextGetYzm;
    private EditText forgetEditPassword;
    private Button forgetBtnLogin;
    private RelativeLayout forgetRlBack;

    private YzmPres yzmPres;
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
        return R.layout.activity_login_forget;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        forgetEditTel = (EditText) findViewById(R.id.forget_edit_tel);
        forgetEditYzm = (EditText) findViewById(R.id.forget_edit_yzm);
        forgetTextGetYzm = (TextView) findViewById(R.id.forget_text_getYzm);
        forgetEditPassword = (EditText) findViewById(R.id.forget_edit_password);
        forgetBtnLogin = (Button) findViewById(R.id.forget_btn_login);
        forgetRlBack = (RelativeLayout) findViewById(R.id.forget_rl_back);


        forgetBtnLogin.setOnClickListener(this);
        forgetTextGetYzm.setOnClickListener(this);
        forgetRlBack.setOnClickListener(this);

        yzmPres = new YzmPres(this, this);
    }

    @Override
    public void doBusiness() {

    }

    @Override
    public void onWidgetClick(View view) {
        if (view == forgetBtnLogin) {//登陆
            if (TextUtils.isEmpty(forgetEditTel.getText().toString().trim())) {
                ToastUtil.showShortToast(this, "电话号码不能为空");
                return;
            }
            if (!URLConstant.isPhoneNumber(forgetEditTel.getText().toString().trim())) {
                ToastUtil.showShortToast(this, "电话号码格式错误");
                return;
            }
            if (TextUtils.isEmpty(forgetEditYzm.getText().toString().trim())) {
                ToastUtil.showShortToast(this, "验证码不能为空");
                return;
            }
            if (forgetEditYzm.getText().toString().trim().length() != 4) {
                ToastUtil.showShortToast(this, "验证码错误");
                return;
            }
            if (!forgetEditYzm.getText().toString().trim().equals(yzmString)) {
                ToastUtil.showShortToast(this, "验证码错误");
                return;
            }
            if (TextUtils.isEmpty(forgetEditPassword.getText().toString().trim())) {
                ToastUtil.showShortToast(this, "密码不能为空");
                return;
            }
            if (forgetEditPassword.getText().toString().trim().length() < 6 || forgetEditPassword.getText().toString().trim().length() > 18) {
                ToastUtil.showShortToast(this, "用户名或者密码错误");
                return;
            }
            Map<String, Object> updateMap = new HashMap<>();
            updateMap.put("version", Constant.P_VERSION);
            updateMap.put("deviceId", "121");
            updateMap.put("mobile", forgetEditTel.getText());
            updateMap.put("password", forgetEditPassword.getText().toString().toString());
            updateMap.put("confirmPassword", forgetEditPassword.getText().toString().toString());
            updateMap.put("verifyCode", yzmString);
            updateMap.put("sign", URLConstant.md5(URLConstant.sign(updateMap)));
            MyLog.d("更改密码参数" + updateMap.toString());
            yzmPres.sendForgetYzm(4, updateMap);
        } else if (view == forgetTextGetYzm) {//获取验证码
            if (isFastClick()) {
                if (TextUtils.isEmpty(forgetEditTel.getText().toString().trim())) {
                    ToastUtil.showShortToast(this, "电话号码不能为空");
                    return;
                }
                if (!URLConstant.isPhoneNumber(forgetEditTel.getText().toString().trim())) {
                    ToastUtil.showShortToast(this, "电话号码格式错误");
                    return;
                }
                countDownTimer = new MyCountDownTimer(60000, 1000);
                Map<String, Object> map = new HashMap<>();
                map.put("version", Constant.P_VERSION);
                map.put("deviceId", "121");
                map.put("mobile", forgetEditTel.getText());
                map.put("sign", URLConstant.md5(URLConstant.sign(map)));
                yzmPres.sendForgetYzm(3, map);
            }

        } else if (view==forgetRlBack) {
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

    //     3:验证码   4：更新密码   5：登陆
    @Override
    public void newDatas(int loOrReg, Object data) {
        if (loOrReg == 3) {//验证码
            ToastUtil.showShortToast(this, "验证码已发送");
            countDownTimer.start();
            yzmString = ((YzmBean) data).getSmsCode();
        } else if (loOrReg == 4) {//更新密码
            ToastUtil.showShortToast(this, "密码已重置");
            Map<String, Object> loginMap = new HashMap<>();
            loginMap.put("version", Constant.P_VERSION);
            loginMap.put("userName", forgetEditTel.getText().toString().trim());
            loginMap.put("password", forgetEditPassword.getText().toString().trim());
            loginMap.put("deviceId", "121");
            loginMap.put("sign", URLConstant.md5(URLConstant.sign(loginMap)));
            MyLog.d("登陆参数" + loginMap.toString());
            yzmPres.sendForgetYzm(5, loginMap);
        } else if (loOrReg == 5) {//登陆
            ToastUtil.showShortToast(this, "登陆成功");
            if (null != countDownTimer) {
                countDownTimer.cancel();
                countDownTimer.onFinish();
                countDownTimer = null;
            }
            SPUtil.getInstance().putString(Constant.U_USERINFO, ((LoginBean) data).toString());
            Intent intent = new Intent(LoginForgetActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    //     3:验证码   4：更新密码   5：登陆
    @Override
    public void showLoadFailMsg(int loOrReg, String tTostring) {
        if (loOrReg == 3) {//验证码
            ToastUtil.showShortToast(this, tTostring);
        } else if (loOrReg == 4) {//更新密码
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
            forgetTextGetYzm.setClickable(false);
            forgetTextGetYzm.setText(l / 1000 + "s");
        }

        //计时完毕的方法
        @Override
        public void onFinish() {
            //重新给Button设置文字
            forgetTextGetYzm.setText("获取验证码");
            //设置可点击
            forgetTextGetYzm.setClickable(true);
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
