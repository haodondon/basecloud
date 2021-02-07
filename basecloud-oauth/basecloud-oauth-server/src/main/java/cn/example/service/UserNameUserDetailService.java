package cn.example.service;

import cn.example.feign.UserFeign;
import cn.example.util.Result;
import cn.example.vo.UserDetailVo;
import cn.example.vo.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author 一枚路过的程序猿
 * @Title: 客户端信息
 * @date 2021/2/2 11:19
 */
@Service
public class UserNameUserDetailService implements UserDetailsService {

    @Autowired
    private UserFeign userFeign;

    /**
     * 完善用户信息
     * @param s
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        Result<UserVo> result = this.userFeign.loadUserByUsername(s);
        if (result.getData() == null) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }

        if(result.getData().getUserFrozen() == 1){
            throw new UsernameNotFoundException("用户已被锁定");
        }

        return new UserDetailVo(result.getData());

    }

}
