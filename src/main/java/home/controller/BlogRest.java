package home.controller;

import home.model.Blog;
import home.model.Category;
import home.service.IBlogService;
import home.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/")
public class BlogRest {
    @Autowired
    ICategoryService categoryService;
    @Autowired
    IBlogService blogService;

    @GetMapping("category")
    public ResponseEntity<Iterable<Category>> findAllCategory() {
        List<Category> list = (List<Category>) categoryService.findAll();
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("blog")
    public ResponseEntity<Iterable<Blog>> findAllBlog() {
        List<Blog> list = (List<Blog>) blogService.findAll();
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @GetMapping("blog/category/{id}")
    public ResponseEntity<Iterable<Blog>> findByCategory(@PathVariable Long id){
        Optional<Category> categoryOptional = categoryService.findById(id);
        List<Blog> list = (List<Blog>) blogService.findAllByCategory(categoryOptional.get());
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("blog/{id}")
    public ResponseEntity<Blog> findByID(@PathVariable Long id){
        Optional<Blog> blog = blogService.findByID(id);
        if (blog.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(blog.get(), HttpStatus.OK);
    }
}
