package com.gamejigi.wiki.service.debate;


import com.gamejigi.wiki.domain.debate.Debate;
import com.gamejigi.wiki.domain.member.role.Role;
import com.gamejigi.wiki.entity.document.DocumentEntity;
import com.gamejigi.wiki.entity.debate.DebateEntity;
import com.gamejigi.wiki.entity.member.MemberEntity;
import com.gamejigi.wiki.repository.document.DocumentRepository;
import com.gamejigi.wiki.repository.debate.DebateRepository;
import com.gamejigi.wiki.repository.member.MemberRepository;
import com.gamejigi.wiki.util.PaginationRequest;
import com.gamejigi.wiki.util.PaginationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DebateServiceImpl implements DebateService {

    private final DebateRepository debateRepository;

    private final DocumentRepository documentRepository;

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public PaginationResponse<Debate> getDebateList(PaginationRequest request) {
        String searchStr = "%" + request.getSearch() + "%";
        Pageable pageable = request.getPageable();

        Page<DebateEntity> result;
        if (searchStr.compareTo("%%") == 0) {
            result = debateRepository.findAll(pageable);
        }
        else {
            result = debateRepository.findPageByNameLike(searchStr, pageable);
        }

        return PaginationResponse.<Debate>builder()
                .request(request)
                .columnList(result.stream()
                        .map(Debate::new)
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

        //document1 만들기
        DocumentEntity document1 = documentRepository.findByName("document1")
                .orElseGet(() -> {
                    //document1 없으면 만들기
                    DocumentEntity document = DocumentEntity.builder()
                            .su(su)
                            .name("document1")
                            .build();
                    return documentRepository.save(document);
                });

        //토론 100개 생성
        for (int i = 0; i < 100; i++) {
            DebateEntity debate = DebateEntity.builder()
                    .document(document1)
                    .su(su)
                    .name("debate"+i)
                    .build();
            debateRepository.save(debate);
        }
    }

    @Override
    public void deleteTestcase() {
        debateRepository.deleteAll();
    }

    @Override
    public Debate getDebateById(long id) {
        return debateRepository.findById(id)
                .map(Debate::new)
                .orElse(null);
    }

    @Override
    public void createDebate(String name, long documentId, long suId) {

        MemberEntity su = memberRepository
                .findById(suId)
                .orElse(null);

        DocumentEntity document = documentRepository
                .findById(documentId)
                .orElse(null);

        DebateEntity debate = DebateEntity
                .builder()
                .su(su)
                .name(name)
                .document(document)
                .build();

        debateRepository.save(debate);
    }

    @Override
    public void delete(long id) {
        debateRepository.deleteById(id);
    }

    @Override
    public void patch(long id, long suId, String name, long documentId) {
        DebateEntity oldDebate = debateRepository.findById(id).orElse(null);
        DocumentEntity newDocument = documentRepository.findById(documentId).orElse(null);
        MemberEntity newSu = memberRepository.findById(suId).orElse(null);

        DebateEntity newDebate = DebateEntity.builder()
                .id(id)
                .name(name)
                .document(newDocument)
                .su(newSu)
                .build();

        debateRepository.save(newDebate);
    }

}