package com.gamejigi.wiki.service.document;

import com.gamejigi.wiki.domain.document.Document;
import com.gamejigi.wiki.util.PaginationRequest;
import com.gamejigi.wiki.util.PaginationResponse;

public interface DocumentService {
    Document getByName(String name);

    PaginationResponse<Document> getDocumentList(PaginationRequest request);

    //void createTestcase();

    //void deleteTestcase();

    Document getDocumentById(long id);

    void createDocument(String name, long suId);

    void delete(long id);

    void patch(long id, long suId, String name);


}
