package zou.te.happy.com.happyte.netdata.retrofit;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

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
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(2, TimeUnit.SECONDS)
                    .readTimeout(2, TimeUnit.SECONDS)
                    .build();
        }

        return mOkHttpClient;
    }
}
