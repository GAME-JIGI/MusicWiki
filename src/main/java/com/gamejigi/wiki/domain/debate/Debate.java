package com.gamejigi.wiki.domain.debate;

import com.gamejigi.wiki.domain.document.Document;
import com.gamejigi.wiki.domain.member.Member;
import com.gamejigi.wiki.entity.debate.DebateEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@Builder
public class Debate {

    private Long id;
    private String name;
    private Integer commentAble;
    private Document document;
    private Member su;
    LocalDateTime lockTime;
    LocalDateTime createdDate;
    LocalDateTime modifiedDate;


    public Debate(DebateEntity entity) {
        id = entity.getId();
        name = entity.getName();
        commentAble = entity.getCommentAble();
        document = new Document(entity.getDocument());
        su = new Member(entity.getSu());
        lockTime = entity.getLockTime();
        createdDate = entity.getCreatedDate();
        modifiedDate = entity.getModifiedDate();
    }
}
