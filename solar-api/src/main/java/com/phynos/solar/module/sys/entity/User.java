package com.phynos.solar.module.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 管理用户表格
 * </p>
 *
 * @author lupc
 * @since 2021-07-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 数据创建时间
     */
    private LocalDateTime createdDatetime;

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
