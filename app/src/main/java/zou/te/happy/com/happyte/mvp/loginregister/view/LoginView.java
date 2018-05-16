package zou.te.happy.com.happyte.mvp.loginregister.view;

/**
 * Created by Administrator on 2018/5/15.
 * 登陆  找回密码
 */

public interface LoginView {
    //显示加载页
    void showProgress();

    //关闭加载页
    void hideProgress();

    //加载新数据
    void newDatas(int loOrReg,Object data);

    //显示加载失败
    void showLoadFailMsg(int loOrReg,String tTostring);

}
