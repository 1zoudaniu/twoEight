package zou.te.happy.com.happyte.netdata.api;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;
import zou.te.happy.com.happyte.base.BaseHttpResult;
import zou.te.happy.com.happyte.beanLists.BaseBean;
import zou.te.happy.com.happyte.beanLists.LoginBean;
import zou.te.happy.com.happyte.beanLists.YzmBean;

/**
 * Created by Administrator on 2018/2/26.
 * API接口
 * 因为使用RxCache作为缓存策略 所以这里不需要写缓存信息
 */

public interface APIService {

    //登陆
    @FormUrlEncoded
    @POST("user/login")
    Observable<BaseHttpResult<LoginBean>> getLogin(@FieldMap Map<String, Object> params);

    //注册
    @FormUrlEncoded
    @POST("user/reg")
    Observable<BaseHttpResult<BaseBean>> getRegister(@FieldMap Map<String, Object> params);

    //获取忘记密码的验证码
    @FormUrlEncoded
    @POST("user/password/sendVerifyCode")
    Observable<BaseHttpResult<YzmBean>> getForgetYzm(@FieldMap Map<String, Object> params);

    //注册的验证码
    @FormUrlEncoded
    @POST("user/sendVerifyCode")
    Observable<BaseHttpResult<YzmBean>> getRegisterYzm(@FieldMap Map<String, Object> params);

    //更新密码
    @FormUrlEncoded
    @POST("user/password/reset")
    Observable<BaseHttpResult<BaseBean>> updatePwd(@FieldMap Map<String, Object> params);




//    //头像上传
//    @Multipart
//    @POST("user/photo/upload")
//    Observable<BaseHttpResult<PhotoBean>>  PhotoUpload(@PartMap Map<String, RequestBody> parma);


}
