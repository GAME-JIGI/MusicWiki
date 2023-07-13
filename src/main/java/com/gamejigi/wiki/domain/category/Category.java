package com.gamejigi.wiki.domain.category;

import com.gamejigi.wiki.domain.member.Member;
import com.gamejigi.wiki.entity.category.CategoryEntity;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Category {
    private Long id;
    private String name;
    private Member su;
    LocalDateTime createdDate;
    LocalDateTime modifiedDate;

    public Category(CategoryEntity entity) {
        id = entity.getId();
        name = entity.getName();
        su = new Member(entity.getSu());
        createdDate = entity.getCreatedDate();
        modifiedDate = entity.getModifiedDate();
    }
}
