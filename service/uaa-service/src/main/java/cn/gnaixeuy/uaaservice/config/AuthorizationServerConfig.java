package cn.gnaixeuy.uaaservice.config;


import cn.gnaixeuy.uaaservice.authentication.device.DeviceClientAuthenticationConverter;
import cn.gnaixeuy.uaaservice.authentication.device.DeviceClientAuthenticationProvider;
import cn.gnaixeuy.uaaservice.authentication.mobile.MobileGrantAuthenticationConverter;
import cn.gnaixeuy.uaaservice.authentication.mobile.MobileGrantAuthenticationProvider;
import cn.gnaixeuy.uaaservice.authentication.oidc.MyOidcUserInfoAuthenticationConverter;
import cn.gnaixeuy.uaaservice.authentication.oidc.MyOidcUserInfoAuthenticationProvider;
import cn.gnaixeuy.uaaservice.authentication.oidc.MyOidcUserInfoService;
import cn.gnaixeuy.uaaservice.authentication.password.PasswordGrantAuthenticationConverter;
import cn.gnaixeuy.uaaservice.authentication.password.PasswordGrantAuthenticationProvider;
import cn.gnaixeuy.uaaservice.constant.OAuth2Constant;
import cn.gnaixeuy.uaaservice.filter.MyExceptionTranslationFilter;
import cn.gnaixeuy.uaaservice.handler.MyAccessDeniedHandler;
import cn.gnaixeuy.uaaservice.handler.MyAuthenticationEntryPoint;
import cn.gnaixeuy.uaaservice.handler.MyAuthenticationFailureHandler;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.apache.catalina.util.StandardSessionIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.oidc.IdTokenClaimNames;
import org.springframework.security.oauth2.core.oidc.endpoint.OidcParameterNames;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.authorization.*;
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.token.*;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.MediaTypeRequestMatcher;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Instant;
import java.util.Collection;
import java.util.Date;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * <img src="https://img1.baidu.com/it/u=2537966709,2852517020&fm=253&fmt=auto&app=138&f=JPEG?w=648&h=489"/>
 * redbook-back
 *
 * @author GnaixEuy
 * @version 1.0
 * @see <a href="https://github.com/GnaixEuy">GnaixEuy</a>
 */
@Configuration
public class AuthorizationServerConfig {

    private static final String CUSTOM_CONSENT_PAGE_URI = "/oauth2/consent";
    private UserDetailsService userDetailsService;
    private MyOidcUserInfoService myOidcUserInfoService;

    /**
     * 生成RSA密钥对，给上面jwkSource() 方法的提供密钥对
     */
    private static KeyPair generateRsaKey() {
        KeyPair keyPair;
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            keyPair = keyPairGenerator.generateKeyPair();
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
        return keyPair;
    }

