package cn.gnaixeuy.common.enmus;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <img src="https://img1.baidu.com/it/u=2537966709,2852517020&fm=253&fmt=auto&app=138&f=JPEG?w=648&h=489"/>
 * redbook-back
 * 执行代码的枚举类型
 *
 * @author GnaixEuy
 * @version 1.0
 * @see <a href="https://github.com/GnaixEuy">GnaixEuy</a>
 */
@Getter
@AllArgsConstructor
public enum CodeEnum {

    SUCCESS(200),
    ERROR(500),
    FAIL(50000),
    WARN(70000);

    private final Integer code;

}

