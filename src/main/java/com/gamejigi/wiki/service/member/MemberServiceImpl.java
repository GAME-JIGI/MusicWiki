package com.gamejigi.wiki.service.member;

import com.gamejigi.wiki.domain.member.Member;
import com.gamejigi.wiki.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public Member getByName(String name) {
        return memberRepository.findByUserId(name)
                .map(Member::new)
                .orElse(null);
    }
}
