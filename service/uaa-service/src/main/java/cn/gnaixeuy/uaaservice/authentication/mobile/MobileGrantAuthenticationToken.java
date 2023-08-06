package cn.gnaixeuy.uaaservice.authentication.mobile;

import cn.gnaixeuy.uaaservice.constant.OAuth2Constant;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationGrantAuthenticationToken;

import java.util.Map;

/**
 * <img src="https://img1.baidu.com/it/u=2537966709,2852517020&fm=253&fmt=auto&app=138&f=JPEG?w=648&h=489"/>
 * redbook-back
 *
 * @author GnaixEuy
 * @version 1.0
 * @see <a href="https://github.com/GnaixEuy">GnaixEuy</a>
 */
public class MobileGrantAuthenticationToken extends OAuth2AuthorizationGrantAuthenticationToken {

    public MobileGrantAuthenticationToken(Authentication clientPrincipal,
                                          @Nullable Map<String, Object> additionalParameters) {
        super(new AuthorizationGrantType(OAuth2Constant.GRANT_TYPE_MOBILE),
                clientPrincipal, additionalParameters);
    }

}