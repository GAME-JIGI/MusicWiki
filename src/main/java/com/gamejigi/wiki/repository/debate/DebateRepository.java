package com.gamejigi.wiki.repository.debate;

import com.gamejigi.wiki.domain.debateComment.DebateComment;
import com.gamejigi.wiki.entity.debate.DebateEntity;
import com.gamejigi.wiki.entity.debateComment.DebateCommentEntity;
import com.gamejigi.wiki.entity.document.DocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface DebateRepository extends JpaRepository<DebateEntity, Long>{
    Page<DebateEntity> findPageByNameLike(String name, Pageable pageable);

    Page<DebateEntity> findPageByDocumentNameLike(String name, Pageable pageable);

    Page<DebateEntity> findPageBySuNameLike(String name, Pageable pageable);

    Optional<DebateEntity> findByName(String name);

    Page<DebateEntity> findAll(Pageable pageable);
}
