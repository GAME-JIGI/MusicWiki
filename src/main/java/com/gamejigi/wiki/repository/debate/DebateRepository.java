package com.gamejigi.wiki.repository.debate;

import com.gamejigi.wiki.entity.debate.DebateEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface DebateRepository extends JpaRepository<DebateEntity, Long>{
    Page<DebateEntity> findPageByNameLike(String name, Pageable pageable);

    Page<DebateEntity> findPageByDocumentNameLike(String name, Pageable pageable);

    Page<DebateEntity> findPageBySuNameLike(String name, Pageable pageable);

    Optional<DebateEntity> findByName(String name);

    // commentAble 값이 2인 멈춘 토론을 찾는 쿼리
    @Query("SELECT d FROM DebateEntity d WHERE d.commentAble = 2 AND d.lockTime <= current_timestamp")
    List<DebateEntity> findStoppedDebates();

    Page<DebateEntity> findAll(Pageable pageable);
}
