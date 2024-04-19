package com.ecommerce.memberservice.vo;


import com.ecommerce.memberservice.entity.Role;
import lombok.Data;

@Data
public class RegisterRequest {

    private String name;
    private String email;
    private String password;
    private Role role;

}
