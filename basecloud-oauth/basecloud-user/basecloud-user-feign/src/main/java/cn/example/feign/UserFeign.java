package cn.example.feign;

import cn.example.fallback.UserFallBack;
import cn.example.util.Result;
import cn.example.vo.UserVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 一枚路过的程序猿
 * @Title: 用户服务接口
 * @date 2021/2/7 10:09
 */
//@FeignClient(value = "user-service",fallback = UserFallBack.class)
@FeignClient(url = "http://localhost:9001",name = "user-service",fallback = UserFallBack.class)
public interface UserFeign {

    /**
     * 根据用户名查看用户相关信息
     * @param userAccount
     * @return
     */
    @GetMapping("/user/loadUserByUsername")
    Result<UserVo> loadUserByUsername(@RequestParam("userAccount") String userAccount);

    @GetMapping("/user/loadUserByUserPhone/{userPhone}")
    Result<UserVo> loadUserByUserPhone(@PathVariable String userPhone);

}
