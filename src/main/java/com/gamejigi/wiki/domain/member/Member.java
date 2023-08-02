package com.gamejigi.wiki.domain.member;

import com.gamejigi.wiki.domain.member.role.Role;
import com.gamejigi.wiki.entity.member.MemberEntity;

import java.time.LocalDate;
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
    LocalDate birth;
    Boolean gender;
    Role role;
    LocalDateTime createdDate;
    LocalDateTime modifiedDate;

    public Member(MemberEntity memberEntity) {
        id = memberEntity.getId();
        user_id = memberEntity.getUserId();
        pw = memberEntity.getPw();
        phone = memberEntity.getPhone();
        email = memberEntity.getEmail();
        name = memberEntity.getName();
        birth = memberEntity.getBirth();
        gender = memberEntity.getGender();
        role = memberEntity.getRole();
        createdDate = memberEntity.getCreatedDate();
        modifiedDate = memberEntity.getModifiedDate();
    }
}
