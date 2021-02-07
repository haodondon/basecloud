package cn.example.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 菜单表
 * </p>
 *
 * @author haodongdong
 * @since 2021-02-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_menu")
@ApiModel(value="MenuDo对象", description="菜单表")
public class MenuDo implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId
      private String id;

    @ApiModelProperty(value = "菜单名称")
    private String menuName;

    @ApiModelProperty(value = "菜单标识")
    private String menuIdentify;

    @ApiModelProperty(value = "上级菜单")
    private String menuParentId;

    @ApiModelProperty(value = "菜单顺序")
    private Integer menuOrder;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "是否删除 0-否 1-是")
    private Integer status;


}
