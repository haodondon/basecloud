package cn.example.authentication.mobile;


import cn.example.authentication.mobile.service.MobileUserDetailsService;
import cn.example.handler.BaseAuthenticationFailureHandler;
import cn.example.handler.BaseAuthenticationSuccessHandler;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 邮箱配置类
 *
 * @author tangchen
 */
public class MobileSecurityConfigurerAdapter extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private MobileUserDetailsService mobileUserDetailsService;

    private BaseAuthenticationFailureHandler baseAuthenticationFailureHandler;

    private BaseAuthenticationSuccessHandler baseAuthenticationSuccessHandler;

    public MobileSecurityConfigurerAdapter baseAuthenticationSuccessHandler(BaseAuthenticationSuccessHandler baseAuthenticationSuccessHandler) {
        this.baseAuthenticationSuccessHandler = baseAuthenticationSuccessHandler;
        return this;
    }

    public MobileSecurityConfigurerAdapter baseAuthenticationFailureHandler(BaseAuthenticationFailureHandler baseAuthenticationFailureHandler) {
        this.baseAuthenticationFailureHandler = baseAuthenticationFailureHandler;
        return this;
    }

    public MobileSecurityConfigurerAdapter mobileUserDetailsService(MobileUserDetailsService mobileUserDetailsService) {
        this.mobileUserDetailsService = mobileUserDetailsService;
        return this;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {


        MobileAuthenticationFilter authenticationFilter = new MobileAuthenticationFilter();
        authenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        authenticationFilter.setAuthenticationSuccessHandler(baseAuthenticationSuccessHandler);
        authenticationFilter.setAuthenticationFailureHandler(baseAuthenticationFailureHandler);

        MobileAuthenticationProvider authenticationProvider = new MobileAuthenticationProvider();
        authenticationProvider.mobileUserDetailsService(mobileUserDetailsService);
        http.authenticationProvider(authenticationProvider)
                .addFilterAfter(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
