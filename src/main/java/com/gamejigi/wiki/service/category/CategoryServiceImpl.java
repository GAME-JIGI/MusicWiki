package com.gamejigi.wiki.service.category;

import com.gamejigi.wiki.domain.board.Board;
import com.gamejigi.wiki.domain.category.Category;
import com.gamejigi.wiki.domain.member.role.Role;
import com.gamejigi.wiki.entity.board.BoardEntity;
import com.gamejigi.wiki.entity.category.CategoryEntity;
import com.gamejigi.wiki.entity.member.MemberEntity;
import com.gamejigi.wiki.repository.board.BoardRepository;
import com.gamejigi.wiki.repository.category.CategoryRepository;
import com.gamejigi.wiki.repository.member.MemberRepository;
import com.gamejigi.wiki.service.board.BoardService;
import com.gamejigi.wiki.util.PaginationRequest;
import com.gamejigi.wiki.util.PaginationResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final BoardRepository boardRepository;
    private final CategoryRepository categoryRepository;
    private final MemberRepository memberRepository;

    @Override
    public List<Category> getCategoryList() {
        return categoryRepository
                .findAll(Sort.by(Order.asc("name")))
                .stream()
                .map(Category::new)
                .toList();
    }

    @Override
    public Category getById(long id) {
        return categoryRepository
                .findById(id)
                .map(Category::new)
                .orElse(null);
    }
}
