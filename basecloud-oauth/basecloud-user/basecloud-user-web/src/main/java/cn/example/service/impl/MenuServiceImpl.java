package cn.example.service.impl;

import cn.example.model.MenuDo;
import cn.example.mapper.MenuMapper;
import cn.example.service.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author haodongdong
 * @since 2021-02-07
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, MenuDo> implements MenuService {

}
