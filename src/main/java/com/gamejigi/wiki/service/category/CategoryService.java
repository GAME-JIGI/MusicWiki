package com.gamejigi.wiki.service.category;

import com.gamejigi.wiki.domain.category.Category;
import com.gamejigi.wiki.util.PaginationRequest;
import com.gamejigi.wiki.util.PaginationResponse;
import java.util.List;

public interface CategoryService {

    List<Category> getCategoryList();

    PaginationResponse<Category> getCategoryList(PaginationRequest request);

    Category getById(long id);

    void createTestcase();

    void deleteTestcase();

    void createCategory(String name, long suId);

    void delete(long id);

    void patch(long id, long suId, String name);
}
