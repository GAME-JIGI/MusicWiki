package com.gamejigi.wiki.config.auth;

import com.gamejigi.wiki.config.auth.dto.CustomUserDetails;
import com.gamejigi.wiki.domain.member.Member;
import com.gamejigi.wiki.domain.member.role.Role;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //일반 로그인 할때 실행되는 코드
        //Username(= 계정 id) 로 계정 정보를 찾아서 반환하는 메서드
        //Username 에 해당하는 계정 정보가 없다면 예외반환

        //db에서 계정 정보를 가져오는 코드
        Member member = Member.builder().name("test").pw("test").email("test@test.com").role(Role.USER).build();

        if (member == null) {
            throw new UsernameNotFoundException("Username Not Found (Username = " + username + ")");
        }

        return new CustomUserDetails(member);
    }
}
