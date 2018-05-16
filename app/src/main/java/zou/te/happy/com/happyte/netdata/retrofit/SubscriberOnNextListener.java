package zou.te.happy.com.happyte.netdata.retrofit;

/**
 * Created by Administrator on 2018/2/26.
 */

public interface SubscriberOnNextListener<T> {
    void onNext(T t);

    void onError(Throwable e);

    void onCompleted();
}
