package com.gamejigi.wiki.repository.member;

import com.gamejigi.wiki.entity.board.BoardEntity;
import com.gamejigi.wiki.entity.member.MemberEntity;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    Optional<MemberEntity> findByUserId(String userId);

    Optional<MemberEntity> findById(Long id);

    Page<MemberEntity> findPageByNameLike(String name, Pageable pageable);

    Page<MemberEntity> findByUserIdIsNotNull(Pageable pageable);

    Page<MemberEntity> findPageByNameLikeAndUserIdNotNull(String name, Pageable pageable);
}
