package zou.te.happy.com.happyte.utils;

import android.util.Log;

import java.io.File;

import zou.te.happy.com.happyte.app.MyApp;

/**
 * Created by Administrator on 2018/5/15.
 */

public class FileUtils {

    /**
     * @return 创建缓存目录
     */
    public static File getcacheDirectory() {
        File file = new File(MyApp.getInstance().getApplicationContext().getExternalCacheDir(), "MyCishuoCache");
        if (!file.exists()) {
            boolean b = file.mkdirs();
            Log.e("file", "文件不存在  创建文件    " + b);
        } else {
            Log.e("file", "文件存在");
        }
        return file;
    }
}
