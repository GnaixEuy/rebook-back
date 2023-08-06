package cn.gnaixeuy.uaaservice.service;

import cn.gnaixeuy.uaaservice.entity.SysUserEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <img src="https://img1.baidu.com/it/u=2537966709,2852517020&fm=253&fmt=auto&app=138&f=JPEG?w=648&h=489"/>
 * redbook-back
 *
 * @author GnaixEuy
 * @version 1.0
 * @see <a href="https://github.com/GnaixEuy">GnaixEuy</a>
 */

public interface SysUserService extends IService<SysUserEntity> {

    /**
     * @param username
     * @return
     * @author Rommel
     * @date 2023/7/12-23:48
     * @version 1.0
     * @description 根据用户名查询用户信息
     */
    SysUserEntity selectByUsername(String username);
}