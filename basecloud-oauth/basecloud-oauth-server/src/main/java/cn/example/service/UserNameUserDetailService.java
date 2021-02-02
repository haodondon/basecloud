package cn.example.service;

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

    /**
     * 完善用户信息
     * @param s
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {


        System.out.println(s);

        return User.builder().username("admin").password(new BCryptPasswordEncoder().encode("123456")).authorities("USER").build();
    }

}
