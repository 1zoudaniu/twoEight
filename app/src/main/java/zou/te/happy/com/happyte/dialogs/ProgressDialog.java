package zou.te.happy.com.happyte.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import zou.te.happy.com.happyte.R;

/**
 * Created by Administrator on 2018/5/8.
 */

public class ProgressDialog extends Dialog {

    final static String TAG = "ProgressDialog";
    private TextView tvMessage;
    private String message;
    private long number;
    private IndeterminateProgressBar progressBar;
    Context context;
    public ProgressDialog(Context context) {
        super(context, R.style.BMProgressDialog);
        this.context = context;
    }

    public ProgressDialog(Context context, String message) {
        super(context, R.style.BMProgressDialog);
        this.context = context;
        this.message = message;
    }

    public ProgressDialog(Context context, int theme){
        super(context, theme);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.dialog_loading_progress);
        this.setCancelable(true);
        this.setCanceledOnTouchOutside(false);
        progressBar = (IndeterminateProgressBar) findViewById(R.id.dialog_loading_progress_progressbar);
        tvMessage = (TextView)findViewById(R.id.dialog_loading_progress_message);
        if(message != null){
            tvMessage.setText(message);
        }
        Log.d(TAG, "onCreate");
    }

    public void show(String message) {
        if(progressBar!= null){
            progressBar.start();
        }
        super.show();
        this.message = message;
        if(tvMessage != null && message != null){
            tvMessage.setText(message);
        }
    }

    public void show(int msgResId){
        show(getContext().getString(msgResId));
    }

    @Override
    public void show() {
        if(progressBar!= null){
            progressBar.start();
        }
        super.show();
    }

    public ProgressDialog setMesage(String message){
        this.message = message;
        if(tvMessage != null && message != null){
            tvMessage.setText(message);
        }
        return this;
    }

    public ProgressDialog setMesage(int resId){
        this.message = getContext().getString(resId);
        if(tvMessage != null && message != null){
            tvMessage.setText(message);
        }
        return this;
    }

    public void setNumber(long number) {
        this.number = number;
    }
    public long getNumber() {
        return number;
    }

    @Override
    public void hide() {
        if(progressBar!= null){
            progressBar.stop();
        }
        super.hide();
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }
}
