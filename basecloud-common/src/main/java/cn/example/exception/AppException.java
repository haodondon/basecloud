package cn.example.exception;

import cn.example.util.ResultCode;

/**
 * @author 一枚路过的程序猿
 * @Title: 自定义异常类
 * @date 2021/2/5 10:11
 */
public class AppException extends Exception {

    private static final long serialVersionUID = 1L;

    private ResultCode resultCode = ResultCode.FAIL;

    public AppException(ResultCode resultCode) {
        super(resultCode.getMessage());
        this.resultCode = resultCode;
    }

    public AppException(String msg) {
        super(msg);
        resultCode.setMessage(msg);
    }

    public ResultCode getResultCode() {
        return resultCode;
    }

    public void setResultCode(ResultCode resultCode) {
        this.resultCode = resultCode;
    }
}

