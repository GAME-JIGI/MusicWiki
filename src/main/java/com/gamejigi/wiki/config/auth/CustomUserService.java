package com.gamejigi.wiki.config.auth;

import com.gamejigi.wiki.config.auth.dto.CustomUserDetails;
import com.gamejigi.wiki.domain.member.Member;
import com.gamejigi.wiki.domain.member.role.Role;
import com.gamejigi.wiki.entity.member.MemberEntity;
import com.gamejigi.wiki.repository.member.MemberRepository;
import java.util.function.Supplier;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //일반 로그인 할때 실행되는 코드
        //Username(= 계정 id) 로 계정 정보를 찾아서 반환한다
        //Username 에 해당하는 계정 정보가 없다면 예외반환

        return memberRepository.findByUserId(username)
                .map(Member::new)
                .map(CustomUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("Username Not Found (Username = " + username + ")"));
    }
}
