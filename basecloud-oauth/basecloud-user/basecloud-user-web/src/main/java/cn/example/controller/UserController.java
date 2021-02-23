package cn.example.controller;


import cn.example.service.UserService;
import cn.example.util.Result;
import cn.example.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author haodongdong
 * @since 2021-02-07
 */
@RestController
@RequestMapping("/user")
@Api(tags={"用户"})
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "根据用户名查看用户相关信息",hidden = true)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userAccount", value = "登录账号", paramType = "path", required = true, dataType = "String"),
    })
    @GetMapping("/loadUserByUsername")
    public Result<UserVo> loadUserByUsername(@RequestParam("userAccount") String userAccount){

        return new Result<UserVo>(this.userService.loadUserByUsername(userAccount));

    }

    @ApiOperation(value = "根据手机号查看用户相关信息",hidden = true)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userPhone", value = "手机号", paramType = "path", required = true, dataType = "String"),
    })
    @GetMapping("/loadUserByUserPhone/{userPhone}")
    public Result<UserVo> loadUserByUserPhone(@PathVariable String userPhone){

        return new Result<UserVo>(this.userService.loadUserByUserPhone(userPhone));

    }

    @GetMapping("/login")
    @PreAuthorize("hasAnyAuthority('USER_ADMIN')")
    public Result<String> loadUserByUsername(){

        return new Result<>("登录成功");

    }


}

