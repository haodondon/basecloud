package cn.example.util;

/**
 * @author 一枚路过的程序猿
 * @Title: 公共返回码
 * @date 2021/2/5 10:09
 */
public enum ResultCode {

    SUCCESS(true,0,"操作成功"),
    INVALID_TOKEN(false,1,"无效token"),
    NO_AUTHORIZATION(false,1,"暂无权限访问，请联系管理员"),
    FAIL(false,1,"操作失败");

    //此处扩展业务状态码

    /**
     * 操作是否成功
     */
    public boolean success;
    /**
     * 操作代码
     */
    public Integer code;
    /**
     * 提示信息
     */
    public String message;

    ResultCode(boolean success, Integer code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
