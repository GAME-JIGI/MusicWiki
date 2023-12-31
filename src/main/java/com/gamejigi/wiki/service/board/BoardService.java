package com.gamejigi.wiki.service.board;

import com.gamejigi.wiki.domain.board.Board;
import com.gamejigi.wiki.domain.category.Category;
import com.gamejigi.wiki.util.PaginationRequest;
import com.gamejigi.wiki.util.PaginationResponse;

import java.util.List;

public interface BoardService {

    List<Board> getBoardList();
    PaginationResponse<Board> getBoardList(PaginationRequest request);

    void createTestcase();
    void deleteTestcase();

    Board getBoardById(long id);

    void createBoard(String name, long categoryId, long suId);

    void delete(long id);

    void patch(long id, long suId, String name, long categoryId);
}
