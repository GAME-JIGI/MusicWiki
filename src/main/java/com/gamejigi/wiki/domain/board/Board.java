package com.gamejigi.wiki.domain.board;

import com.gamejigi.wiki.domain.category.Category;
import com.gamejigi.wiki.domain.member.Member;
import com.gamejigi.wiki.entity.board.BoardEntity;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Board {
    private Long id;
    private String name;
    private Category category;
    private Member su;
    LocalDateTime createdDate;
    LocalDateTime modifiedDate;

    public Board(BoardEntity entity) {
        id = entity.getId();
        name = entity.getName();
        category = new Category(entity.getCategory());
        su = new Member(entity.getSu());
        createdDate = entity.getCreatedDate();
        modifiedDate = entity.getModifiedDate();
    }
}
