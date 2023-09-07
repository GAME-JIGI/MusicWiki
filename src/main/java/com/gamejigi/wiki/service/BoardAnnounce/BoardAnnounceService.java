package com.gamejigi.wiki.service.BoardAnnounce;

import com.gamejigi.wiki.domain.board.Board;
import com.gamejigi.wiki.domain.boardAnnounce.BoardAnnounce;
import com.gamejigi.wiki.util.PaginationRequest;
import com.gamejigi.wiki.util.PaginationResponse;

import java.util.List;

public interface BoardAnnounceService {

    List<BoardAnnounce> getBoardAnnounceList();
    PaginationResponse<BoardAnnounce> getBoardAnnounceList(PaginationRequest request);

    void createTestcase();
    void deleteTestcase();

    BoardAnnounce getBoardAnnounceById(long id);

    void createBoardAnnounce(String title, long boardId, long suId);

    void delete(long id);

    void patch(long id, long suId, String title, long boardId);
}
