package com.gamejigi.wiki.service.debateComment;


import com.gamejigi.wiki.domain.debate.Debate;
import com.gamejigi.wiki.domain.debateComment.DebateComment;
import com.gamejigi.wiki.domain.document.Document;
import com.gamejigi.wiki.domain.member.role.Role;
import com.gamejigi.wiki.entity.debate.DebateEntity;
import com.gamejigi.wiki.entity.debateComment.DebateCommentEntity;
import com.gamejigi.wiki.entity.document.DocumentEntity;
import com.gamejigi.wiki.entity.member.MemberEntity;
import com.gamejigi.wiki.repository.debate.DebateRepository;
import com.gamejigi.wiki.repository.debateComment.DebateCommentRepository;
import com.gamejigi.wiki.repository.document.DocumentRepository;
import com.gamejigi.wiki.repository.member.MemberRepository;
import com.gamejigi.wiki.util.PaginationRequest;
import com.gamejigi.wiki.util.PaginationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class DebateCommentServiceImpl implements DebateCommentService {

    private final DebateCommentRepository debateCommentRepository;

    private final DebateRepository debateRepository;

    private final DocumentRepository documentRepository;

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public PaginationResponse<DebateComment> getDebateCommentList(PaginationRequest request) {
        String searchStr = "%" + request.getSearch() + "%";
        String searchType = request.getSearchType();
        Pageable pageable = request.getPageable();

        Page<DebateCommentEntity> result;
        if ("name".equals(searchType)) {
            result = debateCommentRepository.findPageByNameLike(searchStr, pageable);
        } else if ("debate".equals(searchType)) {
            result = debateCommentRepository.findPageByDebateNameLike(searchStr, pageable);
        } else if ("document".equals(searchType)) {
            result = debateCommentRepository.findPageByDocumentNameLike(searchStr, pageable);
        } else if ("writer".equals(searchType)) {
            result = debateCommentRepository.findPageByWriterNameLike(searchStr, pageable);
        } else result = debateCommentRepository.findAll(pageable);

        return PaginationResponse.<DebateComment>builder()
                .request(request)
                .columnList(result.stream()
                        .map(DebateComment::new)
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

        //debate1 만들기
        DebateEntity debate1 = debateRepository.findByName("debate1")
                .orElseGet(() -> {
                    //debate1 없으면 만들기
                    DebateEntity debate = DebateEntity.builder()
                            .su(su)
                            .document(document1)
                            .name("debate1")
                            .commentAble(0)
                            .build();
                    return debateRepository.save(debate);
                });

        //댓글 100개 생성
        for (int i = 0; i < 100; i++) {
            DebateCommentEntity debateComment = DebateCommentEntity.builder()
                    .document(document1)
                    .debate(debate1)
                    .writer(su)
                    .content("debateComment"+i)
                    .build();
            debateCommentRepository.save(debateComment);
        }
    }

    @Override
    public void deleteTestcase() {
        debateCommentRepository.deleteAll();
    }

    @Override
    public DebateComment getDebateCommentById(long id) {
        return debateCommentRepository.findById(id)
                .map(DebateComment::new)
                .orElse(null);
    }

    @Override
    public void createDebateComment(String name, long documentId,long debateId, long suId) {

        MemberEntity su = memberRepository
                .findById(suId)
                .orElse(null);

        DocumentEntity document = documentRepository
                .findById(documentId)
                .orElse(null);

        DebateEntity debate = debateRepository
                .findById(debateId)
                .orElse(null);

        Integer commentAble = debate.getCommentAble();

        if(commentAble == 0) {
            DebateCommentEntity debateComment = DebateCommentEntity
                    .builder()
                    .writer(su)
                    .content(name)
                    .document(document)
                    .debate(debate)
                    .build();

            debateCommentRepository.save(debateComment);
        }
    }

    @Override
    public void delete(long id) {
        debateCommentRepository.deleteById(id);
    }

    @Override
    public void patch(long id, long suId, String name, long documentId, long debateId) {
        DebateCommentEntity oldDebateComment = debateCommentRepository.findById(id).orElse(null);
        DocumentEntity newDocument = documentRepository.findById(documentId).orElse(null);
        DebateEntity newDebate = debateRepository.findById(debateId).orElse(null);
        MemberEntity newSu = memberRepository.findById(suId).orElse(null);

        DebateCommentEntity newDebateComment = DebateCommentEntity.builder()
                .id(id)
                .content(name)
                .document(newDocument)
                .debate(newDebate)
                .writer(newSu)
                .build();

        debateCommentRepository.save(newDebateComment);
    }


}
