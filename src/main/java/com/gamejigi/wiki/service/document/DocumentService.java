package com.gamejigi.wiki.service.document;

import com.gamejigi.wiki.domain.document.Document;
import com.gamejigi.wiki.util.PaginationRequest;
import com.gamejigi.wiki.util.PaginationResponse;

import java.util.List;

public interface DocumentService {

    List<Document> getDocumentList();

    PaginationResponse<Document> getDocumentList(PaginationRequest request);

    void createTestcase();

    void deleteTestcase();

    Document getDocumentById(long id);

    void createDocument(String name, String text, long suId);

    void delete(long id);

    void patch(long id, long suId, String name, String text);

}
