package com.gamejigi.wiki.domain.member;

import com.gamejigi.wiki.domain.member.role.Role;
import com.gamejigi.wiki.entity.member.MemberEntity;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class Member {
    Long id;
    String user_id;
    String pw;
    String phone;
    String email;
    String name;
    LocalDateTime birth;
    Boolean is_su;
    Role role;

    public Member(MemberEntity memberEntity) {
        id = memberEntity.getId();
        user_id = memberEntity.getUser_id();
        pw = memberEntity.getPw();
        phone = memberEntity.getPhone();
        email = memberEntity.getEmail();
        name = memberEntity.getName();
        birth = memberEntity.getBirth();
        is_su = memberEntity.getIs_su();
        role = memberEntity.getRole();
    }
}
