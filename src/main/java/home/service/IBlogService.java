package home.service;

import home.model.Blog;
import home.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IBlogService {
    Iterable<Blog> findAll();

    Optional<Blog> findByID(Long id);

    void save(Blog blog);

    void remove(Long id);

    Page<Blog> findAll(Pageable pageable);

    Page<Blog> findAllByCategory(Category category, Pageable pageable);

    Iterable<Blog> findAllByCategory(Category category);

    void deleteAllByCategory(Category category);

    Page<Blog> findAllByNameContaining(String name, Pageable pageable);
}
