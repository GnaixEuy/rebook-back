package cn.gnaixeuy.uaaservice.handler;

import cn.gnaixeuy.common.response.ResponseResult;
import cn.gnaixeuy.uaaservice.constant.OAuth2Constant;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import java.io.IOException;

/**
 * <img src="https://img1.baidu.com/it/u=2537966709,2852517020&fm=253&fmt=auto&app=138&f=JPEG?w=648&h=489"/>
 * redbook-back
 *
 * @author GnaixEuy
 * @version 1.0
 * @see <a href="https://github.com/GnaixEuy">GnaixEuy</a>
 */
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        if (authException instanceof InsufficientAuthenticationException) {
            String accept = request.getHeader("accept");
            if (accept.contains(MediaType.TEXT_HTML_VALUE)) {
                //如果是html请求类型，则返回登录页
                LoginUrlAuthenticationEntryPoint loginUrlAuthenticationEntryPoint = new LoginUrlAuthenticationEntryPoint(OAuth2Constant.LOGIN_URL);
                loginUrlAuthenticationEntryPoint.commence(request, response, authException);
            } else {
                //如果是api请求类型，则返回json
                ResponseResult.exceptionResponse(response, "需要带上令牌进行访问");
            }
        } else if (authException instanceof InvalidBearerTokenException) {
            ResponseResult.exceptionResponse(response, "令牌无效或已过期");
        } else {
            ResponseResult.exceptionResponse(response, authException);
        }

    }


}