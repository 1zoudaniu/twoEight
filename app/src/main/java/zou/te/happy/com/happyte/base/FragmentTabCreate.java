package zou.te.happy.com.happyte.base;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import zou.te.happy.com.happyte.R;
import zou.te.happy.com.happyte.fragments.ContentFragment;
import zou.te.happy.com.happyte.fragments.HomeFragment;
import zou.te.happy.com.happyte.fragments.MyFragment;

/**
 * Created by Administrator on 2018/5/8.
 * 主界面tab fragment  创建
 */

public class FragmentTabCreate {

    public static final int []mTabRes = new int[]{R.mipmap.icon_home,R.mipmap.icon_content,R.mipmap.icon_my};
    public static final int []mTabResPressed = new int[]{R.mipmap.icon_home_check,R.mipmap.icon_content_check,R.mipmap.icon_my_check};
    public static final String []mTabTitle = new String[]{"首页","内容","我的"};

    public static Fragment[] getFragments(String from){
        Fragment fragments[] = new Fragment[3];
        fragments[0] = HomeFragment.newInstance(from);
        fragments[1] = ContentFragment.newInstance(from);
        fragments[2] = MyFragment.newInstance(from);
        return fragments;
    }

    /**
     * 获取Tab 显示的内容
     * @param context
     * @param position
     * @return
     */
    public static View getTabView(Context context, int position){
        View view = LayoutInflater.from(context).inflate(R.layout.home_tab_content,null);
        ImageView tabIcon = (ImageView) view.findViewById(R.id.tab_content_image);
        tabIcon.setImageResource(FragmentTabCreate.mTabRes[position]);
        TextView tabText = (TextView) view.findViewById(R.id.tab_content_text);
        tabText.setText(mTabTitle[position]);
        return view;
    }
}
