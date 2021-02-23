package cn.example.fallback;

import cn.example.feign.UserFeign;
import cn.example.util.Result;
import cn.example.util.ResultCode;
import cn.example.vo.UserVo;

/**
 * @author 一枚路过的程序猿
 * @Title: 用户接口熔断
 * @date 2021/2/7 10:17
 */
public class UserFallBack implements UserFeign {

    @Override
    public Result<UserVo> loadUserByUsername(String userAccount) {
        return new Result<>(ResultCode.FAIL,"当前服务拥挤,请稍后再试");
    }

    @Override
    public Result<UserVo> loadUserByUserPhone(String userPhone) {
        return new Result<>(ResultCode.FAIL,"当前服务拥挤,请稍后再试");
    }

}
