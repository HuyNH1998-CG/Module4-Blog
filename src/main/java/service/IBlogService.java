package service;

import model.Blog;

import java.util.List;

public interface IBlogService {
    List<Blog> findAll();

    Blog findByID(Integer id);

    void save(Blog blog);

    void remove(Integer id);

}
