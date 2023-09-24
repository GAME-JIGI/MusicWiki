package com.gamejigi.wiki.domain.member;

import com.gamejigi.wiki.domain.member.role.Role;
import com.gamejigi.wiki.entity.member.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class MainMember {   // member.jsp 파일에서 매개변수 받음 컨트롤러

    String userId;
    String userPw;
    String userEmail;
    String userName;
    String userPhone;
    LocalDate userBirtyday;

//    Boolean gender; // 0 남성, 1 여성
//    Boolean locked;
//    LocalDate lockedDate;
//    Role;  //  0 탈퇴상태, 1 회원, 2 관리자
//    LocalDateTime createdDate;
//    LocalDateTime modifiedDate;

}
