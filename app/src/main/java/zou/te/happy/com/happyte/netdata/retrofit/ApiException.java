package zou.te.happy.com.happyte.netdata.retrofit;

import zou.te.happy.com.happyte.base.BaseHttpResult;
import zou.te.happy.com.happyte.utils.MyLog;

/**
 * Created by Administrator on 2018/2/26.
 * 异常类
 * 统一处理异常
 */

public class ApiException extends RuntimeException {

    public ApiException(BaseHttpResult httpResult) {
        this(getApiExceptionMessage(httpResult));
    }

    public ApiException(String detailMessage) {
        super(detailMessage);
        MyLog.d("是在这里打印的吗");
    }

    /**
     * 对服务器接口传过来的错误信息进行统一处理
     * 免除在Activity的过多的错误判断
     */
    private static String getApiExceptionMessage(BaseHttpResult httpResult) {
        String message = "";
        switch (httpResult.getCode()) {
            case -1:
                message = httpResult.getMessage();
                break;
            default:
                message = "ERROR:网络连接异常";
                break;
        }
        return message;
    }
}
