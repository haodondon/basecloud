package cn.example.service.impl;

import cn.example.vo.TUserVo;
import cn.example.mapper.TUserMapper;
import cn.example.service.TUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 一枚路过的程序猿
 * @since 2021-02-04
 */
@Service
public class TUserServiceImpl extends ServiceImpl<TUserMapper, TUserVo> implements TUserService {

}
