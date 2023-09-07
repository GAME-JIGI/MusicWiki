package com.gamejigi.wiki.repository.boardAnnounce;

import com.gamejigi.wiki.entity.boardAnnounce.BoardAnnounceEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardAnnounceRepository extends JpaRepository<BoardAnnounceEntity, Long> {

    Page<BoardAnnounceEntity> findPageByTitleLike(String title, Pageable pageable);

    Page<BoardAnnounceEntity> findAll(Pageable pageable);
}
