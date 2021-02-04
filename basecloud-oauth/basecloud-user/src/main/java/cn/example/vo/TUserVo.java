package cn.example.vo;

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
 * 
 * </p>
 *
 * @author 一枚路过的程序猿
 * @since 2021-02-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_user")
@ApiModel(value="TUserVo对象", description="")
public class TUserVo implements Serializable {

    private static final long serialVersionUID=1L;

      private String id;

    @ApiModelProperty(value = "用户名称")
    private String tUserName;

    @ApiModelProperty(value = "用户登录账号")
    private String tUserAccount;

    @ApiModelProperty(value = "用户登录密码")
    private String tUserPassword;

    @ApiModelProperty(value = "用户头像")
    private String tUserPic;

    @ApiModelProperty(value = "用户冻结状态 0-否 1-是")
    private Integer tUserFrozen;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "是否删除 0-否 1-是")
    private Integer status;


}
