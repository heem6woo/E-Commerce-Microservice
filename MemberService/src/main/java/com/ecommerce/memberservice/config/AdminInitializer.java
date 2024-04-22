package com.ecommerce.memberservice.config;

import com.ecommerce.memberservice.entity.Member;
import com.ecommerce.memberservice.entity.Role;
import com.ecommerce.memberservice.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminInitializer implements ApplicationRunner {

    private final MemberService memberService;

    @Value("${application.security.admin1.name}")
    private String name1;

    @Value("${application.security.admin1.email}")
    private String email1;

    @Value("${application.security.admin1.password}")
    private String password1;

    @Value("${application.security.admin2.name}")
    private String name2;

    @Value("${application.security.admin2.email}")
    private String email2;

    @Value("${application.security.admin2.password}")
    private String password2;


    @Override
    public void run(ApplicationArguments args) throws Exception {

        // Create admin member
        Member adminMember1 = Member.builder()
                .name(name1)
                .email(email1)
                .password(password1)
                .role(Role.ADMIN)
                .build();


        Member adminMember2 = Member.builder()
                .name(name2)
                .email(email2)
                .password(password2)
                .role(Role.ADMIN)
                .build();

        // Save admin member
        memberService.save(adminMember1);
        memberService.save(adminMember2);

        System.out.println("Admin member created successfully.");

    }
}