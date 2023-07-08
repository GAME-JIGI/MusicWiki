package com.gamejigi.wiki.repository.member;

import com.gamejigi.wiki.entity.member.MemberEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    Optional<MemberEntity> findByUser_id(String user_id);
}
