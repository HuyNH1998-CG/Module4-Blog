package home.service;

import home.model.Blog;
import home.model.Category;
import home.repository.IBlogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BlogService implements IBlogService {
    @Autowired
    private IBlogRepo blogRepo;

    @Override
    public Iterable<Blog> findAll() {
        return blogRepo.findAll();
    }

    @Override
    public Optional<Blog> findByID(Long id) {
        return blogRepo.findById(id);
    }

    @Override
    public void save(Blog blog) {
        blogRepo.save(blog);
    }

    @Override
    public void remove(Long id) {
        blogRepo.deleteById(id);
    }

    @Override
    public Page<Blog> findAll(Pageable pageable) {
        return blogRepo.findAll(pageable);
    }

    @Override
    public Page<Blog> findAllByCategory(Category category, Pageable pageable) {
        return blogRepo.findAllByCategory(category,pageable);
    }

    @Override
    public Iterable<Blog> findAllByCategory(Category category) {
        return blogRepo.findAllByCategory(category);
    }

    @Override
    public void deleteAllByCategory(Category category){
        blogRepo.deleteAllByCategory(category);
    }

    @Override
    public Page<Blog> findAllByNameContaining(String name, Pageable pageable) {
        return blogRepo.findAllByNameContaining(name,pageable);
    }

}
