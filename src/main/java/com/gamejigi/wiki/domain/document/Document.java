package com.gamejigi.wiki.domain.document;

import com.gamejigi.wiki.domain.member.Member;
import com.gamejigi.wiki.entity.document.DocumentEntity;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class Document {
    private Long id;
    private Member su;
    private String name;

    private String text;
    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;
    int modifyingCount;

    public Document(DocumentEntity entity){
        id = entity.getId();
        su = new Member(entity.getSu());
        name = entity.getName();
        text = entity.getText();
        modifyingCount = entity.getModifyingCount();
    }
}
