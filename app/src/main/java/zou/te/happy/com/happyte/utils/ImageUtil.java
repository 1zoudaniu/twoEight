package zou.te.happy.com.happyte.utils;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Created by Administrator on 2018/5/8.
 */

public class ImageUtil {
    /**
     * 加载图片
     */
    public static void loadImage(Context context, String url, final ImageView imageView) {
        if (imageView == null) return;
        Glide.with(context).load(url).into(imageView);
    }

    public static void loadImage(Activity activity, String url, final ImageView imageView) {
        if (imageView == null) return;
        Glide.with(activity).load(url).into(imageView);
    }

    public static void loadImage(Fragment fragment, String url, final ImageView imageView) {
        if (imageView == null) return;
        Glide.with(fragment).load(url).into(imageView);
    }

    public static void loadImage(Activity activity,int res,ImageView imageView) {
        if (imageView == null) return;
        Glide.with(activity).load(res).into(imageView);
    }

}
