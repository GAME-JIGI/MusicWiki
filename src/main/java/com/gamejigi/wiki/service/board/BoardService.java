package com.gamejigi.wiki.service.board;

import com.gamejigi.wiki.domain.board.Board;
import com.gamejigi.wiki.util.PaginationRequest;
import com.gamejigi.wiki.util.PaginationResponse;

public interface BoardService {

    PaginationResponse<Board> getBoardList(PaginationRequest request);

    void createTestcase();
    void deleteTestcase();
}
