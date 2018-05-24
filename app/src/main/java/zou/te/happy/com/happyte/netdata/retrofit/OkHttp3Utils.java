package zou.te.happy.com.happyte.netdata.retrofit;

import android.content.Context;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import zou.te.happy.com.happyte.app.MyApp;
import zou.te.happy.com.happyte.netdata.cache.CaheInterceptor;
import zou.te.happy.com.happyte.netdata.cache.NovateCookieManger;

/**
 * Created by Administrator on 2018/2/26.
 * *okHttp的配置
 */

public class OkHttp3Utils {
    private static OkHttpClient mOkHttpClient;

    /**
     * 获取OkHttpClient对象
     */
    public static OkHttpClient getOkHttpClient() {

        if (null == mOkHttpClient) {
            //同样okhttp3后也使用build设计模式
            mOkHttpClient = new OkHttpClient.Builder()
                    .cookieJar(new NovateCookieManger(MyApp.getInstance()))
                    .cache(new Cache(new File(MyApp.getInstance().getExternalCacheDir(),"test_cache"),10 * 1024 * 1024))
                    .addInterceptor(new CaheInterceptor(MyApp.getInstance()))
                    .addNetworkInterceptor(new CaheInterceptor(MyApp.getInstance()))
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(2, TimeUnit.SECONDS)
                    .readTimeout(2, TimeUnit.SECONDS)
                    .build();
        }

//        addNetworkInterceptor添加的是网络拦截器，他会在在request和resposne是分别被调用一次，
//        能够操作中间过程的响应,如重定向和重试；
//        而addinterceptor添加的是aplication拦截器，他只会在response被调用一次，
//        且总是只调用一次，不需要担心中间过程的响应。


        return mOkHttpClient;
    }
}
