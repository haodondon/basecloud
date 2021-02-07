package cn.example.mapper;

import cn.example.model.MenuDo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author haodongdong
 * @since 2021-02-07
 */
@Mapper
public interface MenuMapper extends BaseMapper<MenuDo> {

}
