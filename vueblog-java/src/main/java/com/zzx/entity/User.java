package com.zzx.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.sql.Timestamp;


/**
 * <p>
 *
 * </p>
 *
 * @author zzx
 * @since 2022-04-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @NotBlank(message = "昵称不能为空")
    private String username;

    private String password;

    private String nickname;

    private String avatar;

    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;

    private Integer status;

    private Timestamp createTime;

    private Timestamp updateTime;

    private String role;


}
