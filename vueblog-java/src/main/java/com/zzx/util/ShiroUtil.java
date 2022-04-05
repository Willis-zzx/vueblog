package com.zzx.util;

import com.zzx.shiro.AccountProfile;
import org.apache.shiro.SecurityUtils;

/**
 * @author zhouzixin
 * @version 1.0
 * @date 2022/4/5 12:12
 */
public class ShiroUtil {

    public static AccountProfile getProfile() {
        return (AccountProfile) SecurityUtils.getSubject().getPrincipal();
    }

}
