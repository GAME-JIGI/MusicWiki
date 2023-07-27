package com.gamejigi.wiki.repository.debateComment;

import com.gamejigi.wiki.entity.debateComment.DebateCommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
public interface DebateCommentRepository extends JpaRepository<DebateCommentEntity, Long>{

    Page<DebateCommentEntity> findPageByNameLike(String name, Pageable pageable);

    Page<DebateCommentEntity> findAll(Pageable pageable);

}
