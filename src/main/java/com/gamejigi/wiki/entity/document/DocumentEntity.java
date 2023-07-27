package com.gamejigi.wiki.entity.document;

import com.gamejigi.wiki.entity.BaseTimeEntity;
import com.gamejigi.wiki.entity.member.MemberEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity()

public class DocumentEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "su_id")
    private MemberEntity su;

    @Column(nullable = false, length = 10000)
    private int modifyingCount;

    @Builder
    public DocumentEntity(Long id, String name, MemberEntity su, int modifyingCount){
        this.id = id;
        this.name = name;
        this.su = su;
        this.modifyingCount = modifyingCount;
    }

}
