package cn.example.constants;

/**
 * @author 一枚路过的程序猿
 * @Title: 系统常量
 * @date 2021/2/18 11:24
 */
public final class BaseConstants {

    /** 请求头token */
    public static final String JWT_TOKEN_HEADER = "Authorization";

    // 登陆类型：user:用户密码登陆；phone:手机验证码登陆；qr:二维码扫码登陆
    public static final String SPRING_SECURITY_RESTFUL_TYPE_KEY = "type";
    // 登陆终端：1：移动端登陆，包括微信公众号、小程序等；0：PC后台登陆
    public static final String SPRING_SECURITY_RESTFUL_MOBILE_KEY = "mobile";

    public static final String SPRING_SECURITY_RESTFUL_TYPE_PHONE = "phone";
    public static final String SPRING_SECURITY_RESTFUL_TYPE_QR = "qr";

    public static final String SPRING_SECURITY_RESTFUL_USERNAME_KEY = "username";
    public static final String SPRING_SECURITY_RESTFUL_PASSWORD_KEY = "password";
    public static final String SPRING_SECURITY_RESTFUL_PHONE_KEY = "phone";
    public static final String SPRING_SECURITY_RESTFUL_VERIFY_CODE_KEY = "verifyCode";
    public static final String SPRING_SECURITY_RESTFUL_QR_CODE_KEY = "qrCode";

}
