package com.winter.market.core.service.category;

import com.winter.market.core.entities.Category;
import com.winter.market.core.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CategoryService implements ICategoryService{

    private final CategoryRepository categoryRepository;

    @Override
    public Optional<Category> findByIdTitle(String title) {
        return categoryRepository.findByTitle(title);
    }
}
