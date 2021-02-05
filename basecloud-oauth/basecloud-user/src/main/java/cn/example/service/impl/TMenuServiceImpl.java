package cn.example.service.impl;

import cn.example.vo.TMenuVo;
import cn.example.mapper.TMenuMapper;
import cn.example.service.TMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author 一枚路过的程序猿
 * @since 2021-02-05
 */
@Service
public class TMenuServiceImpl extends ServiceImpl<TMenuMapper, TMenuVo> implements TMenuService {

}
