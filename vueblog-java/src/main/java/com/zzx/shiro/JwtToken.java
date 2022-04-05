package com.zzx.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author zhouzixin
 * @version 1.0
 * @date 2022/4/5 11:56
 */
public class JwtToken implements AuthenticationToken {

    private String token;

    public JwtToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
