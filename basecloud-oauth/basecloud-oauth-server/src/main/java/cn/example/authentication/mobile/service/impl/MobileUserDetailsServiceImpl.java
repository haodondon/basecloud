package cn.example.authentication.mobile.service.impl;

import cn.example.authentication.mobile.service.MobileUserDetailsService;
import cn.example.feign.UserFeign;
import cn.example.util.Result;
import cn.example.vo.UserDetailVo;
import cn.example.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author tangchen
 * @date 2020/12/21 11:57 上午
 * @copyright 2020 barm Inc. All rights reserved
 */
@Service
public class MobileUserDetailsServiceImpl implements MobileUserDetailsService {

    @Autowired
    private UserFeign userFeign;

    @Override
    public String loadCodeByMobile(String s) {
        return "1";
    }

    @Override
    public UserDetails loadUserByMobile(String userPhone) {

        Result<UserVo> result = userFeign.loadUserByUserPhone(userPhone);
        if (result.getData() == null) {
            throw new UsernameNotFoundException("手机号不存在");
        }

        if(result.getData().getUserFrozen() == 1){
            throw new UsernameNotFoundException("用户已被锁定");
        }

        return new UserDetailVo(result.getData());

    }
}
