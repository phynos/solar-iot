package com.phynos.solar.module.ten.bo;

import com.phynos.solar.auth.user.UserDetails;
import com.phynos.solar.module.ten.entity.TenUser;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * 注释内容
 *
 * @author lupc
 * @date 2022/11/3 16:20
 */
@Data
public class MyUserDetails implements UserDetails {

    private String username;
    private String password;
    private Long tenantId;
    private Boolean enabled;

    public static MyUserDetails fromTenUser(TenUser tenUser) {
        MyUserDetails details = new MyUserDetails();
        BeanUtils.copyProperties(tenUser, details);
        return details;
    }

    @Override
    public Boolean isEnabled() {
        return enabled;
    }

}
