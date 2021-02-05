package cn.example.util;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Collection;

/**
 * @author 一枚路过的程序猿
 * @Title: 分页对象
 * @date 2021/2/5 10:06
 */
@Data
@ApiModel(value = "分页对象")
public class PageData<T> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "总记录数")
    private Long total;

    @ApiModelProperty(value = "列表数据")
    private Collection<T> list;

    /**
     * 分页
     * @param list   列表数据
     * @param total  总记录数
     */
    public PageData(Collection<T> list, long total) {
        this.list = list;
        this.total = total;
    }

    public PageData(){
        super();
    }

}
