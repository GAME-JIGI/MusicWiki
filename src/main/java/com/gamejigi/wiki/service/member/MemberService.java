package com.gamejigi.wiki.service.member;

import com.gamejigi.wiki.domain.board.Board;
import com.gamejigi.wiki.domain.member.Member;
import com.gamejigi.wiki.domain.member.role.Role;
import com.gamejigi.wiki.util.PaginationRequest;
import com.gamejigi.wiki.util.PaginationResponse;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface MemberService {

    PaginationResponse<Member> getMemberList(PaginationRequest request);

    void createTestcase();
    void deleteTestcase();

    Member getById(Long id);

    Member getByName(String userId);

    void createMember(String user_id, String pw, String phone, String email, String name, LocalDate birth, Boolean gender, Role role);

    void delete(long id);

    void patch(long id, String user_id, String pw, String phone, String email, String name, LocalDate birth, Boolean gender, Role role);

}
