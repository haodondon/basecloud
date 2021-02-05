package cn.example.exception;

import cn.example.util.Result;
import cn.example.util.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author 一枚路过的程序猿
 * @Title: 全局异常拦截
 * @date 2021/2/5 10:19
 */
@RestControllerAdvice
@Order(0)
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 处理所有校验失败的异常（MethodArgumentNotValidException异常）
     *
     * @param ex
     * @return
     */
//    @ExceptionHandler(value = MethodArgumentNotValidException.class)
//    public Result handleBindGetException(MethodArgumentNotValidException ex) {
//        log.error("校验异常:{}",ex);
//        // 获取所有异常
//        List<String> errors = ex.getBindingResult()
//                .getFieldErrors()
//                .stream()
//                .map(x -> x.getDefaultMessage())
//                .collect(Collectors.toList());
//        return new Result(ResultCode.FAIL, errors.get(0));
//
//    }

    /**
     * 处理单个参数校验时抛出的异常
     *
     * @param ex
     * @return
     */
//    @ExceptionHandler(value = ConstraintViolationException.class)
//    public Result handleBindException(ConstraintViolationException ex) {
//        log.error("校验异常:{}",ex);
//        // 获取所有异常
//        String errorMsg = Arrays.asList(ex.getMessage().split(",")).get(AppConstant.LIST_FIRST_VALUE);
//        String eMsg = errorMsg.substring(errorMsg.indexOf(":") + AppConstant.NUMBER_ONE);
//        return new Result(ResultCode.FAIL, eMsg);
//    }

    /**
     * 处理所有参数校验时抛出的异常
     *
     * @param appException
     * @return
     */
    @ExceptionHandler(value = AppException.class)
    public Result handleBindException(AppException appException) {
        log.error("自定义异常:{}",appException.getMessage());
        return new Result(appException.getResultCode(),appException.getMessage());

    }

    /**
     * 系统异常捕获处理
     */
    @ExceptionHandler(Exception.class)
    public Result exception(Exception e) {
        log.error("全局异常:{}",e.getMessage(),e);
        return new Result(ResultCode.FAIL,"操作失败");
    }

}