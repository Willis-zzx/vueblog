package com.zzx.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhouzixin
 * @version 1.0
 * @date 2022/4/9 20:30
 */
@Configuration
@MapperScan("com.zzx.mapper")
public class SpringConfig {
}
