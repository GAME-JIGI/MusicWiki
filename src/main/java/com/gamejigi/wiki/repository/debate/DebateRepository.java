package com.gamejigi.wiki.repository.debate;

import com.gamejigi.wiki.entity.debate.DebateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DebateRepository extends JpaRepository<DebateEntity, Long>{
    Page<DebateEntity> findPageByNameLike(String name, Pageable pageable);

    Page<DebateEntity> findAll(Pageable pageable);
}
