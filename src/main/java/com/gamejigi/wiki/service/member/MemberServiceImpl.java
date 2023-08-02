package com.gamejigi.wiki.service.member;

import com.gamejigi.wiki.domain.board.Board;
import com.gamejigi.wiki.domain.member.Member;
import com.gamejigi.wiki.domain.member.role.Role;
import com.gamejigi.wiki.entity.board.BoardEntity;
import com.gamejigi.wiki.entity.category.CategoryEntity;
import com.gamejigi.wiki.entity.member.MemberEntity;
import com.gamejigi.wiki.repository.member.MemberRepository;
import com.gamejigi.wiki.util.PaginationRequest;
import com.gamejigi.wiki.util.PaginationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public PaginationResponse<Member> getMemberList(PaginationRequest request) {
        String searchStr = "%" + request.getSearch() + "%";
        Pageable pageable = request.getPageable();

        Page<MemberEntity> result;
        if (searchStr.compareTo("%%") == 0){
            result = memberRepository.findAll(pageable);
        }
        else {
           result = memberRepository.findPageByNameLike(searchStr, pageable);
        }
        return PaginationResponse.<Member>builder()
                .request(request)
                .columnList(result.stream()
                        .map(Member::new)
                        .toList())
                .pageCount(result.getTotalPages())
                .rowsCount(result.getTotalElements())
                .build();
    }

    @Override
    public void createTestcase() {
        //관리자 계정 찾기
        MemberEntity su = memberRepository.findByUserId("admin")
                .orElseGet(() -> {
                    //관리자 계정 없으면 만들기
                    MemberEntity member = MemberEntity.builder()
                            .name("admin")
                            .email("email")
                            .role(Role.ADMIN)
                            .isSu(true)
                            .userId("admin")
                            .pw(passwordEncoder.encode("admin"))
                            .build();
                    return memberRepository.save(member);
                });

        //회원 100개 생성
        for (int i = 0; i < 100; i++) {
            String password = "password" + i;
            String email = "aaa" + i;
            MemberEntity member = MemberEntity.builder()
                    .userId("ID" + i)
                    .pw(passwordEncoder.encode(password))
                    .phone("010-0000-000" + i)
                    .email(email + "@aaa.com")
                    .name("member" + i)
                    .birth(LocalDate.now())
                    .isSu(true)
                    .role(Role.USER)
                    .build();
            memberRepository.save(member);
        }
    }

    @Override
    public void deleteTestcase() {
        memberRepository.deleteAll();
    }

    @Override
    public Member getByName(String name) {
        return memberRepository.findByUserId(name)
                .map(Member::new)
                .orElse(null);
    }


    @Override
    public void createMember(String user_id, String pw, String phone, String email,
                             String name, LocalDate birth, Boolean is_su, Role role) {
        MemberEntity member = MemberEntity
                .builder()
                .userId(user_id)
                .pw(passwordEncoder.encode(pw))
                .phone(phone)
                .email(email)
                .name(name)
                .birth(birth)
                .isSu(is_su)
                .role(role)
                .build();

        memberRepository.save(member);
    }

    @Override
    public void delete(long id) {
        memberRepository.deleteById(id);
    }

    @Override
    public void patch(long id, String user_id, String pw, String phone,
                      String email, String name, LocalDate birth, Boolean is_su, Role role) {

        MemberEntity oldMember = memberRepository.findById(id).orElse(null);

        MemberEntity newMember = MemberEntity.builder()
                .userId(user_id)
                .pw(passwordEncoder.encode(pw))
                .phone(phone)
                .email(email)
                .name(name)
                .birth(birth)
                .isSu(is_su)
                .role(role)
                .build();

        memberRepository.save(newMember);
    }
}
