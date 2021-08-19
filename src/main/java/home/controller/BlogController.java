package home.controller;

import home.model.Blog;
import home.model.Category;
import home.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import home.service.IBlogService;

import java.util.Optional;

@Controller
public class BlogController {
    @Autowired
    private IBlogService blogService;
    @Autowired
    private ICategoryService categoryService;

    @ModelAttribute("categories")
    public Iterable<Category> categories() {
        return categoryService.findAll();
    }

    @GetMapping("/blog")
    public ModelAndView home(@PageableDefault(size = 5, sort = "date", direction = Sort.Direction.ASC) Pageable pageable, @RequestParam("search") Optional<String> search) {
        Page<Blog> blogs;
        ModelAndView modelAndView = new ModelAndView("/blog/home");
        if (search.isPresent()){
            blogs = blogService.findAllByNameContaining(search.get(),pageable);
        } else {
            blogs = blogService.findAll(pageable);
        }
        modelAndView.addObject("blog", blogs);
        modelAndView.addObject("categories", categoryService.findAll());
        return modelAndView;
    }

    @GetMapping("/create/blog")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/blog/create");
        modelAndView.addObject("blog", new Blog());
        return modelAndView;
    }

    @PostMapping("/create/blog")
    public ModelAndView createBlog(@ModelAttribute Blog blog) {
        blogService.save(blog);
        ModelAndView modelAndView = new ModelAndView("/blog/create");
        modelAndView.addObject("blog", new Blog());
        modelAndView.addObject("message", "New blog created");
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEdit(@PathVariable Long id) {
        Optional<Blog> blog = blogService.findByID(id);
        if (blog.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/blog/edit");
            modelAndView.addObject("blog", blog);
            return modelAndView;
        } else {
            return new ModelAndView("/error.404");
        }
    }

    @PostMapping("/edit")
    public ModelAndView editBlog(@ModelAttribute Blog blog) {
        blogService.save(blog);
        ModelAndView modelAndView = new ModelAndView("/blog/edit");
        modelAndView.addObject("blog", blog);
        modelAndView.addObject("message", "Blog updated");
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable Long id) {
        blogService.remove(id);
        return new ModelAndView("redirect:/blog");
    }

    @GetMapping("/read/{id}")
    public ModelAndView view(@PathVariable Long id) {
        Optional<Blog> blog = blogService.findByID(id);
        ModelAndView modelAndView = new ModelAndView("/blog/view");
        modelAndView.addObject("blog", blog.get());
        return modelAndView;
    }

    @GetMapping("/category/{id}")
    public ModelAndView viewByCategory(@PathVariable Long id, @PageableDefault(size = 5) Pageable pageable) {
        Optional<Category> category = categoryService.findById(id);
        Page<Blog> blogs = blogService.findAllByCategory(category.get(), pageable);
        ModelAndView modelAndView = new ModelAndView("/blog/home");
        modelAndView.addObject("blog", blogs);
        modelAndView.addObject("categories", categoryService.findAll());
        return modelAndView;
    }
}
