package com.gamejigi.wiki.service.board;

import com.gamejigi.wiki.domain.board.Board;
import com.gamejigi.wiki.domain.member.role.Role;
import com.gamejigi.wiki.entity.board.BoardEntity;
import com.gamejigi.wiki.entity.category.CategoryEntity;
import com.gamejigi.wiki.entity.member.MemberEntity;
import com.gamejigi.wiki.repository.board.BoardRepository;
import com.gamejigi.wiki.repository.category.CategoryRepository;
import com.gamejigi.wiki.repository.member.MemberRepository;
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
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final CategoryRepository categoryRepository;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<Board> getBoardList() {
        return boardRepository
                .findAll(Sort.by(Sort.Order.asc("name")))
                .stream()
                .map(Board::new)
                .toList();
    }
    @Override
    public PaginationResponse<Board> getBoardList(PaginationRequest request) {
        String searchStr = "%" + request.getSearch() + "%";
        Pageable pageable = request.getPageable();

        Page<BoardEntity> result;
        if (searchStr.compareTo("%%") == 0) {
            result = boardRepository.findAll(pageable);
        }
        else {
            result = boardRepository.findPageByNameLike(searchStr, pageable);
        }

        return PaginationResponse.<Board>builder()
                .request(request)
                .columnList(result.stream()
                        .map(Board::new)
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
                    CategoryEntity category = CategoryEntity.builder()
                            .su(su)
                            .name("category1")
                            .build();
                    return categoryRepository.save(category);
                });

        //게시판 100개 생성
        for (int i = 0; i < 100; i++) {
            BoardEntity board = BoardEntity.builder()
                    .category(category1)
                    .su(su)
                    .name("board"+i)
                    .build();
            boardRepository.save(board);
        }
    }

    @Override
    public void deleteTestcase() {
        boardRepository.deleteAll();
    }

    @Override
    public Board getBoardById(long id) {
        return boardRepository.findById(id)
                .map(Board::new)
                .orElse(null);
    }

    @Override
    public void createBoard(String name, long categoryId, long suId) {

        MemberEntity su = memberRepository
                .findById(suId)
                .orElse(null);

        CategoryEntity category = categoryRepository
                .findById(categoryId)
                .orElse(null);

        BoardEntity board = BoardEntity
                .builder()
                .su(su)
                .name(name)
                .category(category)
                .build();

        boardRepository.save(board);
    }

    @Override
    public void delete(long id) {
        boardRepository.deleteById(id);
    }

    @Override
    public void patch(long id, long suId, String name, long categoryId) {
        CategoryEntity newCategory = categoryRepository.findById(categoryId).orElse(null);
        MemberEntity newSu = memberRepository.findById(suId).orElse(null);

        boardRepository
                .findById(id)
                .map((oldBoard) -> {
                    oldBoard.patch(name, newSu, newCategory);
                    return boardRepository.save(oldBoard);
                });
    }
}
