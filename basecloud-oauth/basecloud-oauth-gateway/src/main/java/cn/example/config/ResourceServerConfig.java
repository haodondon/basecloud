package cn.example.config;

import cn.example.constants.BaseConstants;
import cn.example.exception.AuthEntryPointException;
import cn.example.exception.AuthorizationException;
import com.sun.deploy.util.ArrayUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 一枚路过的程序猿
 * @Title: 资源服务配置
 * @date 2021/2/18 10:47
 */
@Configuration
@EnableWebFluxSecurity
public class ResourceServerConfig {

    @Autowired
    private AuthEntryPointException authEntryPointException;

    @Autowired
    private AuthorizationException authorizationException;

    @Value("${ignore.urls}")
    private List<String> ignoreUrls;

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
//        http.oauth2ResourceServer().jwt()
//                .jwtAuthenticationConverter(ReactiveJwtAuthenticationConverterAdapter->{
//
//                    return null;
//                });
        http.authorizeExchange()
                .pathMatchers(StringUtils.strip(ignoreUrls.toString(),"[]")).permitAll()//白名单配置
                .anyExchange().access(new ReactiveAuthorizationManager<AuthorizationContext>(){

            /**
             * 鉴权管理器
             * @param authentication
             * @param authorizationContext
             * @return
             */
            @Override
            public Mono<AuthorizationDecision> check(Mono<Authentication> authentication, AuthorizationContext authorizationContext) {

                ServerHttpRequest httpRequest = authorizationContext.getExchange().getRequest();

                /** 对应跨域的预检请求直接放行 */
                if (httpRequest.getMethod() == HttpMethod.OPTIONS) {
                    return Mono.just(new AuthorizationDecision(true));
                }

                /** token为空拒绝访问 */
                String token = httpRequest.getHeaders().getFirst(BaseConstants.JWT_TOKEN_HEADER);
                if (StringUtils.isBlank(token)) {
                    return Mono.just(new AuthorizationDecision(false));
                }


                /** 缓存获取权限 */

                return null;
            }
        })//鉴权管理器配置
                .and().exceptionHandling()
                .accessDeniedHandler(authEntryPointException)//处理未授权
                .authenticationEntryPoint(authorizationException)//处理未认证
                .and().csrf().disable();
        return http.build();
    }


}
