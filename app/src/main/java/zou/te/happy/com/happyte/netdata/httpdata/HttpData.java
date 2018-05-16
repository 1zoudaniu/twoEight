package zou.te.happy.com.happyte.netdata.httpdata;

import java.io.File;
import java.util.Map;

import io.rx_cache.Reply;
import io.rx_cache.internal.RxCache;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import zou.te.happy.com.happyte.base.BaseHttpResult;
import zou.te.happy.com.happyte.beanLists.BaseBean;
import zou.te.happy.com.happyte.beanLists.LoginBean;
import zou.te.happy.com.happyte.beanLists.YzmBean;
import zou.te.happy.com.happyte.netdata.api.APIService;
import zou.te.happy.com.happyte.netdata.api.CacheProviders;
import zou.te.happy.com.happyte.netdata.retrofit.ApiException;
import zou.te.happy.com.happyte.netdata.retrofit.RetrofitUtils;
import zou.te.happy.com.happyte.utils.FileUtils;
import zou.te.happy.com.happyte.utils.MyLog;

/**
 * Created by Administrator on 2018/2/26.
 * 所有的请求数据的方法集中地
 * 根据MovieService的定义编写合适的方法
 * 其中observable是获取API数据
 * observableCahce获取缓存数据
 * new EvictDynamicKey(false) false使用缓存  true 加载数据不使用缓存
 */

public class HttpData extends RetrofitUtils {

    private static File cacheDirectory = FileUtils.getcacheDirectory();
    private static final CacheProviders providers = new RxCache.Builder()
            .persistence(cacheDirectory)
            .using(CacheProviders.class);

    protected static final APIService service =  getRetrofit().create(APIService.class);

    //在访问HttpMethods时创建单例
    private static class SingletonHolder {
        private static final HttpData INSTANCE = new HttpData();
    }

    //获取单例
    public static HttpData getInstance() {
        return SingletonHolder.INSTANCE;
    }

//    //头像上传
//    public  void  PhotoUpload(@PartMap Map<String, RequestBody> testMap, Observer<PhotoBean> observer){
//        Observable observable=service.PhotoUpload(testMap).map(new HttpResultFunc<PhotoBean>());
//        setSubscribe(observable,observer);
//    }

    /**
     * 插入观察者
     * @param observable
     * @param observer
     * @param <T>
     */
    public static <T> void setSubscribe(Observable<T> observable, Observer<T> observer) {
            observable.subscribeOn(Schedulers.io())
                    .subscribeOn(Schedulers.newThread())//子线程访问网络
                    .observeOn(AndroidSchedulers.mainThread())//回调到主线程
                    .subscribe(observer);
    }
    /**
     * 用来统一处理Http的resultCode,并将HttpResult的Data部分剥离出来返回给subscriber
     * @param <T>   Subscriber真正需要的数据类型，也就是Data部分的数据类型
     */
    private  class HttpResultFunc<T> implements Func1<BaseHttpResult<T>, T> {

        @Override
        public T call(BaseHttpResult<T> httpResult) {
            MyLog.d("数据总结：" + httpResult.toString());
            if (httpResult.getCode() !=0 ) {
                throw new ApiException(httpResult);
            }
            return httpResult.getAttr();
        }
    }
    /**
     * 用来统一处理RxCacha的结果
     */
    private  class HttpResultFuncCcche<T> implements Func1<Reply<T>, T> {

        @Override
        public T call(Reply<T> httpResult) {
            return httpResult.getData();
        }
    }

    //登陆
    public void getLogin(Map<String, Object> testMap, Observer<LoginBean> observer){
        Observable observable=service.getLogin(testMap).map(new HttpResultFunc<LoginBean>());
        setSubscribe(observable,observer);
    }
    //注册
    public void getRegister(Map<String, Object> testMap, Observer<BaseBean> observer){
        Observable observable=service.getRegister(testMap).map(new HttpResultFunc<BaseBean>());
        setSubscribe(observable,observer);
    }
    //获取忘记密码的验证码
    public void getForgetYzm(Map<String, Object> testMap, Observer<YzmBean> observer){
        Observable observable=service.getForgetYzm(testMap).map(new HttpResultFunc<YzmBean>());
        setSubscribe(observable,observer);
    }
    //注册的验证码
    public void getRegisterYzm(Map<String, Object> testMap, Observer<YzmBean> observer){
        Observable observable=service.getRegisterYzm(testMap).map(new HttpResultFunc<YzmBean>());
        setSubscribe(observable,observer);
    }
    //更新密码
    public void updatePwd(Map<String, Object> testMap, Observer<BaseBean> observer){
        Observable observable=service.updatePwd(testMap).map(new HttpResultFunc<BaseBean>());
        setSubscribe(observable,observer);
    }

}
