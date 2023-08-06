package cn.gnaixeuy.common.response;

import cn.gnaixeuy.common.enmus.ResultCodeEnum;
import com.alibaba.nacos.shaded.com.google.gson.Gson;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import org.apache.commons.codec.Charsets;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;

import javax.security.sasl.AuthenticationException;
import java.io.IOException;
import java.nio.file.AccessDeniedException;

/**
 * <img src="https://img1.baidu.com/it/u=2537966709,2852517020&fm=253&fmt=auto&app=138&f=JPEG?w=648&h=489"/>
 * redbook-back
 *
 * @author GnaixEuy
 * @version 1.0
 * @see <a href="https://github.com/GnaixEuy">GnaixEuy</a>
 */

@Data
public class ResponseResult<T> {

    /**
     * 状态码
     */
    private Integer code;
    /**
     * 返回信息
     */
    private String message;
    /**
     * 数据
     */
    private T data;

    private ResponseResult() {
    }


    /**
     * @param body
     * @param resultCodeEnum
     * @param <T>
     * @return
     * @author Rommel
     * @date 2023/7/31-10:46
     * @version 1.0
     * @description 构造返回结果
     */
    public static <T> ResponseResult<T> build(T body, ResultCodeEnum resultCodeEnum) {
        ResponseResult<T> result = new ResponseResult<>();
        //封装数据
        if (body != null) {
            result.setData(body);
        }
        //状态码
        result.setCode(resultCodeEnum.getCode());
        //返回信息
        result.setMessage(resultCodeEnum.getMsg());
        return result;
    }


    /**
     * @param <T>
     * @return
     * @author Rommel
     * @date 2023/7/31-10:45
     * @version 1.0
     * @description 成功-无参
     */
    public static <T> ResponseResult<T> ok() {
        return build(null, ResultCodeEnum.SUCCESS);
    }


    /**
     * @param data
     * @param <T>
     * @return
     * @author Rommel
     * @date 2023/7/31-10:45
     * @version 1.0
     * @description 成功-有参
     */
    public static <T> ResponseResult<T> ok(T data) {
        return build(data, ResultCodeEnum.SUCCESS);
    }

    /**
     * @param <T>
     * @return
     * @author Rommel
     * @date 2023/7/31-10:45
     * @version 1.0
     * @description 失败-无参
     */
    public static <T> ResponseResult<T> fail() {
        return build(null, ResultCodeEnum.FAIL);
    }

    /**
     * @param data
     * @param <T>
     * @return
     * @author Rommel
     * @date 2023/7/31-10:45
     * @version 1.0
     * @description 失败-有参
     */
    public static <T> ResponseResult<T> fail(T data) {
        return build(data, ResultCodeEnum.FAIL);
    }

    /**
     * @param response
     * @param e
     * @throws IOException
     * @author Rommel
     * @date 2023/7/31-10:45
     * @version 1.0
     * @description 异常响应
     */
    public static void exceptionResponse(HttpServletResponse response, Exception e) throws AccessDeniedException, AuthenticationException, IOException {

        String message = null;
        if (e instanceof OAuth2AuthenticationException o) {
            message = o.getError().getDescription();
        } else {
            message = e.getMessage();
        }
        exceptionResponse(response, message);
    }


    public static void exceptionResponse(HttpServletResponse response, String message) throws AccessDeniedException, AuthenticationException, IOException {

        ResponseResult<String> responseResult = ResponseResult.fail(message);
        Gson gson = new Gson();
        String jsonResult = gson.toJson(responseResult);
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(Charsets.UTF_8.name());
        response.getWriter().print(jsonResult);

    }

    public ResponseResult<T> message(String msg) {
        this.setMessage(msg);
        return this;
    }

    public ResponseResult<T> code(Integer code) {
        this.setCode(code);
        return this;
    }

}