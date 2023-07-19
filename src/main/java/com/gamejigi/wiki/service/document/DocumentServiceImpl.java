package com.gamejigi.wiki.service.document;

import com.gamejigi.wiki.domain.document.Document;
import com.gamejigi.wiki.repository.document.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService{
    private final DocumentRepository docRepository;

    @Override
    public Document getByTitle(String title){
        return docRepository.findByDocTitle(title)
                .map(Document::new)
                .orElse(null);
    }
}
