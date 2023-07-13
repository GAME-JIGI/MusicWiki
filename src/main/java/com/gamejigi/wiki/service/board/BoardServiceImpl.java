package com.gamejigi.wiki.service.board;

import com.gamejigi.wiki.domain.board.Board;
import com.gamejigi.wiki.entity.board.BoardEntity;
import com.gamejigi.wiki.repository.board.BoardRepository;
import com.gamejigi.wiki.util.PaginationRequest;
import com.gamejigi.wiki.util.PaginationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    @Override
    public PaginationResponse<Board> getBoardList(PaginationRequest request) {
        String searchStr = "%" + request.getSearch() + "%";
        Pageable pageable = request.getPageable();

        Page<BoardEntity> result = boardRepository.findPageByName(searchStr, pageable);

        return PaginationResponse.<Board>builder()
                .request(request)
                .columnList(result.stream().map(Board::new).toList())
                .pageCount(result.getTotalPages())
                .build();
    }
}
