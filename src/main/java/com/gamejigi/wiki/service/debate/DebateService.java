package com.gamejigi.wiki.service.debate;

import com.gamejigi.wiki.domain.debate.Debate;
import com.gamejigi.wiki.util.PaginationRequest;
import com.gamejigi.wiki.util.PaginationResponse;


public interface DebateService {

    PaginationResponse<Debate> getDebateList(PaginationRequest request);

    void createTestcase();
    void deleteTestcase();

    Debate getDebateById(long id);

    void createDebate(String name,long documentId, long suId);

    void delete(long id);

    void patch(long id, long suId, String name, long categoryId);

}
