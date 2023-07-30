package com.gamejigi.wiki.domain.debateComment;

import com.gamejigi.wiki.domain.debate.Debate;
import com.gamejigi.wiki.entity.debateComment.DebateCommentEntity;
import com.gamejigi.wiki.domain.document.Document;
import com.gamejigi.wiki.domain.member.Member;
import com.gamejigi.wiki.entity.debate.DebateEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.w3c.dom.Text;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@Builder
public class DebateComment {

    private Long id;
    private String content;
    private Document document;
    private Debate debate;
    private Member su;
    LocalDateTime createdDate;
    LocalDateTime modifiedDate;

    public DebateComment(DebateCommentEntity entity) {
        id = entity.getId();
        content = entity.getName();
        document = new Document(entity.getDocument());
        debate = new Debate(entity.getDebate());
        su = new Member(entity.getWriter());
        createdDate = entity.getCreatedDate();
        modifiedDate = entity.getModifiedDate();
    }

}
