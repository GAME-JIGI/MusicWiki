package com.gamejigi.wiki.domain.boardAnnounce;

import com.gamejigi.wiki.domain.board.Board;
import com.gamejigi.wiki.entity.boardAnnounce.BoardAnnounceEntity;
import com.gamejigi.wiki.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class BoardAnnounce {
    private Long id;
    private String title;
    private Board board;
    private Member su;
    LocalDateTime createdDate;
    LocalDateTime modifiedDate;

    public BoardAnnounce(BoardAnnounceEntity entity) {
        id = entity.getId();
        title = entity.getTitle();
        board = new Board(entity.getBoard());
        su = new Member(entity.getSu());
        createdDate = entity.getCreatedDate();
        modifiedDate = entity.getModifiedDate();
    }
}
