package com.gamejigi.wiki.domain.member;

import com.gamejigi.wiki.domain.member.role.Role;
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
    String name;
    String pw;
    String email;
    Role role;
}
