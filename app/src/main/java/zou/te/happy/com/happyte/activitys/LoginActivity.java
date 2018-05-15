package zou.te.happy.com.happyte.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import zou.te.happy.com.happyte.R;
import zou.te.happy.com.happyte.base.BaseActivity;
import zou.te.happy.com.happyte.utils.StatusBarUtil;
import zou.te.happy.com.happyte.utils.widgetUtils.NoScrollViewPager;

/**
 * 登陆前夜   不作逻辑处理
 */

public class LoginActivity extends BaseActivity {

    private NoScrollViewPager vpLoginViewpager;
    private List<ImageView> banners = new ArrayList<ImageView>();
    private Button btnLoginregister;
    private Button btnLoginexp;
    private TextView btnLoginText;
    private String[] guideText = new String[]{"妈了个巴子，管求你妈卖批的",
            "乌漆嘛黑的，怎么才能看清楚啊", "三天两头的来这里看这个，合适吗老铁？"};

    int msgWhat = 0;
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            int currentItem = vpLoginViewpager.getCurrentItem() + 1;
            vpLoginViewpager.setCurrentItem(currentItem);//收到消息，指向下一个页面
            btnLoginText.setText(guideText[currentItem % 3]);
            handler.sendEmptyMessageDelayed(msgWhat, 2000);//2S后在发送一条消息，由于在handleMessage()方法中，造成死循环。
        }
    };

    @Override
    public void initData(@Nullable Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        //隐藏标题栏
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        StatusBarUtil.transparencyBar(this);
        return R.layout.activity_login;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        vpLoginViewpager = (NoScrollViewPager) findViewById(R.id.vp_login_viewpager);
        btnLoginregister = (Button) findViewById(R.id.btn_loginregister);
        btnLoginexp = (Button) findViewById(R.id.btn_loginexp);
        btnLoginText = (TextView) findViewById(R.id.btn_login_text);

        banners = new ArrayList<>();
        ImageView img1 = new ImageView(this);
        img1.setImageResource(R.drawable.login_banner_one);
        img1.setScaleType(ImageView.ScaleType.FIT_XY);
        banners.add(img1);
        ImageView img2 = new ImageView(this);
        img2.setImageResource(R.drawable.login_banner_two);
        img2.setScaleType(ImageView.ScaleType.FIT_XY);
        banners.add(img2);
        ImageView img3 = new ImageView(this);
        img3.setImageResource(R.drawable.login_banner_three);
        img3.setScaleType(ImageView.ScaleType.FIT_XY);
        banners.add(img3);

        vpLoginViewpager.setAdapter(new MyAdapter());
        vpLoginViewpager.setCurrentItem(0);

        btnLoginregister.setOnClickListener(this);
        btnLoginexp.setOnClickListener(this);
    }

    @Override
    public void doBusiness() {

    }

    @Override
    public void onWidgetClick(View view) {
        switch (view.getId()) {
            case R.id.btn_loginregister:
                Intent intentLogin = new Intent(LoginActivity.this, LoginRegisterActivity.class);
                startActivity(intentLogin);
                handler.removeMessages(msgWhat);
                finish();
                break;
            case R.id.btn_loginexp:
                Intent intentExp = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intentExp);
                handler.removeMessages(msgWhat);
                finish();
                break;
        }
    }

    public class MyAdapter extends PagerAdapter {
        //表示viewpager共存放了多少个页面
        @Override
        public int getCount() {
            return Integer.MAX_VALUE;//我们设置viewpager中有Integer.MAX_VALUE个页面
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        /**
         * position % imageList.size() 而不是position，是为了防止角标越界异常
         * 因为我们设置了viewpager子页面的数量有Integer.MAX_VALUE，而imageList的数量只是5。
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(banners.get(position % banners.size()));
            return banners.get(position % banners.size());
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.sendEmptyMessageDelayed(msgWhat, 2000);
    }

    @Override
    protected void onStop() {
        super.onStop();
        handler.removeMessages(msgWhat);
    }

//    public void showLogin() {
//        mShowLoginDialog = new Dialog(this, R.style.HelpDialogAll);
//        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.dialog_loginregister, null);
//        mShowLoginDialog.setContentView(layout);
//        mShowLoginDialog.findViewById(R.id.dialog_login_no_login).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//            }
//        });
//        mShowLoginDialog.findViewById(R.id.dialog_login_login).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
//
//        Window w = mShowLoginDialog.getWindow();
//        WindowManager.LayoutParams lp = w.getAttributes();
//        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
//        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
//        w.setAttributes(lp);
//        w.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        mShowLoginDialog.setCancelable(false);
//        mShowLoginDialog.setCanceledOnTouchOutside(false);
//        mShowLoginDialog.show();
//    }

}
