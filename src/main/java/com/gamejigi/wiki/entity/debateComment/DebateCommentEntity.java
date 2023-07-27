package com.gamejigi.wiki.entity.debateComment;

import com.gamejigi.wiki.entity.BaseTimeEntity;
import com.gamejigi.wiki.entity.debate.DebateEntity;
import com.gamejigi.wiki.entity.document.DocumentEntity;
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

public class DebateCommentEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "debate_id")
    private DebateEntity debate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "document_id")
    private DocumentEntity document;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id")
    private MemberEntity writer;

    @Builder
    public DebateCommentEntity(Long id, String content, DebateEntity debate, DocumentEntity document ,MemberEntity writer){
        this.id = id;
        this.name = content;
        this.document = document;
        this.debate = debate;
        this.writer = writer;
    }

}
