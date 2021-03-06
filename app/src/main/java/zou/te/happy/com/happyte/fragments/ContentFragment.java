package zou.te.happy.com.happyte.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import zou.te.happy.com.happyte.R;
import zou.te.happy.com.happyte.base.BaseLazyFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContentFragment extends BaseLazyFragment {

    private String mFrom;

    public ContentFragment() {
        // Required empty public constructor
    }

    public static ContentFragment newInstance(String param) {
        ContentFragment fragment = new ContentFragment();
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
        return R.layout.fragment_content;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {

    }

    @Override
    public void onWidgetClick(View view) {

    }
}
