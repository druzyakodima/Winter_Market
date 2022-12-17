package com.winter.market.core.service.category;

import com.winter.market.core.entities.Category;

import java.util.Optional;

public interface ICategoryService {

    Optional<Category> findByIdTitle(String title);
}
