package cn.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 一枚路过的程序猿
 * @Title: 用户服务启动类
 * @date 2021/2/5 10:34
 */
@SpringBootApplication
//@EnableFeignClient
@MapperScan(basePackages = {"cn.example"})
public class UserApplication {

    public static void main(String[] args) {

        SpringApplication.run(UserApplication.class, args);

    }

}
