package com.gamejigi.wiki.entity.member;


import com.gamejigi.wiki.domain.member.role.Role;
import com.gamejigi.wiki.entity.BaseTimeEntity;
import com.gamejigi.wiki.entity.board.BoardEntity;
import com.gamejigi.wiki.entity.category.CategoryEntity;
import com.gamejigi.wiki.entity.like.LikeEntity;
import com.gamejigi.wiki.entity.post.PostEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class MemberEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String userId;

    @Column(nullable = false)
    private String pw;

    @Column(length = 15, unique = true)
    private String phone;

    @Column(length = 100, unique = true)
    private String email;

    @Column(nullable = false, length = 20)
    private String name;

    @Column
    private LocalDate birth;

    @Column(nullable = false)
    private Boolean gender;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Role role;

    @OneToMany(mappedBy = "su")
    private List<CategoryEntity> categoryList;

    @OneToMany(mappedBy = "su")
    private List<BoardEntity> boardList;

    @OneToMany(mappedBy = "writer")
    private List<PostEntity> postList;

    @OneToMany(mappedBy = "user")
    private List<LikeEntity> likeList;

    @Builder
    public MemberEntity(String userId, String pw, String phone, String email, String name, LocalDate birth, Boolean gender, Role role) {
        this.userId = userId;
        this.pw = pw;
        this.phone = phone;
        this.email = email;
        this.name = name;
        this.birth = birth;
        this.gender = gender;
        this.role = role;
    }

    public void addCategoryList(CategoryEntity category) {
        categoryList.add(category);
    }

    public void addBoardList(BoardEntity board) {
        boardList.add(board);
    }

    public void addPostList(PostEntity post) {
        postList.add(post);
    }

    public void addLikeList(LikeEntity like) {
        likeList.add(like);
    }
}