package cn.example.service.impl;

import cn.example.model.RoleDo;
import cn.example.mapper.RoleMapper;
import cn.example.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author haodongdong
 * @since 2021-02-07
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, RoleDo> implements RoleService {

}
