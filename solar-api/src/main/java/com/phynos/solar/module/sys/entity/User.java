package com.phynos.solar.module.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author lupc
 * @since 2021-12-20
 */
@Getter
@Setter
@TableName("sys_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 数据创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 登录-用户名
     */
    private String username;

    /**
     * 性别，绑定字典
     */
    private String sex;

    /**
     * 登录-邮箱
     */
    private String email;

    /**
     * 登录-移动电话
     */
    private String mobilePhone;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 邮箱验证是否通过
     */
    private Boolean emailValidated;

    /**
     * 手机验证是否通过
     */
    private Boolean mobilePhoneValidated;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 真实姓名
     */
    private String realname;

    /**
     * 身份证号
     */
    private String identityCard;

    /**
     * 机构ID
     */
    private Long deptId;

    /**
     * 启用状态
     */
    private Boolean enabled;


}
