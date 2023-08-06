package cn.gnaixeuy.uaaservice.authentication.oidc;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.authorization.oidc.authentication.OidcUserInfoAuthenticationToken;
import org.springframework.security.web.authentication.AuthenticationConverter;

/**
 * <img src="https://img1.baidu.com/it/u=2537966709,2852517020&fm=253&fmt=auto&app=138&f=JPEG?w=648&h=489"/>
 * redbook-back
 *
 * @author GnaixEuy
 * @version 1.0
 * @see <a href="https://github.com/GnaixEuy">GnaixEuy</a>
 */
public class MyOidcUserInfoAuthenticationConverter implements AuthenticationConverter {

    private MyOidcUserInfoService myOidcUserInfoService;


    public MyOidcUserInfoAuthenticationConverter(MyOidcUserInfoService myOidcUserInfoService) {
        this.myOidcUserInfoService = myOidcUserInfoService;
    }

    @Nullable
    @Override
    public Authentication convert(HttpServletRequest request) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //查询用户信息
        MyOidcUserInfo myOidcUserInfo = myOidcUserInfoService.loadUser(authentication.getName());

        //返回自定义的OidcUserInfoAuthenticationToken
        return new OidcUserInfoAuthenticationToken(authentication, myOidcUserInfo);
    }


}