package com.ecommerce.memberservice.vo;

import com.ecommerce.memberservice.entity.Role;
import lombok.Data;

@Data
public class ChangePermissionRequest {

    private String password;

    private String email;

    private Role role;
}
