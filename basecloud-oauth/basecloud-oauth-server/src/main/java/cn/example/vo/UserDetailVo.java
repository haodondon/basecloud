package cn.example.vo;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author 一枚路过的程序猿
 * @Title: 用户信息扩展
 * @date 2021/2/7 14:32
 */
@Data
public class UserDetailVo implements UserDetails {

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

    public UserDetailVo(UserVo userVo) {
        this.userId = userVo.getUserId();
        this.userAccount = userVo.getUserAccount();
        this.userPassword = userVo.getUserPassword();
        this.userFrozen = userVo.getUserFrozen();
        this.authorities = userVo.getAuthorities();
    }

    /**
     * 用户的权限集， 默认需要添加ROLE_ 前缀
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        if (CollectionUtils.isEmpty(authorities)) {
            return null;
        }

        List<GrantedAuthority> authorityList = new ArrayList<>();
        authorities.forEach(grantedAuthority -> {
            authorityList.add(new SimpleGrantedAuthority(grantedAuthority));
        });

        return authorityList;

    }

    /**
     * 用户的加密后的密码， 不加密会使用{noop}前缀
     * @return
     */
    @Override
    public String getPassword() {
        return userPassword;
    }

    /**
     * 应用内唯一的用户名
     * @return
     */
    @Override
    public String getUsername() {
        return userAccount;
    }

    /**
     * 账户是否过期
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 账户是否锁定
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return userFrozen == 0 ? true : false;
    }

    /**
     * 凭证是否过期
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 用户是否可用
     * @return
     */
    @Override
    public boolean isEnabled() {
        return userFrozen == 0 ? true : false;
    }
}
