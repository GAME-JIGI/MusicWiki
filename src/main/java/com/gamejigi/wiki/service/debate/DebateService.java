package com.gamejigi.wiki.service.debate;

import com.gamejigi.wiki.domain.debate.Debate;
import com.gamejigi.wiki.domain.document.Document;
import com.gamejigi.wiki.util.PaginationRequest;
import com.gamejigi.wiki.util.PaginationResponse;

import java.util.List;
import java.time.LocalDateTime;

public interface DebateService {

    List<Debate> getDebateList();
    PaginationResponse<Debate> getDebateList(PaginationRequest request);

    void createTestcase();
    void deleteTestcase();

    Debate getDebateById(long id);

    void createDebate(String name,long documentId, long suId, Integer commentAble, LocalDateTime lockTime);

    void delete(long id);

    void patch(long id, long suId, String name, long documentId, Integer commentAble, LocalDateTime lockTime);

}
