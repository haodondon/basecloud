package cn.example.service.impl;

import cn.example.model.UserRoleDo;
import cn.example.mapper.UserRoleMapper;
import cn.example.service.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色表 服务实现类
 * </p>
 *
 * @author haodongdong
 * @since 2021-02-07
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRoleDo> implements UserRoleService {

}
