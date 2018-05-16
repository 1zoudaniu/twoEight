package zou.te.happy.com.happyte.constants;

import android.text.TextUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2018/5/15.
 */

public class URLConstant {

    public static final String API_HEAD = "https://";
    public static final String API_URL = "qy.yewu.cishoo.com";
    public static final String API_SERVER = API_HEAD + "qy.yewu.cishoo.com";

    /**
     * @param map
     * @return 签名的方法
     */
    public static String sign(Map<String, Object> map) {
        String str = "abcdefghijklmnopqrstuvwxyz";
        String strs = "";
        List<String> strss = new ArrayList<String>();
        for (String key : map.keySet()) {
            strss.add(key);
        }
        Collections.sort(strss);
        for (String string : strss) {
            strs += string + map.get(string);
        }
        strs = str + strs + str;
        return strs;
    }

    /**
     * MD5加密
     */
    public static String md5(String string) {
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(string.getBytes());
            String result = "";
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result += temp;
            }
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static boolean isPhoneNumber(String tel) {
        //定义电话格式的正则表达式
        String regex = "1\\d{10}$";
        //设定查看模式
        Pattern p = Pattern.compile(regex);
        //判断Str是否匹配，返回匹配结果
        Matcher m = p.matcher(tel);
        return m.find();
    }
}
