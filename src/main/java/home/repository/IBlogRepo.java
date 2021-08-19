package home.repository;

import home.model.Blog;
import home.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBlogRepo extends PagingAndSortingRepository<Blog, Long> {
Page<Blog> findAllByCategory(Category category, Pageable pageable);
void deleteAllByCategory(Category category);
Page<Blog> findAllByNameContaining(String name, Pageable pageable);
}
