package com.gamejigi.wiki.repository.board;

import com.gamejigi.wiki.entity.board.BoardEntity;
import com.gamejigi.wiki.entity.category.CategoryEntity;
import com.gamejigi.wiki.entity.member.MemberEntity;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNullApi;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

    Page<BoardEntity> findPageByNameLike(String name, Pageable pageable);
    Optional<BoardEntity> findByName(String name);
    Page<BoardEntity> findAll(Pageable pageable);
}
