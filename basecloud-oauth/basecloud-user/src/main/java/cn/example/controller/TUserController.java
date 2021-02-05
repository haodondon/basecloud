package cn.example.controller;


import cn.example.service.TUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 一枚路过的程序猿
 * @since 2021-02-05
 */
@RestController
@RequestMapping("/tUserVo")
public class TUserController {

    @Autowired
    TUserService tUserService;

}

