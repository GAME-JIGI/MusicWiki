package com.gamejigi.wiki.entity.document;

import com.gamejigi.wiki.entity.BaseTimeEntity;
<<<<<<< HEAD
import com.gamejigi.wiki.entity.member.MemberEntity;
=======
import com.gamejigi.wiki.entity.category.CategoryEntity;
import com.gamejigi.wiki.entity.member.MemberEntity;
import com.gamejigi.wiki.entity.post.PostEntity;
>>>>>>> feature/documentCRUD
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
<<<<<<< HEAD
=======
import jakarta.persistence.OneToMany;

import java.time.LocalDateTime;
import java.util.List;
>>>>>>> feature/documentCRUD
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class DocumentEntity extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "su_id")
    private MemberEntity su;

    @Column(name = "name", nullable = false, length = 10)
    private String name;

    @Column(name = "text")
    private String text;

    @Column(name = "modifying_count", nullable = false, length = 11)
    private int modifyingCount;

    @Builder
    public DocumentEntity(Long id, MemberEntity su, String name, String text,
                           int modifyingCount){
        this.id = id;
        this.su = su;
        this.name = name;
        this.text = text;
        this.modifyingCount = modifyingCount;
    }

}
