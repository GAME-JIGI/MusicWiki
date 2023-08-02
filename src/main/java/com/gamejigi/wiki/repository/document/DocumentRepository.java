package com.gamejigi.wiki.repository.document;

import com.gamejigi.wiki.entity.document.DocumentEntity;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
public interface DocumentRepository extends JpaRepository<DocumentEntity, Long>{

    Page<DocumentEntity> findPageByName(String name, Pageable pageable);

    Page<DocumentEntity> findAll(Pageable pageable);


    Optional<DocumentEntity> findByName(String name);
}
