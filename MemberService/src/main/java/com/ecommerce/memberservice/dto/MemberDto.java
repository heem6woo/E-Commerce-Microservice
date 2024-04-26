package com.ecommerce.memberservice.dto;

import com.ecommerce.memberservice.entity.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberDto {

    private String name;

    private String email;

    private String password;

    private Role role;
}
