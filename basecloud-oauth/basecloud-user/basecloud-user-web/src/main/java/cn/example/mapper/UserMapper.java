package cn.example.mapper;

import cn.example.model.UserDo;
import cn.example.vo.UserVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author haodongdong
 * @since 2021-02-07
 */
@Mapper
public interface UserMapper extends BaseMapper<UserDo> {

    /**
     * 根据用户名查看用户相关信息
     * @param userAccount
     * @return
     */
    UserVo loadUserByUsername(@Param("userAccount") String userAccount);

    /**
     * 根据手机号查看用户相关信息
     * @param userPhone
     * @return
     */
    UserVo loadUserByUserPhone(@Param("userPhone") String userPhone);
}
