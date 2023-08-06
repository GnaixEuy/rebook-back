package cn.gnaixeuy.uaaservice.constant;

/**
 * <img src="https://img1.baidu.com/it/u=2537966709,2852517020&fm=253&fmt=auto&app=138&f=JPEG?w=648&h=489"/>
 * redbook-back
 *
 * @author GnaixEuy
 * @version 1.0
 * @see <a href="https://github.com/GnaixEuy">GnaixEuy</a>
 */
public class OAuth2Constant {


    /**
     * 密码模式（自定义）
     */
    public static final String GRANT_TYPE_PASSWORD = "authorization_password";

    /**
     * 短信验证码模式（自定义）
     */
    public static final String GRANT_TYPE_MOBILE = "authorization_mobile";

    /**
     * 短信验证码
     */
    public static final String SMS_CODE = "sms_code";

    /**
     * 短信验证码默认值
     */
    public static final String SMS_CODE_VALUE = "8888";

    /**
     * 登录地址
     */
    public static final String LOGIN_URL = "/login";


    /**
     * 构造方法私有化
     */
    private OAuth2Constant() {

    }
}