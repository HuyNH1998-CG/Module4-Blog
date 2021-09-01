package home.controller;

import home.model.Blog;
import home.model.Category;
import home.model.Comment;
import home.repository.ICommentRepo;
import home.service.ICategoryService;
import home.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import home.service.IBlogService;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class BlogController {
    @Autowired
    private IBlogService blogService;
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private ICommentService commentService;

    @ModelAttribute("categories")
    public Iterable<Category> categories() {
        return categoryService.findAll();
    }

    @GetMapping("/")
    public ModelAndView index(){
        return new ModelAndView("redirect:/blog");
    }

    @GetMapping("/blog")
    public ModelAndView home(@PageableDefault(size = 5, sort = "date", direction = Sort.Direction.DESC) Pageable pageable, @RequestParam("search") Optional<String> search) {
        Page<Blog> blogs;
        ModelAndView modelAndView = new ModelAndView("/blog/home");
        if (search.isPresent()) {
            blogs = blogService.findAllByNameContaining(search.get(), pageable);
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
        Blog blog2 = blogService.save(blog);
        Category category = categoryService.findById(blog2.getCategory().getId()).get();
        category.getBlogs().add(blog2);
        categoryService.save(category);
        ModelAndView modelAndView = new ModelAndView("/blog/create");
        modelAndView.addObject("blog", new Blog());
        modelAndView.addObject("message", "New blog created");
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEdit(@PathVariable Long id) {
        Optional<Blog> blog = blogService.findByID(id);
        ModelAndView modelAndView = new ModelAndView("/blog/edit");
        modelAndView.addObject("blog", blog);
        return modelAndView;
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
        blog.get().setViews(blog.get().getViews() + 1);
        blogService.save(blog.get());
        modelAndView.addObject("comment", new Comment());
        modelAndView.addObject("blog", blog.get());
        return modelAndView;
    }

    @GetMapping("/read/{id}/like")
    public ModelAndView like(@PathVariable Long id) {
        Optional<Blog> blog = blogService.findByID(id);
        ModelAndView modelAndView = new ModelAndView("/blog/view");
        blog.get().setLikes(blog.get().getLikes() + 1);
        blogService.save(blog.get());
        modelAndView.addObject("comment", new Comment());
        modelAndView.addObject("blog", blog.get());
        return modelAndView;
    }

    @PostMapping("/read/{id}")
    public ModelAndView commentPost(@PathVariable Long id, @ModelAttribute Comment comment) {
        Optional<Blog> blog = blogService.findByID(id);
        comment.setBlog(blog.get());
        Optional<Comment> comment2 = commentService.findById(commentService.save(comment).getId());
        blog.get().getComments().add(comment2.get());
        blogService.save(blog.get());
        ModelAndView modelAndView = new ModelAndView("/blog/view");
        modelAndView.addObject("blog", blog.get());
        modelAndView.addObject("comment", new Comment());
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
