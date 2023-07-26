package com.gamejigi.wiki.service.document;

import com.gamejigi.wiki.domain.document.Document;
import com.gamejigi.wiki.domain.member.role.Role;
import com.gamejigi.wiki.entity.document.DocumentEntity;
import com.gamejigi.wiki.entity.member.MemberEntity;
import com.gamejigi.wiki.repository.document.DocumentRepository;
import com.gamejigi.wiki.repository.member.MemberRepository;
import com.gamejigi.wiki.util.PaginationRequest;
import com.gamejigi.wiki.util.PaginationResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<Document> getDocumentList() {
        return documentRepository
                .findAll(Sort.by(Order.asc("name")))
                .stream()
                .map(Document::new)
                .toList();
    }

    @Override
    public PaginationResponse<Document> getDocumentList(PaginationRequest request) {
        String searchStr = "%" + request.getSearch() + "%";
        Pageable pageable = request.getPageable();

        Page<DocumentEntity> result;
        if (searchStr.compareTo("%%") == 0) {
            result = documentRepository.findAll(pageable);
        }
        else {
            result = documentRepository.findPageByNameLike(searchStr, pageable);
        }

        return PaginationResponse.<Document>builder()
                .request(request)
                .columnList(result.stream()
                        .map(Document::new)
                        .toList())
                .pageCount(result.getTotalPages())
                .rowsCount(result.getTotalElements())
                .build();
    }

    @Override
    public Document getById(long id) {
        return documentRepository
                .findById(id)
                .map(Document::new)
                .orElse(null);
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

        //게시판 카테고리 100개 생성
        for (int i = 0; i < 100; i++) {
            DocumentEntity board = DocumentEntity.builder()
                    .su(su)
                    .name("document"+i)
                    .build();
            documentRepository.save(board);
        }
    }

    @Override
    public void deleteTestcase() {
        documentRepository.deleteAll();
    }

    @Override
    public void createDocument(String name, long suId) {
        MemberEntity su = memberRepository
                .findById(suId)
                .orElse(null);

        DocumentEntity document = DocumentEntity
                .builder()
                .name(name)
                .su(su)
                .build();

        documentRepository.save(document);
    }

    @Override
    public void delete(long id) {
        documentRepository.deleteById(id);
    }

    @Override
    public void patch(long id, long suId, String name) {
        MemberEntity newSu = memberRepository.findById(suId).orElse(null);

        DocumentEntity newDocument = DocumentEntity.builder()
                .id(id)
                .name(name)
                .su(newSu)
                .build();

        documentRepository.save(newDocument);
    }
}
