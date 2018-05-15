package zou.te.happy.com.happyte.base;

import android.util.Log;

/**
 * Created by Administrator on 2018/5/8.
 */

public abstract  class BaseLazyFragment extends BaseFragment {

    private static final String TAG = "BaseLazyFragment";

    private boolean isDataLoaded;

    public abstract void doLazyBusiness();

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        Log.d(TAG, "setUserVisibleHint: " + isVisibleToUser);
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && mContentView != null && !isDataLoaded) {
            doLazyBusiness();
            isDataLoaded = true;
        }
    }

    @Override
    public void doBusiness() {
        if (getUserVisibleHint()) {
            doLazyBusiness();
            isDataLoaded = true;
        }
    }
}
