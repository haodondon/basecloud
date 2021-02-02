package cn.example.config;

import cn.example.service.UserNameUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.JdbcClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import javax.sql.DataSource;

/**
 * @author 一枚路过的程序猿
 * @Title: 授权中心配置
 * @date 2021/2/2 10:46
 */
@Configuration

/** 开启授权中心 */
@EnableAuthorizationServer

public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    @Qualifier("authenticationManager")
    private AuthenticationManager authenticationManager;

    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;

     @Autowired
     private UserNameUserDetailService userDetailsService;

    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource); /// 使用Jdbctoken store
    }

    /**
     *
     *
     * 第一种 授权码模式
     *      第一步
     *          获取授权码
     *          http://localhost:9000/oauth/authorize?response_type=code&client_id=client_1&client_secret=123456&redirect_uri=http://www.baidu.com
     *      第二步
     *          通过授权码换取toeken
     *          http://localhost:9000/oauth/token?grant_type=authorization_code&code=Xek0hg&redirect_uri=http://www.baidu.com&scope=all&client_id=client_1&client_secret=123456
     *
     *      第三步
     *          校验token是否失效
     *          http://localhost:9000/oauth/check_token?token=453977bc-e6d7-4d99-80cb-179fd3762e0b
     *      第三步
     *          刷新token
     *          http://localhost:8080/oauth/token?grant_type=refresh_token&refresh_token=9da9bac7-27d9-45ad-9e77-7db2b05ce183&client_id=client_1&client_secret=123456
     *
     *
     * 第二种 密码授权模式
     *      第一步
     *          使用用户名密码获取accessToken
     *          http://localhost:9000/oauth/token?grant_type=password&username=admin&password=123456&client_id=client_1&client_secret=123456&scope=all
     *
     * @param clients
     * @throws Exception
     */

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 添加授权用户
        clients.jdbc(dataSource);
//        clients.configure(new JdbcClientDetailsServiceBuilder().dataSource(dataSource).passwordEncoder(passwordEncoder()));
//        clients.inMemory().withClient("client_1").secret(passwordEncoder().encode("123456"))
//                //重定向地址   授权类型   可进行授权的列表
//                .redirectUris("http://www.baidu.com").authorizedGrantTypes("password", "client_credentials", "refresh_token", "authorization_code").scopes("all")
//                //accessToken有效期
//                .accessTokenValiditySeconds(7200)
//                //刷新accessToken
//                .refreshTokenValiditySeconds(7200);

    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore()).authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService());// 必须设置
        // UserDetailsService
        // 否则刷新token 时会报错
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()").checkTokenAccess("permitAll()")
                .allowFormAuthenticationForClients();// 允许表单登录

    }

    @Bean
    AuthenticationManager authenticationManager() {
        AuthenticationManager authenticationManager = new AuthenticationManager() {
            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
                return daoAuhthenticationProvider().authenticate(authentication);
            }
        };
        return authenticationManager;
    }

    @Bean
    public AuthenticationProvider daoAuhthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        daoAuthenticationProvider.setHideUserNotFoundExceptions(false);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    // 设置添加用户信息,正常应该从数据库中读取
    @Bean
    UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager userDetailsService = new InMemoryUserDetailsManager();
        userDetailsService.createUser(User.withUsername("admin").password(passwordEncoder().encode("123456"))
                .authorities("ROLE_USER").build());
        userDetailsService.createUser(User.withUsername("root").password(passwordEncoder().encode("123456"))
                .authorities("ROLE_USER").build());
        return userDetailsService;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        // 加密方式
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder;
    }

}