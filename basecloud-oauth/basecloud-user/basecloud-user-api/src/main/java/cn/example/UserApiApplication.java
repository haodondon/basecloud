package cn.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"cn.example"})
public class UserApiApplication {

    public static void main(String[] args) {

        SpringApplication.run(UserApiApplication.class, args);

    }

}
