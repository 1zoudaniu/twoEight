package zou.te.happy.com.happyte.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import zou.te.happy.com.happyte.R;
import zou.te.happy.com.happyte.base.BaseLazyFragment;

/**
 * create an instance of this fragment.
 */
public class HomeFragment extends BaseLazyFragment {

    private String mFrom;

    public HomeFragment() {
    }

    public static HomeFragment newInstance(String param) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString("from", param);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mFrom = getArguments().getString("from");
        }
    }

    @Override
    public void doLazyBusiness() {

    }

    @Override
    public void initData(@Nullable Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {

    }

    @Override
    public void onWidgetClick(View view) {

    }
}
