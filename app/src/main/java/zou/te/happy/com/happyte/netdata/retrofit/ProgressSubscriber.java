package zou.te.happy.com.happyte.netdata.retrofit;

import android.content.Context;
import android.widget.Toast;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import rx.Subscriber;

/**
 * Created by Administrator on 2018/2/26.
 * 用于在Http请求开始时，自动显示一个ProgressDialog
 * 在Http请求结束是，关闭ProgressDialog
 * 调用者自己对请求数据进行处理
 * 暂时影藏此功能
 */

public class ProgressSubscriber<T> extends Subscriber<T> {
    private SubscriberOnNextListener mSubscriberOnNextListener;

    private Context context;

    public ProgressSubscriber(SubscriberOnNextListener mSubscriberOnNextListener, Context context) {
        this.mSubscriberOnNextListener = mSubscriberOnNextListener;
        this.context = context;
    }

    private void showProgressDialog() {
    }

    private void dismissProgressDialog() {
    }

    /**
     * 订阅开始时调用
     * 显示ProgressDialog
     */
    @Override
    public void onStart() {
        showProgressDialog();
    }

    /**
     * 完成，隐藏ProgressDialog
     */
    @Override
    public void onCompleted() {
        if (mSubscriberOnNextListener != null) {
            mSubscriberOnNextListener.onCompleted();
        }
        dismissProgressDialog();
    }

    /**
     * 对错误进行统一处理
     * 隐藏ProgressDialog
     *
     * @param e
     */
    @Override
    public void onError(Throwable e) {
        if (mSubscriberOnNextListener != null) {
            mSubscriberOnNextListener.onError(e);
        }
        if (e instanceof SocketTimeoutException) {
            Toast.makeText(context, "网络中断，请检查您的网络状态", Toast.LENGTH_SHORT).show();
        } else if (e instanceof ConnectException) {
            Toast.makeText(context, "网络中断，请检查您的网络状态", Toast.LENGTH_SHORT).show();
        }
        dismissProgressDialog();

    }

    /**
     * 将onNext方法中的返回结果交给Activity或Fragment自己处理
     *
     * @param t 创建Subscriber时的泛型类型
     */
    @Override
    public void onNext(T t) {
        if (mSubscriberOnNextListener != null) {
            mSubscriberOnNextListener.onNext(t);
        }
    }
}
