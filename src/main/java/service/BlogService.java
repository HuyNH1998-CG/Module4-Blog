package service;

import model.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import repository.IBlogRepo;

import java.util.List;

public class BlogService implements IBlogService {
    @Autowired
    private IBlogRepo BlogRepo;

    @Override
    public List<Blog> findAll() {
        return BlogRepo.findAll();
    }

    @Override
    public Blog findByID(Integer id) {
        return BlogRepo.findByID(id);
    }

    @Override
    public void save(Blog blog) {
        BlogRepo.save(blog);
    }

    @Override
    public void remove(Integer id) {
        BlogRepo.remove(id);
    }
}
