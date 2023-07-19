package com.gamejigi.wiki.entity.document;

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
public class DocumentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doc_id", length = 20)
    private Long docId;

    @Column(name = "doc_member_id", length = 20)
    private Long docMemberId;

    @Column(name = "doc_title", length = 45)
    private String docTitle;

    @Column(name = "doc_content", length = 255)
    private String docContent;

    @Column(name = "doc_mod_count", length = 20)
    private Long docModCount;

    @Builder
    public DocumentEntity(Long id, Long member_id, String title, String content, Long mod_count){
        this.docId = id;
        this.docMemberId = member_id;
        this.docTitle = title;
        this.docContent = content;
        this.docModCount = mod_count;
    }
}
