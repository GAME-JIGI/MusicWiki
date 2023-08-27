package com.gamejigi.wiki.domain.member;

import com.gamejigi.wiki.domain.member.role.Role;
import com.gamejigi.wiki.entity.member.MemberEntity;

import java.io.Serializable;
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
public class Member implements Serializable {
    Long id;
    String user_id;
    String pw;
    String phone;
    String email;
    String name;
    LocalDate birth;
    Boolean gender;
    Boolean locked;
    LocalDate lockedDate;
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
        locked = memberEntity.getLocked();
        lockedDate = memberEntity.getLockedDate();
        role = memberEntity.getRole();
        createdDate = memberEntity.getCreatedDate();
        modifiedDate = memberEntity.getModifiedDate();
    }
}
