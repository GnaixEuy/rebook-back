package cn.gnaixeuy.uaaservice.authentication.oidc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.oidc.authentication.OidcUserInfoAuthenticationContext;
import org.springframework.security.oauth2.server.authorization.oidc.authentication.OidcUserInfoAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.AbstractOAuth2TokenAuthenticationToken;
import org.springframework.util.Assert;

import java.util.*;
import java.util.function.Function;

/**
 * <img src="https://img1.baidu.com/it/u=2537966709,2852517020&fm=253&fmt=auto&app=138&f=JPEG?w=648&h=489"/>
 * redbook-back
 *
 * @author GnaixEuy
 * @version 1.0
 * @see <a href="https://github.com/GnaixEuy">GnaixEuy</a>
 */
@Slf4j
public class MyOidcUserInfoAuthenticationProvider implements AuthenticationProvider {

    private final OAuth2AuthorizationService authorizationService;
    private Function<OidcUserInfoAuthenticationContext, MyOidcUserInfo> userInfoMapper = new MyOidcUserInfoAuthenticationProvider.DefaultOidcUserInfoMapper();

    public MyOidcUserInfoAuthenticationProvider(OAuth2AuthorizationService authorizationService) {
        Assert.notNull(authorizationService, "authorizationService cannot be null");
        this.authorizationService = authorizationService;
    }

    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        OidcUserInfoAuthenticationToken userInfoAuthentication = (OidcUserInfoAuthenticationToken) authentication;
        AbstractOAuth2TokenAuthenticationToken<?> accessTokenAuthentication = null;
        if (AbstractOAuth2TokenAuthenticationToken.class.isAssignableFrom(userInfoAuthentication.getPrincipal().getClass())) {
            accessTokenAuthentication = (AbstractOAuth2TokenAuthenticationToken) userInfoAuthentication.getPrincipal();
        }

        if (accessTokenAuthentication != null && accessTokenAuthentication.isAuthenticated()) {
            String accessTokenValue = accessTokenAuthentication.getToken().getTokenValue();
            OAuth2Authorization authorization = this.authorizationService.findByToken(accessTokenValue, OAuth2TokenType.ACCESS_TOKEN);
            if (authorization == null) {
                throw new OAuth2AuthenticationException("invalid_token");
            } else {
                if (log.isTraceEnabled()) {
                    log.trace("Retrieved authorization with access token");
                }

                OAuth2Authorization.Token<OAuth2AccessToken> authorizedAccessToken = authorization.getAccessToken();
                if (!authorizedAccessToken.isActive()) {
                    throw new OAuth2AuthenticationException("invalid_token");
                } else {
                    //从认证结果中获取userInfo
                    MyOidcUserInfo myOidcUserInfo = (MyOidcUserInfo) userInfoAuthentication.getUserInfo();
                    //从authorizedAccessToken中获取授权范围
                    Set<String> scopeSet = (HashSet<String>) authorizedAccessToken.getClaims().get("scope");
                    //获取授权范围对应userInfo的字段信息
                    Map<String, Object> claims = DefaultOidcUserInfoMapper.getClaimsRequestedByScope(myOidcUserInfo.getClaims(), scopeSet);
                    if (log.isTraceEnabled()) {
                        log.trace("Authenticated user info request");
                    }
                    //构造新的OidcUserInfoAuthenticationToken
                    return new OidcUserInfoAuthenticationToken(accessTokenAuthentication, new MyOidcUserInfo(claims));
                }
            }
        } else {
            throw new OAuth2AuthenticationException("invalid_token");
        }
    }

    public boolean supports(Class<?> authentication) {
        return OidcUserInfoAuthenticationToken.class.isAssignableFrom(authentication);
    }

    public void setUserInfoMapper(Function<OidcUserInfoAuthenticationContext, MyOidcUserInfo> userInfoMapper) {
        Assert.notNull(userInfoMapper, "userInfoMapper cannot be null");
        this.userInfoMapper = userInfoMapper;
    }

    private static final class DefaultOidcUserInfoMapper implements Function<OidcUserInfoAuthenticationContext, MyOidcUserInfo> {
        private static final List<String> EMAIL_CLAIMS = Arrays.asList("email", "email_verified");
        private static final List<String> PHONE_CLAIMS = Arrays.asList("phone_number", "phone_number_verified");
        private static final List<String> PROFILE_CLAIMS = Arrays.asList("name", "username", "description", "status", "profile");

        private DefaultOidcUserInfoMapper() {
        }

        private static Map<String, Object> getClaimsRequestedByScope(Map<String, Object> claims, Set<String> requestedScopes) {
            Set<String> scopeRequestedClaimNames = new HashSet(32);
            scopeRequestedClaimNames.add("sub");
            if (requestedScopes.contains("address")) {
                scopeRequestedClaimNames.add("address");
            }

            if (requestedScopes.contains("email")) {
                scopeRequestedClaimNames.addAll(EMAIL_CLAIMS);
            }

            if (requestedScopes.contains("phone")) {
                scopeRequestedClaimNames.addAll(PHONE_CLAIMS);
            }

            if (requestedScopes.contains("profile")) {
                scopeRequestedClaimNames.addAll(PROFILE_CLAIMS);
            }

            Map<String, Object> requestedClaims = new HashMap(claims);
            requestedClaims.keySet().removeIf((claimName) -> {
                return !scopeRequestedClaimNames.contains(claimName);
            });
            return requestedClaims;
        }

        public MyOidcUserInfo apply(OidcUserInfoAuthenticationContext authenticationContext) {
            OAuth2Authorization authorization = authenticationContext.getAuthorization();
            OidcIdToken idToken = (OidcIdToken) authorization.getToken(OidcIdToken.class).getToken();
            OAuth2AccessToken accessToken = authenticationContext.getAccessToken();
            Map<String, Object> scopeRequestedClaims = getClaimsRequestedByScope(idToken.getClaims(), accessToken.getScopes());
            return new MyOidcUserInfo(scopeRequestedClaims);
        }
    }
}