package controller;

import model.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import service.IBlogService;

import java.util.List;

@Controller
public class BlogController {
    @Autowired
    private IBlogService blogService;

    @GetMapping("/blog")
    public ModelAndView home() {
        List<Blog> list = blogService.findAll();
        ModelAndView modelAndView = new ModelAndView("/home");
        modelAndView.addObject("blog", list);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("blog",new Blog());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createBlog(@ModelAttribute Blog blog){
        blogService.save(blog);
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("blog",new Blog());
        modelAndView.addObject("message","New blog created");
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEdit(@PathVariable Integer id){
        Blog blog = blogService.findByID(id);
        if(blog != null){
            ModelAndView modelAndView = new ModelAndView("/edit");
            modelAndView.addObject("blog",blog);
            return modelAndView;
        } else {
            return new ModelAndView("/error.404");
        }
    }
    @PostMapping("/edit")
    public ModelAndView editBlog(@ModelAttribute Blog blog){
        blogService.save(blog);
        ModelAndView modelAndView = new ModelAndView("/edit");
        modelAndView.addObject("blog",blog);
        modelAndView.addObject("message", "Blog updated");
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable Integer id){
        blogService.remove(id);
        return new ModelAndView("redirect:/blog");
    }
    @GetMapping("/read/{id}")
    public ModelAndView view(@PathVariable Integer id){
        Blog blog = blogService.findByID(id);
        ModelAndView modelAndView = new ModelAndView("/view");
        modelAndView.addObject("blog",blog);
        return modelAndView;
    }
}
