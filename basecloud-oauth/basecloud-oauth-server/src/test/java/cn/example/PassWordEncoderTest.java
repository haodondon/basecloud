package cn.example;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author 一枚路过的程序猿
 * @Title: 密码加密测试
 * @date 2021/2/2 16:43
 */
public class PassWordEncoderTest {

    public static void main(String[] args) {

        System.out.println(new BCryptPasswordEncoder().encode("123456"));

    }

}
