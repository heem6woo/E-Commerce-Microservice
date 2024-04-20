package com.ecommerce.memberservice.vo;


import com.ecommerce.memberservice.entity.Role;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class RegisterRequest {

    @NotEmpty
    private String name;

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;

    @NotEmpty
    private Role role;

}