    /**
     * Spring Authorization Server 相关配置
     * 此处方法与下面defaultSecurityFilterChain都是SecurityFilterChain配置，配置的内容有点区别，
     * 因为Spring Authorization Server是建立在Spring Security 基础上的，defaultSecurityFilterChain方法主要
     * 配置Spring Security相关的东西，而此处authorizationServerSecurityFilterChain方法主要配置OAuth 2.1和OpenID Connect 1.0相关的东西
     */
    @Bean
    @Order(1)
    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http, RegisteredClientRepository registeredClientRepository,
                                                                      AuthorizationServerSettings authorizationServerSettings,
                                                                      OAuth2AuthorizationService authorizationService,
                                                                      OAuth2TokenGenerator<?> tokenGenerator)
            throws Exception {
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);

        //AuthenticationConverter(预处理器)，尝试从HttpServletRequest提取客户端凭据,用以构建OAuth2ClientAuthenticationToken实例。
        DeviceClientAuthenticationConverter deviceClientAuthenticationConverter =
                new DeviceClientAuthenticationConverter(
                        authorizationServerSettings.getDeviceAuthorizationEndpoint());
        //AuthenticationProvider(主处理器)，用于验证OAuth2ClientAuthenticationToken。
        DeviceClientAuthenticationProvider deviceClientAuthenticationProvider =
                new DeviceClientAuthenticationProvider(registeredClientRepository);

        http.getConfigurer(OAuth2AuthorizationServerConfigurer.class)
                .deviceAuthorizationEndpoint(deviceAuthorizationEndpoint ->
                        //设置用户码校验地址
                        deviceAuthorizationEndpoint.verificationUri("/activate")
                )
                .deviceVerificationEndpoint(deviceVerificationEndpoint ->
                        //设置授权页地址
                        deviceVerificationEndpoint.consentPage(CUSTOM_CONSENT_PAGE_URI)
                )
                .clientAuthentication(clientAuthentication ->
                        //设置AuthenticationConverter(预处理器)和AuthenticationProvider(主处理器)
                        clientAuthentication
                                .authenticationConverter(deviceClientAuthenticationConverter)
                                .authenticationProvider(deviceClientAuthenticationProvider)
                )
                .authorizationEndpoint(authorizationEndpoint ->
                        authorizationEndpoint.consentPage(CUSTOM_CONSENT_PAGE_URI))
                //设置自定义密码模式
                .tokenEndpoint(tokenEndpoint ->
                        tokenEndpoint
                                .accessTokenRequestConverter(
                                        new PasswordGrantAuthenticationConverter())
                                .authenticationProvider(
                                        new PasswordGrantAuthenticationProvider(
                                                authorizationService, tokenGenerator)))
                //设置自定义手机验证码模式
                .tokenEndpoint(tokenEndpoint ->
                        tokenEndpoint
                                .accessTokenRequestConverter(
                                        new MobileGrantAuthenticationConverter())
                                .authenticationProvider(
                                        new MobileGrantAuthenticationProvider(
                                                authorizationService, tokenGenerator)))
                .tokenEndpoint(tokenEndpoint -> tokenEndpoint.errorResponseHandler(new MyAuthenticationFailureHandler()))
                .clientAuthentication(clientAuthentication -> clientAuthentication.errorResponseHandler(new MyAuthenticationFailureHandler()))
                //开启OpenID Connect 1.0（其中oidc为OpenID Connect的缩写）。
                //.oidc(Customizer.withDefaults());
                //自定义oidc
                .oidc(oidcCustomizer -> oidcCustomizer.userInfoEndpoint(userInfoEndpointCustomizer -> {
                    userInfoEndpointCustomizer.userInfoRequestConverter(new MyOidcUserInfoAuthenticationConverter(myOidcUserInfoService));
                    userInfoEndpointCustomizer.authenticationProvider(new MyOidcUserInfoAuthenticationProvider(authorizationService));
                }));

        //设置登录地址，需要进行认证的请求被重定向到该地址
        http
                .addFilterBefore(new MyExceptionTranslationFilter(), ExceptionTranslationFilter.class)
                .exceptionHandling((exceptions) -> exceptions
                        .defaultAuthenticationEntryPointFor(
                                new LoginUrlAuthenticationEntryPoint(OAuth2Constant.LOGIN_URL),
                                new MediaTypeRequestMatcher(MediaType.TEXT_HTML)
                        )
                        .authenticationEntryPoint(new MyAuthenticationEntryPoint())
                        .accessDeniedHandler(new MyAccessDeniedHandler())
                )
                .oauth2ResourceServer(oauth2ResourceServer ->
                        oauth2ResourceServer.jwt(Customizer.withDefaults()));

        return http.build();
    }

    /**
     * 客户端信息
     * 对应表：oauth2_registered_client
     */
    @Bean
    public RegisteredClientRepository registeredClientRepository(JdbcTemplate jdbcTemplate) {
        return new JdbcRegisteredClientRepository(jdbcTemplate);
    }

    /**
     * 授权信息
     * 对应表：oauth2_authorization
     */
    @Bean
    public OAuth2AuthorizationService authorizationService(JdbcTemplate jdbcTemplate, RegisteredClientRepository registeredClientRepository) {
        return new JdbcOAuth2AuthorizationService(jdbcTemplate, registeredClientRepository);
    }

    /**
     * 授权确认
     * 对应表：oauth2_authorization_consent
     */
    @Bean
    public OAuth2AuthorizationConsentService authorizationConsentService(JdbcTemplate jdbcTemplate, RegisteredClientRepository registeredClientRepository) {
        return new JdbcOAuth2AuthorizationConsentService(jdbcTemplate, registeredClientRepository);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 配置 JWK，为JWT(id_token)提供加密密钥，用于加密/解密或签名/验签
     * JWK详细见：<a href="https://datatracker.ietf.org/doc/html/draft-ietf-jose-json-web-key-41">...</a>
     */
    @Bean
    public JWKSource<SecurityContext> jwkSource() {
        KeyPair keyPair = generateRsaKey();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        RSAKey rsaKey = new RSAKey.Builder(publicKey)
                .privateKey(privateKey)
                .keyID(UUID.randomUUID().toString())
                .build();
        JWKSet jwkSet = new JWKSet(rsaKey);
        return new ImmutableJWKSet<>(jwkSet);
    }

    /**
     * 配置jwt解析器
     */
    @Bean
    public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
        return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
    }

    /**
     * 配置认证服务器请求地址
     */
    @Bean
    public AuthorizationServerSettings authorizationServerSettings() {
        //什么都不配置，则使用默认地址
        return AuthorizationServerSettings.builder().build();
    }

    /**
     * 配置token生成器
     */
    @Bean
    OAuth2TokenGenerator<?> tokenGenerator(JWKSource<SecurityContext> jwkSource) {
        JwtGenerator jwtGenerator = new JwtGenerator(new NimbusJwtEncoder(jwkSource));
        jwtGenerator.setJwtCustomizer(jwtCustomizer(myOidcUserInfoService));
        OAuth2AccessTokenGenerator accessTokenGenerator = new OAuth2AccessTokenGenerator();
        OAuth2RefreshTokenGenerator refreshTokenGenerator = new OAuth2RefreshTokenGenerator();
        return new DelegatingOAuth2TokenGenerator(
                jwtGenerator, accessTokenGenerator, refreshTokenGenerator);
    }

    @Bean
    public OAuth2TokenCustomizer<JwtEncodingContext> jwtCustomizer(MyOidcUserInfoService myOidcUserInfoService) {

        return context -> {
            JwsHeader.Builder headers = context.getJwsHeader();
            JwtClaimsSet.Builder claims = context.getClaims();
            if (context.getTokenType().equals(OAuth2TokenType.ACCESS_TOKEN)) {
                // Customize headers/claims for access_token
                claims.claims(claimsConsumer -> {
                    UserDetails userDetails = userDetailsService.loadUserByUsername(context.getPrincipal().getName());
                    claimsConsumer.merge("scope", userDetails.getAuthorities(), (scope, authorities) -> {
                        Set<String> scopeSet = (Set<String>) scope;
                        Set<String> cloneSet = scopeSet.stream().map(String::new).collect(Collectors.toSet());
                        Collection<SimpleGrantedAuthority> simpleGrantedAuthorities = (Collection<SimpleGrantedAuthority>) authorities;
                        simpleGrantedAuthorities.forEach(simpleGrantedAuthority -> {
                            if (!cloneSet.contains(simpleGrantedAuthority.getAuthority())) {
                                cloneSet.add(simpleGrantedAuthority.getAuthority());
                            }
                        });
                        return cloneSet;
                    });
                });

            } else if (context.getTokenType().getValue().equals(OidcParameterNames.ID_TOKEN)) {
                // Customize headers/claims for id_token
                claims.claim(IdTokenClaimNames.AUTH_TIME, Date.from(Instant.now()));
                StandardSessionIdGenerator standardSessionIdGenerator = new StandardSessionIdGenerator();
                claims.claim("sid", standardSessionIdGenerator.generateSessionId());
            }
        };
    }

    @Autowired
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Autowired
    public void setMyOidcUserInfoService(MyOidcUserInfoService myOidcUserInfoService) {
        this.myOidcUserInfoService = myOidcUserInfoService;
    }
}