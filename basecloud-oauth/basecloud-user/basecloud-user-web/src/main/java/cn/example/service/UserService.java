package cn.example.service;

import cn.example.model.UserDo;
import cn.example.vo.UserVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author haodongdong
 * @since 2021-02-07
 */
public interface UserService extends IService<UserDo> {

    /**
     * 根据用户名查看用户相关信息
     * @param userAccount
     * @return
     */
    UserVo loadUserByUsername(String userAccount);

    /**
     * 根据手机号查看用户相关信息
     * @param userPhone
     * @return
     */
    UserVo loadUserByUserPhone(String userPhone);
}
