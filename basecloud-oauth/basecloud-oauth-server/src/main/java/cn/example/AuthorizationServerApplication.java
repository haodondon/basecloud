package cn.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/** SpringBoot启动注解 */
@SpringBootApplication(scanBasePackages = {"cn.example"})
@EnableFeignClients
public class AuthorizationServerApplication {

    public static void main(String[] args) {

        SpringApplication.run(AuthorizationServerApplication.class, args);

    }

}
