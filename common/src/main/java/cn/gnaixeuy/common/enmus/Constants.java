package cn.gnaixeuy.common.enmus;

import lombok.Getter;

/**
 * <img src="https://img1.baidu.com/it/u=2537966709,2852517020&fm=253&fmt=auto&app=138&f=JPEG?w=648&h=489"/>
 * redbook-back
 *
 * @author GnaixEuy
 * @version 1.0
 * @see <a href="https://github.com/GnaixEuy">GnaixEuy</a>
 */
public class Constants {
    @Getter
    public enum KEY_IN_REDIS {
        AUTH_RESOURCE_URL_ROLE_IDS_CEL("AUTH:RESOURCE_URL_ROLE_IDS_CEL", "资源和角色关系"),
        AUTH_ROLE_ID_RESOURCE_CODE_CEL("AUTH:ROLE_ID_RESOURCE_CODE_CEL", "资源和角色关系"),
        EMAIL_CODE("EMAIL_CODE:", "邮箱验证码");
        //构造器默认只能是private, 保证构造函数只能在内部使用
        private final String value;
        private final String desc;

        KEY_IN_REDIS(String value, String desc) {
            this.value = value;
            this.desc = desc;
        }
    }
}
