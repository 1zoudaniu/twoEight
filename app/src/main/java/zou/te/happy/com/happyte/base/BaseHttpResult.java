package zou.te.happy.com.happyte.base;

/**
 * Created by Administrator on 2018/5/15.
 * 接口返回的基类   根据状态   进行统一处理
 */

public class BaseHttpResult<T> {

    public int code;
    public String message;
    public T attr;

    public BaseHttpResult(int code, String message, T attr) {
        this.code = code;
        this.message = message;
        this.attr = attr;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getAttr() {
        return attr;
    }

    public void setAttr(T attr) {
        this.attr = attr;
    }

    @Override
    public String toString() {
        return "{" +
                "code:" + code +
                ", message:" + message +
                ", attr:" + attr +
                '}';
    }
}
