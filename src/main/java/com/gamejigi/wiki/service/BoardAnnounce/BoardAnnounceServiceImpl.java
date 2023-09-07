package com.gamejigi.wiki.service.BoardAnnounce;

import com.gamejigi.wiki.domain.board.Board;
import com.gamejigi.wiki.domain.boardAnnounce.BoardAnnounce;
import com.gamejigi.wiki.domain.member.role.Role;
import com.gamejigi.wiki.entity.board.BoardEntity;
import com.gamejigi.wiki.entity.boardAnnounce.BoardAnnounceEntity;
import com.gamejigi.wiki.entity.category.CategoryEntity;
import com.gamejigi.wiki.entity.member.MemberEntity;
import com.gamejigi.wiki.repository.board.BoardRepository;
import com.gamejigi.wiki.repository.boardAnnounce.BoardAnnounceRepository;
import com.gamejigi.wiki.repository.category.CategoryRepository;
import com.gamejigi.wiki.repository.member.MemberRepository;
import com.gamejigi.wiki.service.board.BoardService;
import com.gamejigi.wiki.util.PaginationRequest;
import com.gamejigi.wiki.util.PaginationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardAnnounceServiceImpl implements BoardAnnounceService {

    private final BoardAnnounceRepository boardAnnounceRepository;
    private final BoardRepository boardRepository;
    private final CategoryRepository categoryRepository;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<BoardAnnounce> getBoardAnnounceList() {
        return boardAnnounceRepository
                .findAll(Sort.by(Sort.Order.asc("title")))
                .stream()
                .map(BoardAnnounce::new)
                .toList();
    }
    @Override
    public PaginationResponse<BoardAnnounce> getBoardAnnounceList(PaginationRequest request) {
        String searchStr = "%" + request.getSearch() + "%";
        Pageable pageable = request.getPageable();

        Page<BoardAnnounceEntity> result;
        if (searchStr.compareTo("%%") == 0) {
            result = boardAnnounceRepository.findAll(pageable);
        }
        else {
            result = boardAnnounceRepository.findPageByTitleLike(searchStr, pageable);
        }

        return PaginationResponse.<BoardAnnounce>builder()
                .request(request)
                .columnList(result.stream()
                        .map(BoardAnnounce::new)
                        .toList())
                .pageCount(result.getTotalPages())
                .rowsCount(result.getTotalElements())
                .build();
    }

    @Override
    public void createTestcase() {
        //관리자 계정 찾기
        MemberEntity su = memberRepository.findByUserId("admin")
                .orElseGet(() -> {
                    //관리자 계정 없으면 만들기
                    MemberEntity member = MemberEntity.builder()
                            .name("admin")
                            .email("email")
                            .role(Role.ADMIN)
                            .gender(true)
                            .userId("admin")
                            .pw(passwordEncoder.encode("admin"))
                            .build();
                    return memberRepository.save(member);
                });

        //category1 만들기
        CategoryEntity category1 = categoryRepository.findByName("category1")
                .orElseGet(() -> {
                    //category1 없으면 만들기
                    var category = CategoryEntity.builder()
                            .su(su)
                            .name("category1")
                            .build();
                    return categoryRepository.save(category);
                });

        //board1 만들기
        BoardEntity board1 = boardRepository.findByName("board1")
                .orElseGet(() -> {
                    //board1 없으면 만들기
                    var board = BoardEntity.builder()
                            .su(su)
                            .category(category1)
                            .name("board1")
                            .build();
                    return boardRepository.save(board);
                });

        //게시판 100개 생성
        for (int i = 0; i < 100; i++) {
            BoardAnnounceEntity boardAnnounce = BoardAnnounceEntity.builder()
                    .board(board1)
                    .su(su)
                    .title("boardAnnounce"+i)
                    .build();
            boardAnnounceRepository.save(boardAnnounce);
        }
    }

    @Override
    public void deleteTestcase() {
        boardAnnounceRepository.deleteAll();
    }

    @Override
    public BoardAnnounce getBoardAnnounceById(long id) {
        return boardAnnounceRepository.findById(id)
                .map(BoardAnnounce::new)
                .orElse(null);
    }

    @Override
    public void createBoardAnnounce(String title, long boardId, long suId) {
        MemberEntity su = memberRepository
                .findById(suId)
                .orElse(null);

        BoardEntity board = boardRepository
                .findById(boardId)
                .orElse(null);

        BoardAnnounceEntity boardAnnounce = BoardAnnounceEntity
                .builder()
                .su(su)
                .title(title)
                .board(board)
                .build();

        boardAnnounceRepository.save(boardAnnounce);
    }


    @Override
    public void delete(long id) {
        boardAnnounceRepository.deleteById(id);
    }

    @Override
    public void patch(long id, long suId, String title, long boardId) {
        BoardAnnounceEntity oldBoardAnnounce = boardAnnounceRepository.findById(id).orElse(null);
        BoardEntity newBoard = boardRepository.findById(boardId).orElse(null);
        MemberEntity newSu = memberRepository.findById(suId).orElse(null);

        BoardAnnounceEntity newBoardAnnounce = BoardAnnounceEntity.builder()
                .id(id)
                .title(title)
                .board(newBoard)
                .su(newSu)
                .build();

        boardAnnounceRepository.save(newBoardAnnounce);
    }
}
