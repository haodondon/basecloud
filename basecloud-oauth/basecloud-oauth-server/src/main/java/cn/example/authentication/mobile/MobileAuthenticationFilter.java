package cn.example.authentication.mobile;

import cn.example.constants.BaseConstants;
import cn.example.util.HttpServletRequestUtil;
import lombok.SneakyThrows;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 定义拦截器，拦截相应的请求封装相应的登录参数
 *
 * @author tangchen
 */
public class MobileAuthenticationFilter extends AbstractAuthenticationProcessingFilter {


    /**
     * 是否只支持post
     */
    private boolean postOnly = true;

    private final static String REQUEST_POST = "POST";

    /**
     * 默认拦截的地址
     */
    private final static String DEFAULT_TOKEN_URI = "/mobile/token";

    /**
     * 默认拦截方法
     */
    private final static String DEFAULT_TOKEN_METHOD = "POST";

    /**
     * 邮箱参数
     */
    private String mobileParam = "mobile";

    /**
     * 验证码参数
     */
    private String mobileCodeParam = "code";

    public MobileAuthenticationFilter() {
        super(new AntPathRequestMatcher(DEFAULT_TOKEN_URI, DEFAULT_TOKEN_METHOD));
    }

    public MobileAuthenticationFilter(String tokenUri, String tokenMethod) {
        super(new AntPathRequestMatcher(tokenUri, tokenMethod));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        if (postOnly && !REQUEST_POST.equals(httpServletRequest.getMethod())) {
            throw new AuthenticationServiceException("请求方法只能为POST");
        }

        String mobile = null;

        String code = null;

        if (BaseConstants.REQUEST_JSON_CONTENT_TYPE.equals(httpServletRequest.getContentType())) {
            Map paramMap = new ObjectMapper().readValue(HttpServletRequestUtil.getPostData(httpServletRequest), Map.class);
            mobile = String.valueOf(paramMap.get(mobileParam));
            code = String.valueOf(paramMap.get(mobileCodeParam));
        }else{
            mobile = obtainEmail(httpServletRequest);
            code = obtainCode(httpServletRequest);
        }

        if (StringUtils.isEmpty(mobile)) {
            throw new AuthenticationServiceException("手机号码为空");
        }
        if (StringUtils.isEmpty(code)) {
            throw new AuthenticationServiceException("验证码为空");
        }

        MobileAuthenticationToken mobileAuthenticationToken = new MobileAuthenticationToken(mobile, code);
        this.setDetails(httpServletRequest, mobileAuthenticationToken);
        //调用authenticationManager进行认证
        return this.getAuthenticationManager().authenticate(mobileAuthenticationToken);
    }

    @SneakyThrows
    private String obtainEmail(HttpServletRequest request) {

        return request.getParameter(mobileParam);

    }

    @SneakyThrows
    private String obtainCode(HttpServletRequest request) {

        return request.getParameter(mobileCodeParam);

    }

    private void setDetails(HttpServletRequest request, MobileAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }

    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }

    /**
     * 设置请求邮箱参数
     *
     * @param mobileParam
     */
    public void setMobileParam(String mobileParam) {
        this.mobileParam = mobileParam;
    }

    /**
     * 设置请求验证码参数
     *
     * @param mobileCodeParam
     */
    public void setMobileCodeParam(String mobileCodeParam) {
        this.mobileCodeParam = mobileCodeParam;
    }

    public String getMobileCodeParam() {
        return mobileCodeParam;
    }

    public String getMobileParam() {
        return mobileParam;
    }
}
