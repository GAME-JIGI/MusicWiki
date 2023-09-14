package com.gamejigi.wiki;

import com.gamejigi.wiki.domain.member.role.Role;
import com.gamejigi.wiki.entity.member.MemberEntity;
import com.gamejigi.wiki.repository.member.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootTest
public class TestCreateAdmin {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void createAdmin() {
        if (memberRepository.findByUserId("admin").isPresent()) {
            System.out.println("Admin is already existing");
            return;
        }

        MemberEntity member = MemberEntity.builder()
                .name("admin")
                .email("email")
                .role(Role.ADMIN)
                .gender(true)
                .userId("admin")
                .pw(passwordEncoder.encode("admin"))
                .build();
        memberRepository.save(member);
    }
}
