package com.gamejigi.wiki.entity.board;

import com.gamejigi.wiki.entity.BaseTimeEntity;
import com.gamejigi.wiki.entity.category.CategoryEntity;
import com.gamejigi.wiki.entity.member.MemberEntity;
import com.gamejigi.wiki.entity.post.PostEntity;
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
public class BoardEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 10)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "su_id")
    private MemberEntity su;

    @OneToMany(mappedBy = "board")
    private List<PostEntity> postList;


    @Builder
    public BoardEntity(Long id, String name, CategoryEntity category, MemberEntity su) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.su = su;
    }

    public void addPostList(PostEntity post) { postList.add(post); }

    public void patch(String name, MemberEntity newSu, CategoryEntity newCategory) {
        this.name = name;
        su = newSu;
        category = newCategory;
    }
}