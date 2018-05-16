package zou.te.happy.com.happyte.mvp.loginregister.model;

/**
 * Created by Administrator on 2018/5/15.
 * 登陆注册接口监听
 */

public interface LoginLoadDataListener<T> {
    void onSuccess(int loOrReg, T data);

    void onFailure(int loOrReg, Throwable e);
}
