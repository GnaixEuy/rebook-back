package cn.gnaixeuy.common.enmus;

import lombok.Getter;


@Getter
public enum ResultCodeEnum {

    SUCCESS(200, "成功"),
    FAIL(201, "失败");

    private final Integer code;
    private final String msg;

    private ResultCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}