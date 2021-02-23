package cn.example.handler;

import cn.example.util.Result;
import cn.example.util.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

/**
 * 登录失败处理器
 *
 * @author tangchen
 */

@Slf4j
public class BaseAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private static final String ACCEPT_TYPE_HTML = "text/html";
    private static final String CONTENT_TYPE_JSON = "application/json;charset=UTF-8";

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String type = request.getHeader("Accept");
        if (!type.contains(ACCEPT_TYPE_HTML)) {
            response.setContentType(CONTENT_TYPE_JSON);
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            Writer writer = response.getWriter();
            writer.write(new ObjectMapper().writeValueAsString(new Result(ResultCode.FAIL,exception.getMessage())));
            log.debug(exception.getMessage());
        } else {
            super.onAuthenticationFailure(request, response, exception);
        }
    }
}
