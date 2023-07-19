package com.gamejigi.wiki.repository.document;

import com.gamejigi.wiki.entity.document.DocumentEntity;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
public interface DocumentRepository extends JpaRepository<DocumentEntity, Long>{
    Optional<DocumentEntity> findByDocTitle(String title);
}
