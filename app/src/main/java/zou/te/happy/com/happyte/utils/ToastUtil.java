package zou.te.happy.com.happyte.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import zou.te.happy.com.happyte.constants.URLConstant;

/**
 * Created by Administrator on 2018/5/8.
 */

public class ToastUtil {

    private static Toast toast;

    private static View view;

    private ToastUtil() {
        throw new AssertionError();
    }

    private static void getToast(Context context) {
        if (toast == null) {
            toast = new Toast(context);
        }
        if (view == null) {
            view = Toast.makeText(context, "", Toast.LENGTH_SHORT).getView();
        }
        toast.setView(view);
    }

    public static void showShortToast(Context context, CharSequence msg) {
        showToast(context.getApplicationContext(), msg, Toast.LENGTH_SHORT);
    }

    public static void showShortToast(Context context, int resId) {
        showToast(context.getApplicationContext(), resId, Toast.LENGTH_SHORT);
    }

    public static void showLongToast(Context context, CharSequence msg) {
        showToast(context.getApplicationContext(), msg, Toast.LENGTH_LONG);
    }

    public static void showLongToast(Context context, int resId) {
        showToast(context.getApplicationContext(), resId, Toast.LENGTH_LONG);
    }

    private static void showToast(Context context, CharSequence msg, int duration) {
        if ((msg + "").contains(URLConstant.API_URL)) {
            msg = "网络不可用,请检查网络";
        }
        try {
            getToast(context);
            toast.setText(msg);
            toast.setDuration(duration);
            toast.setGravity(Gravity.BOTTOM, 0, 80);
            toast.show();
        } catch (Exception e) {
        }
    }

    private static void showToast(Context context, int resId, int duration) {
        try {
            if (resId == 0) {
                return;
            }
            getToast(context);
            toast.setText(resId);
            toast.setDuration(duration);
            toast.setGravity(Gravity.CENTER, 0, 80);
            toast.show();
        } catch (Exception e) {
        }
    }

    public static void cancelToast() {
        if (toast != null) {
            toast.cancel();
        }
    }
}
