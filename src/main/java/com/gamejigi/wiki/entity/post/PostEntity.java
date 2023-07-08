package com.gamejigi.wiki.entity.post;

import com.gamejigi.wiki.entity.BaseTimeEntity;
import com.gamejigi.wiki.entity.board.BoardEntity;
import com.gamejigi.wiki.entity.like.LikeEntity;
import com.gamejigi.wiki.entity.member.MemberEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class PostEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long views;

    @Column(nullable = false)
    private Long likes;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private BoardEntity board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id")
    private MemberEntity writer;

    @OneToMany(mappedBy = "post")
    private List<LikeEntity> likeList;

    @Builder
    public PostEntity(Long id, Long views, Long likes, String content, BoardEntity board, MemberEntity writer) {
        this.id = id;
        this.views = views;
        this.likes = likes;
        this.content = content;
        this.board = board;
        this.writer = writer;
    }

    public void addLikeList(LikeEntity like) { likeList.add(like); }
}