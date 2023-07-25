package com.gamejigi.wiki.repository.category;

import com.gamejigi.wiki.entity.category.CategoryEntity;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    Optional<CategoryEntity> findById(long id);

    Optional<CategoryEntity> findByName(String name);

    Page<CategoryEntity> findPageByNameLike(String searchStr, Pageable pageable);
}
