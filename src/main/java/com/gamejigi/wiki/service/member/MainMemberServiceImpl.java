package com.gamejigi.wiki.service.member;

import com.gamejigi.wiki.domain.member.MainMember;
import com.gamejigi.wiki.domain.member.role.Role;
import com.gamejigi.wiki.entity.member.MemberEntity;
import com.gamejigi.wiki.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MainMemberServiceImpl implements MainMemberService{

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void createMember(MainMember mainMember) {
        MemberEntity member = MemberEntity
                .builder()
                .userId(mainMember.getUserId())
                .pw(passwordEncoder.encode(mainMember.getUserPw()))
                .phone(mainMember.getUserPhone())
                .email(mainMember.getUserEmail())
                .name(mainMember.getUserName())
                .birth(mainMember.getUserBirtyday())
                .locked(false)
                .role(Role.USER)
                .build();

        memberRepository.save(member);
    }

}
