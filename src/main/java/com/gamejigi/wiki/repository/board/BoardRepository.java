package com.gamejigi.wiki.repository.board;

import com.gamejigi.wiki.entity.board.BoardEntity;
import com.gamejigi.wiki.entity.member.MemberEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

    Page<BoardEntity> findPageByName(String name, Pageable pageable);
}
