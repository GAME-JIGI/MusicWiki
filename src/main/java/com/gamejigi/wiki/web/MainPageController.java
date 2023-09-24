package com.gamejigi.wiki.web;

import com.gamejigi.wiki.domain.member.MainMember;
import com.gamejigi.wiki.domain.member.role.Role;
import com.gamejigi.wiki.service.member.MainMemberService;
import com.gamejigi.wiki.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
public class MainPageController {

    public final MainMemberService mainMemberService;

    @GetMapping("/")
    public String index() {
        return "main";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/member")
    public String member() {
        return "member";
    }


    @PostMapping("/member")
    public String mainMemberCreate(
            // domain.member.MainMember에 전달
              MainMember mainMember

//            @RequestParam String userId,
//            @RequestParam String userPw,
//            @RequestParam String userPhone,
//            @RequestParam String userEmail,
//            @RequestParam String userName,
//            @RequestParam String userBirthday
    ) {
        // 유효성검사 전체적으로 필요
        mainMemberService.createMember(mainMember);
        // ??.db에 회원 정보 저장하는 쿼리를 날리는 역할하는 메서드(jsp파일에서 받아온 매개변수의값)

        return "main";
    }
}
