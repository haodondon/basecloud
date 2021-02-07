package cn.example.util;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 一枚路过的程序猿
 * @Title: 数据响应对象
 * @date 2021/2/5 10:08
 */
@Data
@NoArgsConstructor
@ApiModel(value = "数据响应对象")
public class Result<T> {

    /**
     * 是否成功
     */
    @ApiModelProperty(value = "是否成功",example = "true")
    private boolean success;

    /**
     * 返回码
     */
    @ApiModelProperty(value = "返回码",example = "1000")
    private Integer code;

    /**
     * 返回信息
     */
    @ApiModelProperty(value = "返回信息",example = "操作成功")
    private String message;

    /**
     *  返回数据
     */
    @ApiModelProperty(value = "返回数据",name = "data")
    private T data;

    public Result(T data) {
        this.success = ResultCode.SUCCESS.isSuccess();
        this.code = ResultCode.SUCCESS.code;
        this.message = ResultCode.SUCCESS.message;
        this.data = data;
    }

    public Result(ResultCode code) {
        this.success = code.success;
        this.code = code.code;
        this.message = code.message;
    }


    public Result(ResultCode code, T data) {
        this.success = code.success;
        this.code = code.code;
        this.message = code.message;
        this.data = data;
    }


    public Result(ResultCode code, String message) {
        this.success = code.success;
        this.code = code.code;
        this.message = message;
    }

    public Result(ResultCode code, T data, String message) {
        this.success = code.success;
        this.code = code.code;
        this.message = message;
        this.data = data;
    }

}