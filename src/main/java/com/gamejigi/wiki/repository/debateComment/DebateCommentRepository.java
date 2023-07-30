package com.gamejigi.wiki.repository.debateComment;

import com.gamejigi.wiki.domain.document.Document;
import com.gamejigi.wiki.entity.debate.DebateEntity;
import com.gamejigi.wiki.entity.debateComment.DebateCommentEntity;
import com.gamejigi.wiki.entity.document.DocumentEntity;
import com.gamejigi.wiki.entity.member.MemberEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
public interface DebateCommentRepository extends JpaRepository<DebateCommentEntity, Long>{

    Page<DebateCommentEntity> findPageByNameLike(String name, Pageable pageable);

    Page<DebateCommentEntity> findPageByDebateNameLike(String debateName, Pageable pageable);

    Page<DebateCommentEntity> findPageByDocumentNameLike(String documentName, Pageable pageable);

    Page<DebateCommentEntity> findPageByWriterNameLike(String writerName, Pageable pageable);

    Page<DebateCommentEntity> findAll(Pageable pageable);

}
