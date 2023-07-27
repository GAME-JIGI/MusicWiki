package com.gamejigi.wiki.service.debateComment;

import com.gamejigi.wiki.domain.debateComment.DebateComment;
import com.gamejigi.wiki.util.PaginationRequest;
import com.gamejigi.wiki.util.PaginationResponse;


public interface DebateCommentService {

    PaginationResponse<DebateComment> getDebateCommentList(PaginationRequest request);

    void createTestcase();
    void deleteTestcase();

    DebateComment getDebateCommentById(long id);

    void createDebateComment(String name,long documentId, long debateId ,long suId);

    void delete(long id);

    void patch(long id, long suId, String name, long documentId, long debateId);

}
