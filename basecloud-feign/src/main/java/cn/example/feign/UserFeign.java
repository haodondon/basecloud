package cn.example.feign;

import cn.example.fallback.UserFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author 一枚路过的程序猿
 * @Title: 用户接口
 * @date 2021/2/5 11:08
 */
@FeignClient(value = "user-service",fallbackFactory = UserFallbackFactory.class)
public interface UserFeign {



}
