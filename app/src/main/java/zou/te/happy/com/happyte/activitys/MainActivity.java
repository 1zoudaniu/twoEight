package zou.te.happy.com.happyte.activitys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import zou.te.happy.com.happyte.R;
import zou.te.happy.com.happyte.base.BaseActivity;
import zou.te.happy.com.happyte.base.FragmentTabCreate;
import zou.te.happy.com.happyte.utils.StatusBarUtil;
import zou.te.happy.com.happyte.utils.widgetUtils.CustomViewPager;

public class MainActivity extends BaseActivity {

    private RadioGroup mainRlTop;
    private RadioButton mainRbHome;
    private RadioButton mainRbContent;
    private RadioButton mainRbMy;
    private CustomViewPager mainVp;
    private Fragment[] fragments;
    private MyFragmentAdapter myFragmentAdapter;
    private FragmentTransaction mTransaction;

    @Override
    public void initData(@Nullable Bundle bundle) {
    }

    @Override
    public int bindLayout() {
        //隐藏标题栏
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        StatusBarUtil.transparencyBar(this);
        return R.layout.activity_main;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        mainRlTop = (RadioGroup) findViewById(R.id.main_rl_top);
        mainRbHome = (RadioButton) findViewById(R.id.main_rb_home);
        mainRbContent = (RadioButton) findViewById(R.id.main_rb_content);
        mainRbMy = (RadioButton) findViewById(R.id.main_rb_my);
        mainVp = (CustomViewPager) findViewById(R.id.main_vp);

        fragments = FragmentTabCreate.getFragments("from");
        if (myFragmentAdapter == null) {
            myFragmentAdapter = new MyFragmentAdapter(getSupportFragmentManager());
        }
        mainVp.setAdapter(myFragmentAdapter);
        mainVp.setCurrentItem(0);
        mainVp.setOffscreenPageLimit(3);

        mainRbHome.setOnClickListener(this);
        mainRbContent.setOnClickListener(this);
        mainRbMy.setOnClickListener(this);

        mTransaction = getSupportFragmentManager().beginTransaction();
    }

    @Override
    public void doBusiness() {

    }

    @Override
    public void onWidgetClick(View v) {
        if (v == mainRbHome) {
            mainVp.setCurrentItem(0);
            if (fragments != null) {
                getSupportFragmentManager().beginTransaction().hide(fragments[1]);
                getSupportFragmentManager().beginTransaction().hide(fragments[2]);
                getSupportFragmentManager().beginTransaction().show(fragments[0]).commit();
            }
        } else if (v == mainRbContent) {
            mainVp.setCurrentItem(1);
            if (fragments != null) {
                getSupportFragmentManager().beginTransaction().hide(fragments[0]);
                getSupportFragmentManager().beginTransaction().hide(fragments[2]);
                getSupportFragmentManager().beginTransaction().show(fragments[1]).commit();
            }
        } else if (v == mainRbMy) {
            mainVp.setCurrentItem(2);
            if (fragments != null) {
                getSupportFragmentManager().beginTransaction().hide(fragments[0]);
                getSupportFragmentManager().beginTransaction().hide(fragments[1]);
                getSupportFragmentManager().beginTransaction().show(fragments[2]).commit();
            }
        }
    }

    class MyFragmentAdapter extends FragmentPagerAdapter {

        public MyFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return fragments[i];
        }

        @Override
        public int getCount() {
            return fragments.length == 0 ? 0 : fragments.length;
        }
    }

}
