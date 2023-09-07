package com.gamejigi.wiki.entity.boardAnnounce;

import com.gamejigi.wiki.entity.BaseTimeEntity;
import com.gamejigi.wiki.entity.board.BoardEntity;
import com.gamejigi.wiki.entity.category.CategoryEntity;
import com.gamejigi.wiki.entity.member.MemberEntity;
import com.gamejigi.wiki.entity.post.PostEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class BoardAnnounceEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private BoardEntity board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "su_id")
    private MemberEntity su;

    @Builder
    public BoardAnnounceEntity(Long id, String title, BoardEntity board, MemberEntity su) {
        this.id = id;
        this.title = title;
        this.board = board;
        this.su = su;
    }
    public void patch(String name, MemberEntity newSu, BoardEntity newBoard) {
        this.title = name;
        su = newSu;
        board = newBoard;
    }
}