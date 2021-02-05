package cn.example.util;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 一枚路过的程序猿
 * @Title: 分页参数
 * @date 2021/2/5 10:08
 */
@Api(value="分页参数",tags={"分页参数"})
@Data
public class PageParam<T> {

    @ApiModelProperty(value = "当前页")
    private Long page;

    @ApiModelProperty(value = "每页记录数")
    private Long limit;

    @ApiModelProperty(value = "查询条件")
    private T query;

}
