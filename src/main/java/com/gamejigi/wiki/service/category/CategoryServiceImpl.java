package com.gamejigi.wiki.service.category;

import com.gamejigi.wiki.domain.category.Category;
import com.gamejigi.wiki.domain.member.role.Role;
import com.gamejigi.wiki.entity.category.CategoryEntity;
import com.gamejigi.wiki.entity.member.MemberEntity;
import com.gamejigi.wiki.repository.category.CategoryRepository;
import com.gamejigi.wiki.repository.member.MemberRepository;
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

    private final CategoryRepository categoryRepository;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<Category> getCategoryList() {
        return categoryRepository
                .findAll(Sort.by(Order.asc("name")))
                .stream()
                .map(Category::new)
                .toList();
    }

    @Override
    public PaginationResponse<Category> getCategoryList(PaginationRequest request) {
        String searchStr = "%" + request.getSearch() + "%";
        Pageable pageable = request.getPageable();

        Page<CategoryEntity> result;
        if (searchStr.compareTo("%%") == 0) {
            result = categoryRepository.findAll(pageable);
        }
        else {
            result = categoryRepository.findPageByNameLike(searchStr, pageable);
        }

        return PaginationResponse.<Category>builder()
                .request(request)
                .columnList(result.stream()
                        .map(Category::new)
                        .toList())
                .pageCount(result.getTotalPages())
                .rowsCount(result.getTotalElements())
                .build();
    }

    @Override
    public Category getById(long id) {
        return categoryRepository
                .findById(id)
                .map(Category::new)
                .orElse(null);
    }

    @Override
    public void createTestcase() {
        //관리자 계정 찾기
        MemberEntity su = memberRepository.findByUserId("admin")
                .orElseGet(() -> {
                    //관리자 계정 없으면 만들기
                    MemberEntity member = MemberEntity.builder()
                            .name("admin")
                            .email("email")
                            .role(Role.ADMIN)
                            .isSu(true)
                            .userId("admin")
                            .pw(passwordEncoder.encode("admin"))
                            .build();
                    return memberRepository.save(member);
                });

        //게시판 카테고리 100개 생성
        for (int i = 0; i < 100; i++) {
            CategoryEntity board = CategoryEntity.builder()
                    .su(su)
                    .name("category"+i)
                    .build();
            categoryRepository.save(board);
        }
    }

    @Override
    public void deleteTestcase() {
        categoryRepository.deleteAll();
    }

    @Override
    public void createCategory(String name, long suId) {
        MemberEntity su = memberRepository
                .findById(suId)
                .orElse(null);

        CategoryEntity category = CategoryEntity
                .builder()
                .name(name)
                .su(su)
                .build();

        categoryRepository.save(category);
    }

    @Override
    public void delete(long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public void patch(long id, long suId, String name) {
        MemberEntity newSu = memberRepository.findById(suId).orElse(null);

        CategoryEntity newCategory = CategoryEntity.builder()
                .id(id)
                .name(name)
                .su(newSu)
                .build();

        categoryRepository.save(newCategory);
    }
}
