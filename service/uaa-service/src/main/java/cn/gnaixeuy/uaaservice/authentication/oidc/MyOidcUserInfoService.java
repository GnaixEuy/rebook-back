package cn.gnaixeuy.uaaservice.authentication.oidc;

import cn.gnaixeuy.uaaservice.entity.SysUserEntity;
import cn.gnaixeuy.uaaservice.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <img src="https://img1.baidu.com/it/u=2537966709,2852517020&fm=253&fmt=auto&app=138&f=JPEG?w=648&h=489"/>
 * redbook-back
 *
 * @author GnaixEuy
 * @version 1.0
 * @see <a href="https://github.com/GnaixEuy">GnaixEuy</a>
 */
@Service
public class MyOidcUserInfoService {

    private SysUserService sysUserService;

    @Autowired
    public void setSysUserService(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    public MyOidcUserInfo loadUser(String username) {
        SysUserEntity sysUserEntity = sysUserService.selectByUsername(username);
        return new MyOidcUserInfo(this.createUser(sysUserEntity));
    }

    private Map<String, Object> createUser(SysUserEntity sysUserEntity) {
        return MyOidcUserInfo.myBuilder()
                .name(sysUserEntity.getName())
                .username(sysUserEntity.getUsername())
                .description(sysUserEntity.getDescription())
                .status(sysUserEntity.getStatus())
                .phoneNumber(sysUserEntity.getUsername())
                .email(sysUserEntity.getUsername() + "@example.com")
                .profile("https://example.com/" + sysUserEntity.getName())
                .address("XXX共和国XX省XX市XX区XXX街XXX号")
                .build()
                .getClaims();
    }

}