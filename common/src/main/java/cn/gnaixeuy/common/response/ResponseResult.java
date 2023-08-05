package cn.gnaixeuy.common.response;

import cn.gnaixeuy.common.enmus.CodeEnum;
import com.alibaba.nacos.api.model.v2.Result;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <img src="https://img1.baidu.com/it/u=2537966709,2852517020&fm=253&fmt=auto&app=138&f=JPEG?w=648&h=489"/>
 * redbook-back
 * 统一返回格式
 *
 * @author GnaixEuy
 * @version 1.0
 * @see <a href="https://github.com/GnaixEuy">GnaixEuy</a>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseResult<T> implements Serializable {
    // 处理的数据
    private T data;
    // 返回的结果编码
    private Integer code;
    // 返回的结果信息
    private String msg;

    // 执行成功，返回的信息
    public static <T> Result<T> succeed(String msg) {
        return succeedWith(null, CodeEnum.SUCCESS.getCode(), msg);
    }

    public static <T> Result<T> succeed(T model, String msg) {
        return succeedWith(model, CodeEnum.SUCCESS.getCode(), msg);
    }

    public static <T> Result<T> succeed(T model) {
        return succeedWith(model, CodeEnum.SUCCESS.getCode(), "");
    }

    public static <T> Result<T> succeedWith(T datas, Integer code, String msg) {
        return new Result<>(code, msg, datas);
    }

    // 执行失败，返回的信息
    public static <T> Result<T> failed(String msg) {
        return failedWith(null, CodeEnum.FAIL.getCode(), msg);
    }

    public static <T> Result<T> failed(T model, String msg) {
        return failedWith(model, CodeEnum.FAIL.getCode(), msg);
    }

    public static <T> Result<T> failedWith(T datas, Integer code, String msg) {
        return new Result<>(code, msg, datas);
    }

    // 执行异常，返回的消息
    public static <T> Result<T> error(String msg) {
        return errorWith(null, CodeEnum.ERROR.getCode(), msg);
    }

    public static <T> Result<T> error(T model, String msg) {
        return errorWith(model, CodeEnum.ERROR.getCode(), msg);
    }

    public static <T> Result<T> errorWith(T datas, Integer code, String msg) {
        return new Result<>(code, msg, datas);
    }

    // 警告信息
    public static <T> Result<T> warn(String msg) {
        return warnWith(null, CodeEnum.WARN.getCode(), msg);
    }

    public static <T> Result<T> warn(T model, String msg) {
        return warnWith(model, CodeEnum.WARN.getCode(), msg);
    }

    public static <T> Result<T> warnWith(T datas, Integer code, String msg) {
        return new Result<>(code, msg, datas);
    }

    public Boolean isSuccess() {
        return CodeEnum.SUCCESS.getCode().equals(this.code);
    }

    // 判断执行结果
    public Boolean isFailed() {
        return CodeEnum.FAIL.getCode().equals(this.code);
    }

    public Boolean isError() {
        return CodeEnum.ERROR.getCode().equals(this.code);
    }

    public Boolean isWarn() {
        return CodeEnum.WARN.getCode().equals(this.code);
    }

}
