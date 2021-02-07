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
 * 
 * </p>
 *
 * @author haodongdong
 * @since 2021-02-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_user")
@ApiModel(value="UserDo对象", description="")
public class UserDo implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId
      private String id;

    @ApiModelProperty(value = "用户名称")
    private String userName;

    @ApiModelProperty(value = "用户登录账号")
    private String userAccount;

    @ApiModelProperty(value = "用户登录密码")
    private String userPassword;

    @ApiModelProperty(value = "用户头像")
    private String userPic;

    @ApiModelProperty(value = "用户冻结状态 0-否 1-是")
    private Integer userFrozen;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "是否删除 0-否 1-是")
    private Integer status;


}
