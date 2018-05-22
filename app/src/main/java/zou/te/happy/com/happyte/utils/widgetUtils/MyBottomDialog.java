package zou.te.happy.com.happyte.utils.widgetUtils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import zou.te.happy.com.happyte.R;
import zou.te.happy.com.happyte.utils.DensityUtil;

/**
 * Created by Administrator on 2018/5/22.
 * 底部对话框
 */

public class MyBottomDialog extends Dialog {

    private Context mContent;

    public MyBottomDialog(Context context) {
        this(context, R.style.MyAnimDialog);
        mContent=context;
    }

    public MyBottomDialog(Context context, int themeResId) {
        super(context, themeResId);
        //加载布局并给布局的控件设置点击事件
        View contentView = getLayoutInflater().inflate(themeResId, null);
        contentView.findViewById(R.id.dialog_take_photo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        super.setContentView(contentView);

        mContent=context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 预先设置Dialog的一些属性
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams p = dialogWindow.getAttributes();
        WindowManager m = getWindow().getWindowManager();
        Display d = m.getDefaultDisplay();
        getWindow().setAttributes(p);
        p.height = DensityUtil.dip2px(mContent,170);
        p.width = d.getWidth();
        p.gravity = Gravity.LEFT | Gravity.BOTTOM;
        dialogWindow.setAttributes(p);
    }
}
