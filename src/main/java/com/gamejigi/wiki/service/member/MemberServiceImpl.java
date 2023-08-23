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
import org.springframework.cglib.core.Local;
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
        if (searchStr.compareTo("%%") == 0) {
            result = memberRepository.findByUserIdIsNotNull(pageable);
            //result = memberRepository.findAll(pageable);
        } else {
            result = memberRepository.findPageByNameLikeAndUserIdNotNull(searchStr, pageable);
            //result = memberRepository.findPageByNameLike(searchStr, pageable);
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
                            .gender(true)
                            .userId("admin")
                            .pw(passwordEncoder.encode("admin"))
                            .locked(false)
                            .lockedDate(null)
                            .build();
                    return memberRepository.save(member);
                });

        //회원 100개 생성
        for (int i = 0; i < 100; i++) {
            String password = "password" + i;
            String email = "aaa" + i;
            LocalDate birth = LocalDate.now();
            MemberEntity member = MemberEntity.builder()
                    .userId("ID" + i)
                    .pw(passwordEncoder.encode(password))
                    .phone("010-0000-000" + i)
                    .email(email + "@aaa.com")
                    .name("member" + i)
                    .birth(birth)
                    .gender(true)
                    .locked(false)
                    .lockedDate(null)
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
    public Member getById(Long id) {
        return memberRepository.findById(id)
                .map(Member::new)
                .orElse(null);
    }

    @Override
    public Member getByName(String userId) {
        return memberRepository.findByUserId(userId)
                .map(Member::new)
                .orElse(null);
    }


    @Override
    public void createMember(String user_id, String pw, String phone, String email,
                             String name, LocalDate birth, Boolean gender, Role role) {
        MemberEntity member = MemberEntity
                .builder()
                .userId(user_id)
                .pw(passwordEncoder.encode(pw))
                .phone(phone)
                .email(email)
                .name(name)
                .birth(birth)
                .gender(gender)
                .role(role)
                .locked(false)
                .build();

        memberRepository.save(member);
    }

    @Override
    public void delete(long id) {
        MemberEntity oldMember = memberRepository.findById(id).orElse(null);

        if (oldMember == null) {
            return;
        }
        // 입력된 정보로 기존 회원 정보를 수정
        oldMember.setUserId(null);
        oldMember.setPw(null);
        oldMember.setPhone(null);
        oldMember.setEmail(null);
        oldMember.setName(null);
        oldMember.setBirth(null);
        oldMember.setGender(null);
        oldMember.setRole(null);
        oldMember.setLocked(null);

        // 수정된 회원 정보를 데이터베이스에 저장
        memberRepository.save(oldMember);

       // memberRepository.deleteById(id);
    }

    @Override
    public void patch(long id, String user_id, String pw, String phone,
                      String email, String name, LocalDate birth, Boolean gender, Role role, Boolean locked, LocalDate lockedDate) {

        MemberEntity oldMember = memberRepository.findById(id).orElse(null);
        System.out.println(oldMember);

        // 기존 회원 정보가 존재하지 않으면 처리 중단
        if (oldMember == null) {
            return;
        }
        // 입력된 정보로 기존 회원 정보를 수정
        oldMember.setUserId(user_id);
        if (!pw.isEmpty()) {
            oldMember.setPw(passwordEncoder.encode(pw));
        }
        oldMember.setPhone(phone);
        oldMember.setEmail(email);
        oldMember.setName(name);
        oldMember.setBirth(birth);
        oldMember.setGender(gender);
        oldMember.setRole(role);
        oldMember.setLocked(locked);
        oldMember.setLockedDate(lockedDate);

        // 수정된 회원 정보를 데이터베이스에 저장
        memberRepository.save(oldMember);
    }
}
