package com.gamejigi.wiki.service.document;

import com.gamejigi.wiki.domain.board.Board;
import com.gamejigi.wiki.domain.document.Document;
import com.gamejigi.wiki.domain.member.role.Role;
import com.gamejigi.wiki.entity.board.BoardEntity;
import com.gamejigi.wiki.entity.category.CategoryEntity;
import com.gamejigi.wiki.entity.document.DocumentEntity;
import com.gamejigi.wiki.entity.member.MemberEntity;
import com.gamejigi.wiki.repository.document.DocumentRepository;
import com.gamejigi.wiki.repository.member.MemberRepository;
import com.gamejigi.wiki.util.PaginationRequest;
import com.gamejigi.wiki.util.PaginationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService{

    private final DocumentRepository documentRepository;
    private final MemberRepository memberRepository;
    @Override
    public Document getByName(String name){
        return documentRepository.findByName(name)
                .map(Document::new)
                .orElse(null);
    }

    @Override
    public PaginationResponse<Document> getDocumentList(PaginationRequest request){
        String searchStr = "%" + request.getSearch() + "%";
        Pageable pageable = request.getPageable();

        Page<DocumentEntity> result;
        if (searchStr.compareTo("%%") == 0) {
            result = documentRepository.findAll(pageable);
        }
        else {
            result = documentRepository.findPageByName(searchStr, pageable);
        }

        return PaginationResponse.<Document>builder()
                .request(request)
                .columnList(result.stream()
                        .map(Document::new)
                        .toList())
                .pageCount(result.getTotalPages())
                .build();
    }


    @Override
    public Document getDocumentById(long id) {
        return documentRepository.findById(id)
                .map(Document::new)
                .orElse(null);
    }

    @Override
    public void createDocument(String name, long suId){
        MemberEntity su = memberRepository
                .findById(suId)
                .orElse(null);

        DocumentEntity document = DocumentEntity
                .builder()
                .su(su)
                .name(name)
                .build();

        documentRepository.save(document);
    }

    @Override
    public void delete(long id) {
        documentRepository.deleteById(id);
    }


    @Override
    public void patch(long id, long suId, String name) {
        DocumentEntity oldDocument = documentRepository.findById(id).orElse(null);
        MemberEntity newSu = memberRepository.findById(suId).orElse(null);

        DocumentEntity newDocument = DocumentEntity.builder()
                .id(id)
                .name(name)
                .su(newSu)
                .build();

        documentRepository.save(newDocument);
    }



}
