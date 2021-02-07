package cn.example.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author 一枚路过的程序猿
 * @Title: 用户Feign实体
 * @date 2021/2/7 10:12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVo implements Serializable {

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 登录账户
     */
    private String userAccount;

    /**
     * 登录密码
     */
    private String userPassword;

    /**
     * 用户冻结状态 0-否 1-是
     */
    private Integer userFrozen;

    /**
     * 角色权限集合
     */
    private List<String> authorities;

}
