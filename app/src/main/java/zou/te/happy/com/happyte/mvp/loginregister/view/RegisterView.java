package zou.te.happy.com.happyte.mvp.loginregister.view;

/**
 * Created by Administrator on 2018/5/16.
 * 注册
 */

public interface RegisterView {
    //显示加载页
    void showProgress();

    //关闭加载页
    void hideProgress();

    //加载新数据
    void newDatas(int loOrReg,Object data);

    //显示加载失败
    void showLoadFailMsg(int loOrReg,String tTostring);
}
