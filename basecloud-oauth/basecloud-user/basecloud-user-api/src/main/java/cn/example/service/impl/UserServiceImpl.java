package cn.example.service.impl;

import cn.example.model.UserDo;
import cn.example.mapper.UserMapper;
import cn.example.service.UserService;
import cn.example.vo.UserVo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author haodongdong
 * @since 2021-02-07
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDo> implements UserService {

    /**
     * 根据用户名查看用户相关信息
     * @param userAccount
     * @return
     */
    @Override
    public UserVo loadUserByUsername(String userAccount) {
        return this.baseMapper.loadUserByUsername(userAccount);
    }
}
