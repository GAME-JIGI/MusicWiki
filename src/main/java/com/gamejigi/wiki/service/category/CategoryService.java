package com.gamejigi.wiki.service.category;

import com.gamejigi.wiki.domain.category.Category;
import java.util.List;

public interface CategoryService {

    List<Category> getCategoryList();

    Category getById(long id);
}
