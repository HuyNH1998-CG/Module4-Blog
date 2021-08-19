package home.service;

import home.model.Category;

import java.util.Optional;

public interface ICategoryService {
    Iterable<Category> findAll();

    Optional<Category> findById(long id);

    void save(Category category);

    void remove(Long id);
}
