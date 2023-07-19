package com.gamejigi.wiki.domain.document;

import com.gamejigi.wiki.entity.document.DocumentEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class Document {
     Long docId;
     Long docMemberId;
     String docTitle;
     String docContent;
     Long docModCount;

    public Document(DocumentEntity docEntity){
        docId = docEntity.getDocId();
        docMemberId = docEntity.getDocMemberId();
        docTitle = docEntity.getDocTitle();
        docContent = docEntity.getDocContent();
        docModCount = docEntity.getDocModCount();
    }
}
