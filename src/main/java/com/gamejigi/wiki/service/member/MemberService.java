package com.gamejigi.wiki.service.member;

import com.gamejigi.wiki.domain.member.Member;

public interface MemberService {

    Member getByName(String name);

}
