package com.zzx.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 标签实体类
 *
 * @author zhouzixin
 * @version 1.0
 * @date 2022/4/12 22:55
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Tag implements Serializable {
    private Long id;
    private String tagName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp updateTime;
}
