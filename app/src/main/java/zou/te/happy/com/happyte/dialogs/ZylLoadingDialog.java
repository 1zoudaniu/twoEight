package zou.te.happy.com.happyte.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import zou.te.happy.com.happyte.R;


/**
 * Created by Administrator on 2018/5/8.
 */

public class ZylLoadingDialog  extends Dialog {
        private static final String TAG = "ZylLoadingDialog";
        private TextView tvMessage;
        private ImageView yzsLoading;
        private Context context;
        /**
         * 下方显示message
         */
        private String message;

        private Drawable drawable;

    public ZylLoadingDialog(Context context) {
            super(context, R.style.ZylLoadingDialog);
            this.context = context;
        }

    public ZylLoadingDialog(Context context, String message) {
            super(context, R.style.ZylLoadingDialog);
            this.context = context;
            this.message = message;
        }

    public ZylLoadingDialog(Context context, String message, Drawable drawable) {
            super(context, R.style.ZylLoadingDialog);
            this.context = context;
            this.message = message;
            this.drawable = drawable;
        }

    public ZylLoadingDialog(Context context, int theme) {
            super(context, theme);
            this.context = context;
        }

    public ZylLoadingDialog(Context context, Drawable drawable) {
            super(context, R.style.ZylLoadingDialog);
            this.drawable = drawable;
        }


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            this.setContentView(R.layout.dialog_loading);
            this.setCancelable(true);
            this.setCanceledOnTouchOutside(false);
            yzsLoading = (ImageView) findViewById(R.id.dialog_loading_dialog);
            tvMessage = (TextView) findViewById(R.id.dialog_loading_message);
            if (message != null) {
                tvMessage.setText(message);
            }
            if (null == drawable) {
                yzsLoading.setImageDrawable(context.getResources().getDrawable(R.mipmap.icon_loading));
            } else {
                yzsLoading.setImageDrawable(drawable);
            }
            Rotate3d rotate3d = new Rotate3d();
            rotate3d.setDuration(2000);
            rotate3d.setRepeatCount(Integer.MAX_VALUE);
            rotate3d.setRepeatMode(2);
            yzsLoading.startAnimation(rotate3d);
        }

    public void show(String message) {
        this.message = message;
        if (tvMessage != null && message != null) {
            tvMessage.setText(message);
        }
        super.show();
    }

    public void show(int msgResId) {
        show(getContext().getString(msgResId));
    }


    public ZylLoadingDialog setYzsMessage(String message) {
        this.message = message;
        if (tvMessage != null && message != null) {
            tvMessage.setText(message);
        }
        return this;
    }

    public ZylLoadingDialog setYzsMessage(int resId) {
        this.message = getContext().getString(resId);
        if (tvMessage != null && message != null) {
            tvMessage.setText(message);
        }
        return this;
    }

    @Override
    public void hide() {
        if (null != yzsLoading) {
            yzsLoading.clearAnimation();
        }
        super.hide();
    }

    @Override
    public void dismiss() {
        if (null != yzsLoading) {
            yzsLoading.clearAnimation();
        }
        super.dismiss();
    }
}
