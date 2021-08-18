package repository;

import model.Blog;

import java.util.List;

public interface IBlogRepo {
List<Blog> findAll();
Blog findByID(Integer id);
void save(Blog blog);
void remove(Integer id);
}
