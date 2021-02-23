package cn.example.config;

import cn.example.authentication.mobile.MobileSecurityConfigurerAdapter;
import cn.example.authentication.mobile.service.MobileUserDetailsService;
import cn.example.handler.BaseAuthenticationFailureHandler;
import cn.example.handler.BaseAuthenticationSuccessHandler;
import cn.example.handler.BaseLogoutSuccessHandler;
import cn.example.service.UserNameUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * @author 一枚路过的程序猿
 * @Title:
 * @date 2021/2/2 11:34
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserNameUserDetailService userDetailsService;

    @Autowired
    private MobileSecurityConfigurerAdapter mobileSecurityConfigurerAdapter;


    // 拦截所有请求,使用httpBasic方式登陆
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.logout()
                .permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/email/token", "/mobile/token", "/social/token")//这里放开认证的端点
                .permitAll()
                .antMatchers("/**").fullyAuthenticated()
                .and().formLogin()
                .and().httpBasic().disable().csrf().disable();

        //这里引入扩展登陆的配置
        http.apply(mobileSecurityConfigurerAdapter);
    }

    /**
     * 用户验证
     * @param auth
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(daoAuthenticationProvider());
    }


    @Bean
    public AuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setHideUserNotFoundExceptions(false);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    /**
     * 配置密码加密方式
     * @return
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        // 加密方式
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder;
    }

    @Bean
    @ConditionalOnMissingBean(MobileSecurityConfigurerAdapter.class)
    MobileSecurityConfigurerAdapter mobileSecurityConfigurerAdapter(MobileUserDetailsService mobileUserDetailsService,
                                                                    BaseAuthenticationFailureHandler baseAuthenticationFailureHandler,
                                                                    BaseAuthenticationSuccessHandler baseAuthenticationSuccessHandler) {
        return new MobileSecurityConfigurerAdapter()
                .baseAuthenticationFailureHandler(baseAuthenticationFailureHandler)
                .baseAuthenticationSuccessHandler(baseAuthenticationSuccessHandler)
                .mobileUserDetailsService(mobileUserDetailsService);
    }
//
//    @Bean
//    @ConditionalOnMissingBean(BaseWebResponseExceptionTranslator.class)
//    public BaseWebResponseExceptionTranslator baseWebResponseExceptionTranslator() {
//        BaseWebResponseExceptionTranslator baseWebResponseExceptionTranslator = new BaseWebResponseExceptionTranslator();
//        return baseWebResponseExceptionTranslator;
//    }

    @Bean
    @ConditionalOnMissingBean(BaseAuthenticationFailureHandler.class)
    public BaseAuthenticationFailureHandler baseAuthenticationFailureHandler() {
        BaseAuthenticationFailureHandler baseAuthenticationFailureHandler = new BaseAuthenticationFailureHandler();
        return baseAuthenticationFailureHandler;
    }

    @Bean
    @ConditionalOnMissingBean(BaseAuthenticationSuccessHandler.class)
    public BaseAuthenticationSuccessHandler baseAuthenticationSuccessHandler(ClientDetailsService clientDetailsService,
                                                                             AuthorizationServerTokenServices authorizationServerTokenServices,
                                                                             PasswordEncoder passwordEncoder) {
        BaseAuthenticationSuccessHandler baseAuthenticationSuccessHandler = new BaseAuthenticationSuccessHandler()
                .clientDetailsService(clientDetailsService)
                .authorizationServerTokenServices(authorizationServerTokenServices)
                .passwordEncoder(passwordEncoder);
        return baseAuthenticationSuccessHandler;
    }

    @Bean
    @ConditionalOnMissingBean(BaseLogoutSuccessHandler.class)
    public BaseLogoutSuccessHandler baseLogoutSuccessHandler(TokenStore tokenStore) {
        return new BaseLogoutSuccessHandler().tokenStore(tokenStore);
    }


}
