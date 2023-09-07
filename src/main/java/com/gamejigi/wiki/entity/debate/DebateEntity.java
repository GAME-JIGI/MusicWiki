package com.gamejigi.wiki.entity.debate;

import com.gamejigi.wiki.entity.debateComment.DebateCommentEntity;
import com.gamejigi.wiki.entity.document.DocumentEntity;
import com.gamejigi.wiki.entity.BaseTimeEntity;
import com.gamejigi.wiki.entity.member.MemberEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@Entity()
public class DebateEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 1)
    private Integer commentAble;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "document_id")
    private DocumentEntity document;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "su_id")
    private MemberEntity su;

    @OneToMany(mappedBy = "debate")
    private List<DebateCommentEntity> debateCommentList;
    @Builder
    public DebateEntity(Long id, String name, Integer commentAble,DocumentEntity document, MemberEntity su) {
        this.id = id;
        this.name = name;
        this.commentAble = commentAble;
        this.document = document;
        this.su = su;
    }

    public void adddebateCommentList(DebateCommentEntity debateComment) { debateCommentList.add(debateComment); }

}
