package cn.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

/**
 * @author 一枚路过的程序猿
 * @Title: token增强配置
 * @date 2021/2/3 9:22
 */
@Configuration
public class TokenConfig extends JwtAccessTokenConverter {

    /**
     * 生成token
     * @param accessToken
     * @param authentication
     * @return
     */
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        DefaultOAuth2AccessToken defaultOAuth2AccessToken = new DefaultOAuth2AccessToken(accessToken);

        // 设置额外用户信息
//        BaseUser baseUser = ((BaseUserDetail) authentication.getPrincipal()).getBaseUser();
//        baseUser.setPassword(null);
//         将用户信息添加到token额外信息中
//        defaultOAuth2AccessToken.getAdditionalInformation().put(Constant.USER_INFO, baseUser);
//
        return super.enhance(defaultOAuth2AccessToken, authentication);
    }

}
