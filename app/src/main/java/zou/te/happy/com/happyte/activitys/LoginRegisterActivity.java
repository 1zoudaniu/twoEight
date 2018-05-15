package zou.te.happy.com.happyte.activitys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;

import zou.te.happy.com.happyte.R;
import zou.te.happy.com.happyte.base.BaseActivity;
import zou.te.happy.com.happyte.utils.StatusBarUtil;

public class LoginRegisterActivity extends BaseActivity {

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

    }

    @Override
    public void doBusiness() {

    }

    @Override
    public void onWidgetClick(View view) {

    }

}
